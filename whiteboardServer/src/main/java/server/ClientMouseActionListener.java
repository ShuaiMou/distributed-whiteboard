package server;

import remoteInterface.Client;
import remoteInterface.Communication;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class ClientMouseActionListener implements Runnable{
    private Communication server;
    ClientMouseActionListener(Communication server){
        this.server = server;
    }

    public void run() {
        while (true) {
            Hashtable cachedCommands = ServerImpl.cachedCommands;
            ArrayList<Client> clients = null;

            try {
                clients = server.getUsers();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (clients != null) {
                for (Client client : clients) {
                    try {
                        if (!client.isRunningStatus()) {
                            LinkedList<Object[]> list = (LinkedList<Object[]>) cachedCommands.get(client);
                            if (list != null && list.size() > 0) {
                                Object[] commands = list.poll();
                                java.util.List<Integer> pointss = (java.util.List<Integer>) commands[0];
                                Color color = (Color) commands[1];
                                String command = (String) commands[2];
                                boolean flag = (Boolean) commands[3];
                                String input = (String) commands[4];
                                System.out.println(client.getUsername() + ": " + command);
                                if(!("freehand".equals(command) || "small eraser".equals(command)
                                        || "middle eraser".equals(command)  || "big eraser".equals(command))) {
                                    try {
                                        Thread.currentThread().sleep(250);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                server.draw(pointss, color, command, client, flag, input);
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
