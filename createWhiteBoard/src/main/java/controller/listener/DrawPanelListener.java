package controller.listener;

import WhiteboardUtil.Point;
import lombok.Getter;
import lombok.Setter;
import multiInterface.BoardThread;
import view.DrawPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DrawPanelListener extends MouseAdapter implements MouseListener {
    private Point startPoint;
    private Point endPoint;
    private Point dragEndPoint;
    private boolean flag;
    private DrawPanel drawPanel;
    private DrawOperationButtonListener drawOperationButtonListener;
    private ColorButtonListener colorButtonListener;
    private String command;
    public DrawPanelListener(DrawPanel drawPanel, DrawOperationButtonListener drawOperationButtonListener){
        this.drawOperationButtonListener = drawOperationButtonListener;
        this.drawPanel = drawPanel;
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
        List<Integer> pointss = new ArrayList<Integer>(6);
        pointss.add(startPoint.getX());
        pointss.add(startPoint.getY());
        pointss.add(endPoint.getX());
        pointss.add(endPoint.getY());
        pointss.add(0);
        pointss.add(0);
        try {
            BoardThread.server.draw(pointss,colorButtonListener.getColor(),command,BoardThread.client,flag);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mouseDragged(MouseEvent e) {

        dragEndPoint = new Point(e.getX(), e.getY());
        if("freehand".equals(command) || "small eraser".equals(command)  || "middle eraser".equals(command)  || "big eraser".equals(command)) {
            startPoint = endPoint;
            endPoint = new Point(e.getX(), e.getY());
        }
        drawPanel.repaint();
        List<Integer> pointss = new ArrayList<Integer>(6);
        pointss.add(startPoint.getX());
        pointss.add(startPoint.getY());
        pointss.add(endPoint.getX());
        pointss.add(endPoint.getY());
        pointss.add(dragEndPoint.getX());
        pointss.add(dragEndPoint.getY());
        try {
            BoardThread.server.draw(pointss,colorButtonListener.getColor(),command,BoardThread.client,flag);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}