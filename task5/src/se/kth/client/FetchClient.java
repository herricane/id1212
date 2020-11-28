package se.kth.client;

import se.kth.server.FetchService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FetchClient {
    public static void main(String[] args) {
        try {
            FetchService f = (FetchService) Naming.lookup("rmi://127.0.0.1:5099/fetch-service");
            String firstMail = f.fetchMail("webmail.kth.se", "imaps", "chenhui", "Xch159357");
            System.out.println(firstMail);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
