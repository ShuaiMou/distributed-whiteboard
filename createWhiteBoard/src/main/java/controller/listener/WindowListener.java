package controller.listener;

import multiInterface.BoardThread;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

public class WindowListener extends WindowAdapter {
    private JFrame mainFrame;
    public  WindowListener(JFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        Object[] selection = {"Yes", "No"};
        int exit = JOptionPane.showOptionDialog(null, "Exit this window or not",
                "exit dialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, selection, selection[0]);
        if (exit == 0){
            try {
                BoardThread.server.close(BoardThread.client);
            } catch (RemoteException ex) {
                System.out.print("");
            }finally {
                System.exit(0);
//                frame.dispose();
//                ManageMultiInterface.executor.shutdown();
            }
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        super.windowIconified(e);
    }
}
