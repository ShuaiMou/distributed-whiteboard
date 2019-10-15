package controller.listener;

import multiInterface.BoardThread;

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
        }
    }

}
