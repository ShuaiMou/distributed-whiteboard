package remoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Communication extends Remote{
    void sendMessage(Client client, String message) throws RemoteException;
    void showHint(String message) throws RemoteException;
    void managerLogin(Client client) throws RemoteException;
    void collaboratorLogin(Client client) throws RemoteException;
}
