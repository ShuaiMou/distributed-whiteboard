package server;

import remoteInterface.Client;
import remoteInterface.Communication;

import java.awt.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class ServerImpl extends UnicastRemoteObject implements Communication {

    public static List<Client> users;
    public static Hashtable<Client, LinkedList<Object[]>> cachedCommands;

    public ServerImpl() throws RemoteException{
        users = new ArrayList<Client>(10);
        cachedCommands = new Hashtable<Client,LinkedList<Object[]>>();
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

    public synchronized void managerLogin(Client client) throws RemoteException {
        users.add(client);
        LinkedList<Object[]> linkedList = new LinkedList<Object[]>();
        cachedCommands.put(client, linkedList);
        client.showOnlineUser(users);
    }

    public synchronized void collaboratorLogin(Client client) throws IOException {
        for (Client c : users){
            if (client.getUsername().equals(c.getUsername())){
                client.hintWindow("your username is duplicate with the exist user," +
                        "\n please change another username and login again.");
                client.exit();
            }
        }

        if (users.size() != 0){
            boolean confirmation = users.get(0).hintWindow(client.getUsername() +
                    " want to share your whiteboard");
            if ( !confirmation ){
                client.hintWindow("you are not permitted.");
                client.exit();
            }else {
                users.add(client);
                LinkedList<Object[]> linkedList = new LinkedList<Object[]>();
                cachedCommands.put(client, linkedList);
                for (Client c : users){
                    c.showOnlineUser(users);
                }
                client.paintImage(users.get(0).getImage());
            }
        }
    }

    public synchronized void quit(Client client) throws RemoteException {
        users.remove(client);
        cachedCommands.remove(client);
        for (Client c : users){
            c.showOnlineUser(users);
        }
        client.exit();
    }

    public synchronized void kickOut(String name) throws RemoteException {
        for (Client c : users){
            if (c.getUsername().equals(name)){
                users.remove(c);
                cachedCommands.remove(c);
                for (Client d : users){
                    d.showOnlineUser(users);
                }
                c.hintWindow("Sorry, you are not VIP, you are kicked out. Bye Bye");
                c.exit();
            }
        }

    }


    public synchronized void close(Client client) throws RemoteException {
        users.remove(client);
        for (Client c : users) {
            users.remove(c);
            cachedCommands.remove(c);
            c.hintWindow("the manager closed this application, you are forced to quit.");
            c.exit();
        }
    }

    public void drawImage(byte[] bytes) throws IOException {
        for (Client c : users){
            c.paintImage(bytes);
        }
    }

    public void draw(java.util.List<Integer> pointss, Color color, String command, Client client,boolean flag,String input) throws RemoteException{

        client.paint(pointss, color, command, flag, input);

    }

    public void addCommands(List<Integer> pointss, Color color, String command, Client client, boolean flag, String input) throws RemoteException {
        for (Client c : users){
            if (!client.getUsername().equals(c.getUsername()) ){
                cachedCommands.get(c).add(new Object[]{pointss,color,command,flag,input});
            }
        }


    }

    public List<String> getUsersName(Client client) throws RemoteException {
        List<String> names = new ArrayList<String>(10);
        for (Client c : users){
            if (!c.getUsername().equals(client.getUsername())){
                names.add(c.getUsername());
            }
        }
        return names;
    }

    public void clearWhiteboard() throws RemoteException {
        for (Client c : users){
            c.clearWhiteboard();
        }
    }

    public synchronized ArrayList<Client> getUsers(){
        ArrayList<Client> userReturn = new ArrayList<Client>();
        for (Client c : users){
            userReturn.add(c);
        }
        return userReturn;
    }
}
