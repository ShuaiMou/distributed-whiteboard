package client;

import WhiteboardUtil.Point;
import controller.listener.ColorButtonListener;
import controller.listener.DrawPanelListener;
import lombok.Setter;
import remoteInterface.Client;
import view.DrawPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
@Setter
public class RMIClient implements Client {
    private String name;
    private JTextArea communicationWindow;
    private JTextArea onlineUser;
    private DrawPanel drawPanel;
    private DrawPanelListener drawPanelListener;
    private ColorButtonListener colorButtonListener;

    public void showMessage(String message) throws RemoteException {
        communicationWindow.append(message);
    }

    public String getUsername() throws RemoteException {
        return this.name;
    }

    public boolean hintWindow(String message) throws RemoteException{
        Object[] selection = {"Yes", "No"};
        int exit = JOptionPane.showOptionDialog(communicationWindow, message,
                "exit dialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, selection, selection[0]);
        return exit == 0;
    }

    public void exit() throws RemoteException {
        System.exit(0);
    }

    public void showOnlineUser(List<Client> clients) throws RemoteException {
        onlineUser.setText("");
        onlineUser.append("online users: \n");

        int i = 0;
        for (Client client : clients){
            onlineUser.append(client.getUsername() + ",");
            i ++;
            if ( i % 3 == 0){
                onlineUser.append("\n");
            }
        }
    }


    public void paintImage(byte[] bytes) throws IOException {
        BufferedImage image = ImageIO.read( new ByteArrayInputStream(bytes));
        drawPanel.setImage(image);
        drawPanel.repaint();
    }

    public void paint(List<Integer> pointss, Color color, String command, boolean flag,String input) throws RemoteException {
        String preCommand = drawPanelListener.getDrawOperationButtonListener().getDrawOperationCommond();
        String preInput = drawPanelListener.getDrawOperationButtonListener().getInput1();
        Color preColor = colorButtonListener.getColor();

        drawPanelListener.getDrawOperationButtonListener().setDrawOperationCommond(command);
        drawPanelListener.getDrawOperationButtonListener().setInput1(input);
        drawPanelListener.setStartPoint(new Point(pointss.get(0), pointss.get(1)));
        drawPanelListener.setEndPoint(new Point(pointss.get(2), pointss.get(3)));
        drawPanelListener.setDragEndPoint(new Point(pointss.get(4), pointss.get(5)));
        drawPanelListener.setFlag(flag);
        colorButtonListener.setColor(color);
        drawPanel.repaint();
        try {
            Thread.currentThread().sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (!"clear".equals(preCommand)) {
            drawPanelListener.getDrawOperationButtonListener().setDrawOperationCommond(preCommand);
        }
        drawPanelListener.getDrawOperationButtonListener().setInput1(preInput);
        colorButtonListener.setColor(preColor);



    }

    public byte[] getImage() throws RemoteException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte = null;
        try {
            ImageIO.write( drawPanel.getImage(), "jpg", baos );
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageInByte;
    }

    public void clearWhiteboard() throws RemoteException {
        drawPanelListener.getDrawOperationButtonListener().setDrawOperationCommond("clear");
        drawPanel.repaint();
    }

    public boolean isRunningStatus() throws RemoteException {
        return drawPanelListener.isRunningStatus();
    }
}
