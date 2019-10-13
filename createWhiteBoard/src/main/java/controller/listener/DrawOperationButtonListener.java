package controller.listener;

import lombok.Getter;
import view.DrawPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawOperationButtonListener implements ActionListener {

    private @Getter String drawOperationCommond;
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
            drawOperationCommond = "clear";
            drawPanel.repaint();
        }else {
            drawOperationCommond = "init";
        }
    }

}
