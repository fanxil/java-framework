package rpc.rmiAndRegisterCenter.ServerB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// 服务B实现
public class ServiceBImpl extends UnicastRemoteObject implements IServiceB {
    protected ServiceBImpl() throws RemoteException {
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}