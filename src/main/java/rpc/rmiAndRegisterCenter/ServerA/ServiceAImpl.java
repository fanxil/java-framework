package rpc.rmiAndRegisterCenter.ServerA;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// 服务A实现
public class ServiceAImpl extends UnicastRemoteObject implements IServiceA {
    protected ServiceAImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "! This is ServiceA.";
    }
}