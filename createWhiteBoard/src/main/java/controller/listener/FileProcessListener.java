package controller.listener;

import lombok.Setter;
import multiInterface.BoardThread;
import view.DrawPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class FileProcessListener implements ActionListener {

    private JFrame frame;
    private @Setter String filePath;
    private DrawPanel drawPanel;
    private String[] options ;

    public FileProcessListener(DrawPanel drawPanel, JFrame frame) {
        this.drawPanel = drawPanel;
        this.frame = frame;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("new")) {
            newFile();
        } else if (e.getActionCommand().equals("open")) {
            openFile();
        } else if (e.getActionCommand().equals("save")) {
            saveFile();
        } else if (e.getActionCommand().equals("save as")) {
            saveAsFile();
        } else if (e.getActionCommand().equals("close")) {
            closeFile();
        } else if (e.getActionCommand().equals("kick out")) {
           kickOut();
        }else {
            Object[] selection = {"OK"};
            JOptionPane.showOptionDialog(null, "this function is developing... ",
                    "Warning",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, selection, null);
        }
    }

    private void kickOut(){
        try {

            List<String> names = BoardThread.server.getUsersName(BoardThread.client);
            String [] options = names.toArray(new String[names.size()]);

            String s = (String) JOptionPane.showInputDialog(null,"please select user:\n", "Kick out", JOptionPane.PLAIN_MESSAGE, null,options, "...");
            if (s!=null){
                BoardThread.server.kickOut(s);
            }

        } catch (RemoteException ex) {
            System.out.println("");
        }
    }


    private void newFile() {
//        BoardThread thread = new BoardThread();
//        ManageMultiInterface.executor.execute(thread);
        try {
            BoardThread.server.clearWhiteboard();
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }


    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            readFile(file);
            setFilePath(file.getPath());
            String path = file.getPath().substring(0, file.getPath().length() - 4);
            setFilePath(path);
        }
    }


    private void saveFile() {
        writeFile(filePath);
    }

    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            writeFile(file.getPath());
        }
    }


    private void closeFile() {
        Object[] selection = {"Yes", "No"};
        int exit = JOptionPane.showOptionDialog(null, "Exit the system or not",
                "exit dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, selection, selection[0]);
        if (exit == 0) {
            try {
                BoardThread.server.close(BoardThread.client);
            } catch (RemoteException e) {
                System.out.print("");
            }finally {
                System.exit(0);
//                frame.dispose();
//                ManageMultiInterface.executor.shutdown();
            }

        }
    }


    private void readFile(File file) {
        try {
            BufferedImage img = ImageIO.read(file);
            drawPanel.setImage(img);
            drawPanel.repaint();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( img, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            BoardThread.server.drawImage(imageInByte);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeFile(String savepath) {

        if (savepath == null) {
            savepath = "image";
        }
        try {
            BufferedImage myImage = drawPanel.getImage();
            ImageIO.write(myImage, "jpg", new File(savepath + ".jpg"));
        } catch (Exception e) {
            System.out.println("There is a problem with saving the file.");
        }
    }
}
