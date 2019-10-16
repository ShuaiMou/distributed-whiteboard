package view;

import controller.listener.ColorButtonListener;
import controller.listener.DrawOperationButtonListener;
import controller.listener.DrawPanelListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Setter
public class DrawPanel extends JScrollPane  {
    private ColorButtonListener colorButtonListener;
    private DrawOperationButtonListener drawOperationButtonListener;
    private DrawPanelListener drawPanelListener;
    private @Getter BufferedImage image;
    private String command;
    private Color color;
    private String input;

    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;
    private int x3 = 0;
    private int y3 = 0;
    private boolean fl = true;

    DrawPanel(){
        image = new BufferedImage(1200, 1200, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().fillRect(0, 0, 1200, 1200);
        command = " ";
        color = Color.black;

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (drawOperationButtonListener != null){
            command = drawOperationButtonListener.getDrawOperationCommond();
        }
        if (colorButtonListener != null){
            color = colorButtonListener.getColor();
        }

        Graphics2D g2d = (Graphics2D) g;
        Graphics2D bg = image.createGraphics();
        bg.setColor(color);
        bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 抗锯齿
        g2d.drawImage(image, 0, 0, this);
        g2d.setColor(color);

        if(drawPanelListener != null && !"init".equals(command)){

            x1 = drawPanelListener.getStartPoint().getX();
            y1 = drawPanelListener.getStartPoint().getY();
            x2 = drawPanelListener.getEndPoint().getX();
            y2 = drawPanelListener.getEndPoint().getY();
            x3 = drawPanelListener.getDragEndPoint().getX();
            y3 = drawPanelListener.getDragEndPoint().getY();
            fl = drawPanelListener.isFlag();
            input = drawOperationButtonListener.getInput1();
        }

        if ("line".equals(command)){
            bg.drawLine(x1,y1,x2,y2);
            g2d.drawLine(x1,y1,x3,y3);
        }else if("circle".equals(command)){
            bg.drawOval(Math.min(x1, x2), Math.min(y1, y2),
                    Math.abs(x1 - x2), Math.abs(x1 - x2));
            g2d.drawOval(Math.min(x1, x3), Math.min(y1, y3), Math.abs(x1 - x3),
                    Math.abs(x1 - x3));
        }else if("rectangle".equals(command)){
            bg.drawRect(Math.min(x1, x2), Math.min(y1, y2),
                    Math.abs(x1 - x2), Math.abs(y1 - y2));
            g2d.drawRect(Math.min(x1, x3), Math.min(y1, y3), Math.abs(x1 - x3),
                    Math.abs(y1 - y3));
        }else if("oval".equals(command)){
            bg.drawOval(Math.min(x1, x2), Math.min(y1, y2),
                    Math.abs(x1 - x2), Math.abs(y1 - y2));
            g2d.drawOval(Math.min(x1, x3), Math.min(y1, y3), Math.abs(x1 - x3),
                    Math.abs(y1 - y3));
        }else if("small eraser".equals(command)){
            bg.setColor(Color.WHITE);
            g2d.setColor(Color.WHITE);
            bg.setStroke(new BasicStroke(10));
            bg.drawLine(x1,y1,x2,y2);

        }else if("middle eraser".equals(command)) {
            bg.setColor(Color.WHITE);
            g2d.setColor(Color.WHITE);
            bg.setStroke(new BasicStroke(30));
            bg.drawLine(x1, y1, x2, y2);
        }else if("big eraser".equals(command)) {
            bg.setColor(Color.WHITE);
            g2d.setColor(Color.WHITE);
            bg.setStroke(new BasicStroke(50));
            bg.drawLine(x1, y1, x2, y2);
        }
        else if ("freehand".equals(command)){
            bg.drawLine(x1,y1,x2,y2);
            g2d.drawLine(x1,y1,x2,y2);
        }else if ("clear".equals(command)){
            image.getGraphics().fillRect(0, 0, this.getWidth(), this.getHeight());
        }else if ("text".equals(command)){

            if (input != null) {
                x3 = 0;
                y3 = 0;
                bg.drawString(input, x1, y1);
                g2d.drawString(input, x1, y1);
            }

        }

        if(fl==false){
            g2d.drawImage(image, 0, 0, this);

        }
    }


}
