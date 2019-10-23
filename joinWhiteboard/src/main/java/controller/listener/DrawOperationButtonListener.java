package controller.listener;

import lombok.Getter;
import lombok.Setter;
import multiInterface.BoardThread;
import view.DrawPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;

@Setter @Getter
public class DrawOperationButtonListener extends MouseAdapter implements ActionListener {

    private String drawOperationCommond;
    private String input1;
    private DrawPanel drawPanel;

    public DrawOperationButtonListener(DrawPanel drawPanel){
        this.drawPanel = drawPanel;
        drawOperationCommond = "init";
    }

    /**
     * get the command which denotes what shape will be drawed.
     *
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("line")){
            drawOperationCommond = "line";
        }else if (e.getActionCommand().equals("circle")){
            drawOperationCommond = "circle";
        }else if (e.getActionCommand().equals("rectangle")){
            drawOperationCommond = "rectangle";
        }else if (e.getActionCommand().equals("oval")){
            drawOperationCommond = "oval";
        }else if (e.getActionCommand().equals("freehand")){
            drawOperationCommond = "freehand";
        }else if (e.getActionCommand().equals("small eraser")){
            drawOperationCommond = "small eraser";
        }else if (e.getActionCommand().equals("middle eraser")){
            drawOperationCommond = "middle eraser";
        }else if (e.getActionCommand().equals("big eraser")) {
            drawOperationCommond = "big eraser";
        }else if (e.getActionCommand().equals("open")) {
            drawOperationCommond = "open";
        }else if (e.getActionCommand().equals("clear")) {
            try {
                BoardThread.server.clearWhiteboard();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }else if (e.getActionCommand().equals("text")){

            input1 = JOptionPane.showInputDialog("input text");
            drawOperationCommond = "text";
        }else {
            drawOperationCommond = "init";
        }
    }

}
