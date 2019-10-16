package server;

import remoteInterface.Client;
import remoteInterface.Communication;

import java.awt.*;
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

    public void managerLogin(Client client) throws RemoteException {
        System.out.println("aa");
        users.add(client);
        client.showOnlineUser(users);
    }

    public void collaboratorLogin(Client client) throws RemoteException {
        if (users.size() != 0){
            boolean confirmation = users.get(0).hintWindow(client.getUsername() + " want to share your whiteboard");
            if ( !confirmation ){
                client.hintWindow("you are not permitted.");
                client.exit();
            }else {
                users.add(client);
                for (Client c : users){
                    c.showOnlineUser(users);
                }
            }
        }
    }

    public void quit(Client client) throws RemoteException {
        users.remove(client);
        for (Client c : users){
            c.showOnlineUser(users);
        }
        client.exit();
    }

    public void quit1(String name) throws RemoteException {


        for (Client c : users){
            if (c.getUsername().equals(name)){
                users.remove(c);
                for (Client d : users){
                    d.showOnlineUser(users);
                }
                c.hintWindow("Sorry, you are not VIP, you are kicked out. Bye Bye");
                c.exit();
            }
        }

    }


    public void close(Client client) throws RemoteException {
        users.remove(client);
        for (Client c : users) {
            users.remove(c);
            c.hintWindow("the manager closed this application, you are forced to quit.");
            c.exit();
        }
    }

    public void drawImage(byte[] bytes) throws RemoteException {
        for (Client c : users){
            c.paintImage(bytes);
        }
    }

    public void draw(java.util.List<Integer> pointss, Color color, String command, Client client,boolean flag) throws RemoteException{
        System.out.println("draw before");
        for (Client c : users){
            if (!client.getUsername().equals(c.getUsername())){
            c.paint(pointss,color,command,flag);
            System.out.println("draw after");
            }

        }
    }

}
