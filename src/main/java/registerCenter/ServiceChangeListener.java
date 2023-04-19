package registerCenter;

import java.util.List;

public interface ServiceChangeListener {
    /**
     * 服务变更事件回调方法
     *
     * @param instances 变更后的服务实例列表
     */
    void onServiceChange(List<ServiceInstance> instances);
}