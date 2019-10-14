package controller.listener;

import lombok.Setter;
import multiInterface.BoardThread;
import view.DrawPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class FileProcessListener implements ActionListener {

    private JFrame frame;
    private @Setter String filePath;
    private DrawPanel drawPanel;

    public FileProcessListener(DrawPanel drawPanel, JFrame frame) {
        this.drawPanel = drawPanel;
        this.frame = frame;
    }


    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("new")) {
//            newFile();
//        } else if (e.getActionCommand().equals("open")) {
//            openFile();
//        } else if (e.getActionCommand().equals("save")) {
//            saveFile();
//        } else if (e.getActionCommand().equals("save as")) {
//            saveAsFile();
//        } else if (e.getActionCommand().equals("close")) {
//            closeFile();
//        }
        if (e.getActionCommand().equals("quit")){
            try {
                BoardThread.server.quit(BoardThread.client);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

}
