package rpc.rmiAndRegisterCenter.center;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

// 注册中心
public class RegistryCenter {
    // 服务列表
    private static Map<String, String> serviceMap = new HashMap<>();

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RegistryCenter", new RegistryCenterImpl());
        System.out.println("RegistryCenter started.");
    }

    public static synchronized void register(String serviceName, String serviceUrl) {
        serviceMap.put(serviceName, serviceUrl);
        System.out.println(serviceName + " registered successfully: " + serviceUrl);
    }

    public static synchronized String getServiceUrl(String serviceName) {
        return serviceMap.get(serviceName);
    }
}