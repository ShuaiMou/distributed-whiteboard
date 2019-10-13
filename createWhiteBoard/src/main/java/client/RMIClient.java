package client;

import lombok.Setter;
import remoteInterface.Client;

import javax.swing.*;
import java.rmi.RemoteException;

@Setter
public class RMIClient implements Client {
    private String name;
    private boolean  permission;
    private JTextArea communicationWindow;


    public void showMessage(String message) throws RemoteException {
        communicationWindow.append(message);
    }

    public String getUsername() throws RemoteException {
        return this.name;
    }

    public boolean hintWindow(String message) throws RemoteException{
        Object[] selection = {"Yes", "No"};
        int exit = JOptionPane.showOptionDialog(null, message,
                "exit dialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, selection, selection[0]);
        if (exit == 0){
            return true;
        }else {
            return false;
        }
    }

}
