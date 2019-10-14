package controller.listener;

import multiInterface.BoardThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class CommunicationPanelListener implements ActionListener {
    private JTextField messageInput;


    public CommunicationPanelListener(JTextField messageInput){
        this.messageInput = messageInput;


    }

    public void actionPerformed(ActionEvent e) {
        String message = messageInput.getText();
        messageInput.setText("");
        try {
            BoardThread.server.sendMessage(BoardThread.client, message);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }


    }
}
