package rpc.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public class MainClass {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

        RmiInterface rpcServer = new RmiRpcServer();

        LocateRegistry.createRegistry(9999);

//        Naming.bind("rpcServer" , rpcServer);

        Naming.rebind("rmi://localhost:9999/rmi", rpcServer);


        System.out.println("rpc server 启动完成");

    }

}
