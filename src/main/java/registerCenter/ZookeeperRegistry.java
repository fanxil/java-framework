package registerCenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZookeeperRegistry implements Registry {

    private final CuratorFramework client;

    public ZookeeperRegistry(String connectString) {
        client = CuratorFrameworkFactory.newClient(connectString, new RetryNTimes(3, 1000));
        client.start();
    }

    @Override
    public void register(ServiceInstance serviceInstance) {
        String path = "/services/" + serviceInstance.getServiceName() + "/" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        try {
            byte[] payload = toJsonBytes(serviceInstance);
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ServiceInstance> getServiceInstances(String serviceName) {
        String path = "/services/" + serviceName;
        try {
            List<String> children = client.getChildren().watched().forPath(path);
            List<ServiceInstance> instances = new ArrayList<>(children.size());
            for (String child : children) {
                byte[] data = client.getData().forPath(path + "/" + child);
                ServiceInstance instance = fromJsonBytes(data);
                instances.add(instance);
            }
            return instances;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void subscribeService(String serviceName, ServiceChangeListener listener) {
        String path = "/services/" + serviceName;
        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        PathChildrenCacheListener cacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                    case CHILD_REMOVED:
                    case CHILD_UPDATED:
                        List<ServiceInstance> instances = getServiceInstances(serviceName);
                        listener.onServiceChange(instances);
                        break;
                    default:
                        break;
                }
            }
        };
        cache.getListenable().addListener(cacheListener);
        try {
            cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unsubscribeService(String serviceName, ServiceChangeListener listener) {
        // ...
    }

    private byte[] toJsonBytes(ServiceInstance instance) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(instance);
    }

    private ServiceInstance fromJsonBytes(byte[] json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, ServiceInstance.class);
    }
}
