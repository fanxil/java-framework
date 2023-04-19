package rpc.rmiAndRegisterCenter.ServerB;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 服务B接口
public interface IServiceB extends Remote {
    int add(int a, int b) throws RemoteException;
}