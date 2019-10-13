package server;


import remoteInterface.Communication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(9999);
            Communication server = new ServerImpl();

            Naming.rebind("rmi://localhost:9999/Communication", server);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
