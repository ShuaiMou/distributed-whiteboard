package controller.listener;

import client.RMIClient;
import lombok.Setter;
import multiInterface.BoardThread;
import multiInterface.ManageMultiInterface;
import view.DrawPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

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
            try {

                String [] options = RMIClient.nameList.toArray(new String[RMIClient.nameList.size()]);

                String s = (String) JOptionPane.showInputDialog(null,"please select user:\n", "Kick out", JOptionPane.PLAIN_MESSAGE, new ImageIcon("xx.png"), options, "xx");

                BoardThread.server.quit1(s);
                System.out.println(s);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }


        }
    }


    private void newFile() {
        BoardThread thread = new BoardThread();
        ManageMultiInterface.executor.execute(thread);

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
                e.printStackTrace();
            }finally {
                frame.dispose();
                ManageMultiInterface.executor.shutdown();
            }

        }
    }


    private void readFile(File file) {
//        BufferedReader reader = null;
//        String input = "";
//        try {
//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
//            reader = new BufferedReader(inputStreamReader);
//            String temp;
//            while ((temp = reader.readLine()) != null) {
//                input = input.concat(temp);
//            }
//            pictureReaded = gson.fromJson(input, PictureQueue.class);
//
//
//        } catch (Exception e) {
//            System.out.println("There is a problem with opening the file.");
//        } finally {
//            Util.closeInputStream(reader);
//            }
        try {
            BufferedImage img = ImageIO.read(file);
            drawPanel.setImage(img);
            drawPanel.repaint();
            //g.drawImage(img, 0, 0, drawPanel.getWidth(), drawPanel.getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeFile(String savepath) {
//        BufferedWriter writer = null;
//        String content;

//            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savepath, false), "UTF-8"));
//            content = gson.toJson(picture);
//            writer.write(content);
//            writer.close();

        if (savepath == null) {
            savepath = "image";
        }
        try {
            Point location = frame.getLocation();
            int x = (int) location.getX();
            int y = (int) location.getY();

            Dimension size = drawPanel.getSize();
            int width = (int) size.getWidth();
            int height = (int) size.getHeight();

//            System.out.println("x   "+x);
//            System.out.println("y   "+y);
//            System.out.println("w   "+width);
//            System.out.println("h   "+height);


            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.translate(x, y);
            drawPanel.paint(g);
            ImageIO.write(image, "jpeg", new File("drawpanelImage.jpg"));
            BufferedImage myImage;
            myImage = new Robot().createScreenCapture(
                    new Rectangle(x + 100, y + 44, width, height));
            ImageIO.write(myImage, "jpg", new File(savepath + ".jpg"));
        } catch (Exception e) {
            System.out.println("There is a problem with saving the file.");
        }
    }
}
