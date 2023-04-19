package rpc.rmiAndRegisterCenter.center;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 注册中心接口
public interface IRegistryCenter extends Remote {
    void register(String serviceName, String serviceUrl) throws RemoteException;

    String getServiceUrl(String serviceName) throws RemoteException;
}