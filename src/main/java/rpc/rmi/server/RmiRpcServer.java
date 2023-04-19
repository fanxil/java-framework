package rpc.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public class RmiRpcServer extends UnicastRemoteObject implements RmiInterface, Remote {


    public RmiRpcServer() throws RemoteException {
        super();
    }

    @Override
    public String rmi1(String str) throws RemoteException {
        System.out.println(str);
        return "rmi 调用成功";
    }
}
