package remoteInterface;

import WhiteboardUtil.Point;

import java.awt.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Client extends Remote{
    void showMessage(String message) throws RemoteException;
    String getUsername() throws RemoteException;
    boolean hintWindow(String message) throws RemoteException;
    void exit() throws RemoteException;
    void showOnlineUser(List<Client> clients) throws RemoteException;
    void paintImage(byte[] bytes) throws IOException;
    void paint(Point[] points, Color color, String command, boolean flag) throws RemoteException;
}
