package rpc.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public interface RmiInterface extends Remote {


    String rmi1(String str) throws RemoteException;
}
