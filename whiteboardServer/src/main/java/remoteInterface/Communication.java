package remoteInterface;

import java.awt.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Communication extends Remote{
    void sendMessage(Client client, String message) throws RemoteException;
    void showHint(String message) throws RemoteException;
    void managerLogin(Client client) throws RemoteException;
    void collaboratorLogin(Client client) throws IOException;
    void quit(Client client) throws RemoteException;
    //the parameter is designed for multi-team in the server.
    void kickOut(String name) throws RemoteException;
    void close(Client client) throws RemoteException;
    void drawImage(byte[] bytes) throws IOException;
    void draw(java.util.List<Integer> pointss, Color color, String command, Client client, boolean flag,String input) throws RemoteException;
    List<String> getUsersName(Client client) throws RemoteException;
}
