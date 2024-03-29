package remoteInterface;

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
    void paint(List<Integer> pointss, Color color, String command, boolean flag,String input) throws RemoteException;
    byte[] getImage() throws RemoteException;
    void clearWhiteboard() throws RemoteException;
    boolean isRunningStatus() throws RemoteException;
    void showEditingUser(String username, boolean flag) throws  RemoteException;
}
