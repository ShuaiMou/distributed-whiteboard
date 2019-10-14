package remoteInterface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Communication extends Remote{
    void sendMessage(Client client, String message) throws RemoteException;
    void showHint(String message) throws RemoteException;
    void managerLogin(Client client) throws RemoteException;
    void collaboratorLogin(Client client) throws RemoteException;
    void quit(Client client) throws RemoteException;
    //the parameter is designed for multi-team in the server.
    void close(Client client) throws RemoteException;
    void draw(byte[] bytes) throws IOException;
}
