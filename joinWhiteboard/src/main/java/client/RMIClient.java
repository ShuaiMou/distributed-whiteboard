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
        if (exit == 0){
            return true;
        }else {
            return false;
        }
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
            i++;
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

    public void paint(java.util.List<Integer> pointss, Color color, String command,boolean flag) throws RemoteException {
        drawPanelListener.getDrawOperationButtonListener().setDrawOperationCommond(command);
        drawPanelListener.setStartPoint(new Point(pointss.get(0), pointss.get(1)));
        drawPanelListener.setEndPoint(new Point(pointss.get(2), pointss.get(3)));
        drawPanelListener.setDragEndPoint(new Point(pointss.get(4), pointss.get(5)));
        drawPanelListener.setFlag(flag);
        colorButtonListener.setColor(color);
        drawPanel.repaint();
    }
}
