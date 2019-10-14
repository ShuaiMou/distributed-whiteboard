package server;

import remoteInterface.Client;
import remoteInterface.Communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl extends UnicastRemoteObject implements Communication {

    private static List<Client> users;

    public ServerImpl() throws RemoteException{
        users = new ArrayList<Client>(10);
    }

    public void sendMessage(Client client, String message) throws RemoteException {
        String username = client.getUsername();
        String msg = username + ": "+ message + "\n";
        for (Client c : users){
            c.showMessage(msg);
        }
    }

    public void showHint(String message) throws RemoteException{
        users.get(0).hintWindow(message);

    }

    public void managerLogin(Client client) {
        users.add(client);
    }

    public void collaboratorLogin(Client client) throws RemoteException {
        if (users.size() != 0){
            boolean confirmation = users.get(0).hintWindow(client.getUsername() + " want to share your whiteboard");
            if ( !confirmation ){
                client.hintWindow("you are not permitted.");
                client.exit();
            }else {
                users.add(client);
            }
        }
    }

}
