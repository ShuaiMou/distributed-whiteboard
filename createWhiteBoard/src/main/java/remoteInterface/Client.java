package remoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote{
    void showMessage(String message) throws RemoteException;
    String getUsername() throws RemoteException;
    boolean hintWindow(String message) throws RemoteException;
    void exit() throws RemoteException;
}
