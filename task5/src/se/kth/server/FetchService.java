package se.kth.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FetchService extends Remote {
    String fetchMail(String host, String storeType, String user, String password) throws RemoteException;
}
