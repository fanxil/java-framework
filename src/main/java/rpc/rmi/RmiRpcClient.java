package rpc.rmi;

import rpc.rmi.server.RmiInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public class RmiRpcClient {


    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        RmiInterface lookup = (RmiInterface) Naming.lookup("rmi://localhost:9999/rmi");

        String str = lookup.rmi1("客户端使用rpc调用");
        System.out.println(str);
    }

}
