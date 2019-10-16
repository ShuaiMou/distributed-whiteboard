package controller.listener;

import multiInterface.BoardThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class FileProcessListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("quit")){
            try {
                BoardThread.server.quit(BoardThread.client);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }else if (e.getActionCommand().equals("join room") || e.getActionCommand().equals("instructions")
                || e.getActionCommand().equals("about it") ){
            Object[] selection = {"OK"};
            JOptionPane.showOptionDialog(null, "this function is developing... ",
                    "Warning",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, selection, null);
        }else {
            Object[] selection = {"OK"};
            JOptionPane.showOptionDialog(null, "only manager can use this function",
                    "Warning",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, selection, null);
        }
    }

}
