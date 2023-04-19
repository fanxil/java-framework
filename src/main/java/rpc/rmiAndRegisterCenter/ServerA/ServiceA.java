package rpc.rmiAndRegisterCenter.ServerA;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// 服务A
public class ServiceA {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("ServiceA", new ServiceAImpl());
        System.out.println("ServiceA started.");
    }
}