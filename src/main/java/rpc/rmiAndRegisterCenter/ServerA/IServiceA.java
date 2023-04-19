package rpc.rmiAndRegisterCenter.ServerA;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 服务A接口
public interface IServiceA extends Remote {
    String sayHello(String name) throws RemoteException;
}