package registerCenter;

import java.util.List;

public interface Registry {
    /**
     * 注册服务实例
     *
     * @param serviceInstance 服务实例
     */
    void register(ServiceInstance serviceInstance);

    /**
     * 查询指定服务名的所有服务实例
     *
     * @param serviceName 服务名
     * @return 服务实例列表
     */
    List<ServiceInstance> getServiceInstances(String serviceName);

    /**
     * 订阅服务变更事件
     *
     * @param serviceName 服务名
     * @param listener    监听器
     */
    void subscribeService(String serviceName, ServiceChangeListener listener);

    /**
     * 取消订阅服务变更事件
     *
     * @param serviceName 服务名
     * @param listener    监听器
     */
    void unsubscribeService(String serviceName, ServiceChangeListener listener);
}
