package remoteInterface;

import java.awt.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import WhiteboardUtil.Point;
public interface Communication extends Remote{
    void sendMessage(Client client, String message) throws RemoteException;
    void showHint(String message) throws RemoteException;
    void managerLogin(Client client) throws RemoteException;
    void collaboratorLogin(Client client) throws RemoteException;
    void quit(Client client) throws RemoteException;
    //the parameter is designed for multi-team in the server.
    void quit1(String name) throws RemoteException;
    void close(Client client) throws RemoteException;
    void drawImage(byte[] bytes) throws IOException;
    void draw(Point[] points, Color color, String command, Client client,boolean flag) throws RemoteException;
}
