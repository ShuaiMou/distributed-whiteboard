package controller.listener;

import client.RMIClient;
import multiInterface.BoardThread;
import remoteInterface.Communication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CommunicationPanelListener implements ActionListener {
    private JTextField messageInput;
    private RMIClient client;
    private Communication stub;

    public CommunicationPanelListener(JTextField messageInput){
        this.messageInput = messageInput;
        this.client = BoardThread.client;
        try {
            UnicastRemoteObject.exportObject(client,0 );
            stub = (Communication) Naming.lookup("rmi://" + BoardThread.ipAddress +":" + BoardThread.port + "/Communication");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        String message = messageInput.getText();
        messageInput.setText("");
        try {
            stub.sendMessage(client, message);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }


    }
}
