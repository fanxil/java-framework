package rpc.rmiAndRegisterCenter.center;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// 注册中心实现
public class RegistryCenterImpl extends UnicastRemoteObject implements IRegistryCenter {
    protected RegistryCenterImpl() throws RemoteException {
    }

    @Override
    public void register(String serviceName, String serviceUrl) throws RemoteException {
        MainClass.register(serviceName, serviceUrl);
    }

    @Override
    public String getServiceUrl(String serviceName) throws RemoteException {
        return MainClass.getServiceUrl(serviceName);
    }
}