package controller.listener;

import WhiteboardUtil.Point;
import lombok.Getter;
import view.DrawPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
public class DrawPanelListener extends MouseAdapter implements MouseListener {
    private Point startPoint;
    private Point endPoint;
    private Point dragEndPoint;
    private boolean flag;
    private DrawPanel drawPanel;
    private DrawOperationButtonListener drawOperationButtonListener;
    private String command;
    public DrawPanelListener(DrawPanel drawPanel, DrawOperationButtonListener drawOperationButtonListener){
        this.drawOperationButtonListener = drawOperationButtonListener;
        this.drawPanel = drawPanel;
    }

    public void mouseClicked(MouseEvent e) {


    }

    public void mousePressed(MouseEvent e) {
        startPoint = endPoint = new Point(e.getX(), e.getY());
        flag = true;
        command = drawOperationButtonListener.getDrawOperationCommond();

    }

    public void mouseReleased(MouseEvent e) {
        endPoint = new Point(e.getX(), e.getY());
        flag = false;
        drawPanel.repaint();
    }

    public void mouseDragged(MouseEvent e) {

        dragEndPoint = new Point(e.getX(), e.getY());
        if("freehand".equals(command) || "small eraser".equals(command)  || "middle eraser".equals(command)  || "big eraser".equals(command)) {
            startPoint = endPoint;
            endPoint = new Point(e.getX(), e.getY());
        }
        drawPanel.repaint();
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}