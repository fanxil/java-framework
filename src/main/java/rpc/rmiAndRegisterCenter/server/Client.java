package rpc.rmiAndRegisterCenter.server;

import rpc.rmiAndRegisterCenter.ServerA.IServiceA;
import rpc.rmiAndRegisterCenter.center.IRegistryCenter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// 客户端
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // 从注册中心获取服务A的地址和端口号
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        IRegistryCenter registryCenter = (IRegistryCenter) registry.lookup("RegistryCenter");
        String serviceAUrl = registryCenter.getServiceUrl("ServiceA");

        // 连接服务A
        IServiceA serviceA = (IServiceA) Naming.lookup(serviceAUrl);

        // 调用服务A的方法
        String result = serviceA.sayHello("Bob");
        System.out.println(result);
    }
}