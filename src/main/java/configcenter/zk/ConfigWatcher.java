package configcenter.zk;

import configcenter.store.Config;
import configcenter.store.ConfigDao;
import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

//使用Zookeeper来实现数据同步功能。当配置信息发生变化时，可以通过Zookeeper通知所有节点更新配置信息。
public class ConfigWatcher implements Watcher {
    private ConfigDao configDao;
    private ZooKeeper zooKeeper;

    public ConfigWatcher(ConfigDao configDao, String connectString) throws IOException {
        this.configDao = configDao;
        this.zooKeeper = new ZooKeeper(connectString, 5000, this);
    }

    @SneakyThrows
    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDataChanged) {
            String path = event.getPath();
            String key = path.substring(path.lastIndexOf("/") + 1);
            String value = new String(zooKeeper.getData(path, false, null));
            Config config = new Config();
            config.setKey(key);
            config.setValue(value);
            configDao.save(config);
        }
    }

    public void watch() throws KeeperException, InterruptedException {
        List<String> keys = configDao.findAllKeys();
        for (String key : keys) {
            String path = "/config/" + key;
            Stat stat = zooKeeper.exists(path, this);
            if (stat != null) {
                byte[] data = zooKeeper.getData(path, false, null);
                String value = new String(data);
                Config config = new Config();
                config.setKey(key);
                config.setValue(value);
                configDao.save(config);
            } else {
                String value = configDao.findByKey(key).getValue();
                zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }
    }
}
