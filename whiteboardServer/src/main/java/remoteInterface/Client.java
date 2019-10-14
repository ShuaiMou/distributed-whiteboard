package remoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Client extends Remote{
    void showMessage(String message) throws RemoteException;
    String getUsername() throws RemoteException;
    boolean hintWindow(String message) throws RemoteException;
    void exit() throws RemoteException;
    void showOnlineUser(List<Client> clients) throws RemoteException;
}
