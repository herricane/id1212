package se.kth.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FetchServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(5099);
            FetchService fs = new FetchServiceImpl();
            registry.rebind("fetch-service", fs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
