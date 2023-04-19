package rpc.rmiAndRegisterCenter.ServerB;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// 服务B
public class ServiceB {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("ServiceB", new ServiceBImpl());
        System.out.println("ServiceB started.");
    }
}