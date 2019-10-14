package controller;

import controller.listener.*;
import lombok.Getter;
import lombok.Setter;
import multiInterface.BoardThread;
import view.View;

import javax.swing.*;
import java.util.ArrayList;

@Setter@Getter
public class Controller {
    private View view;
    private DrawOperationButtonListener drawOperationButtonListener;
    private ColorButtonListener colorButtonListener;
    private DrawPanelListener drawPanelListener;
    private FileProcessListener fileProcessListener;
    private WindowListener windowListener;
    private CommunicationPanelListener communicationPanelListener;

    public Controller(View view){
        this.view = view;
        colorButtonListener = new ColorButtonListener();
        drawOperationButtonListener = new DrawOperationButtonListener(view.getDrawPanel());
        drawPanelListener = new DrawPanelListener(view.getDrawPanel(), drawOperationButtonListener);
        fileProcessListener = new FileProcessListener(this.view.getDrawPanel(), this.view.getMainFrame());
        windowListener = new WindowListener(view.getMainFrame());
        communicationPanelListener = new CommunicationPanelListener(view.getCommunicationPanel().getMessageInput());
        init();
    }

    private void init(){
        JButton[] shapeButtons = view.getOperationPanel().getDrawOperationButtons();
        JButton[] colorButtons = view.getColorPanel().getColorButtons();
        ArrayList<JMenuItem>  menuItems = this.view.getMainFrame().getMenuItems();
        for (JButton shapeButton : shapeButtons) {
            shapeButton.addActionListener(drawOperationButtonListener);
        }

        for (JButton colorButton : colorButtons) {
            colorButton.addActionListener(colorButtonListener);
        }

        for(JMenuItem menuItem : menuItems){
            menuItem.addActionListener(fileProcessListener);
        }

        view.getDrawPanel().addMouseListener(drawPanelListener);
        view.getDrawPanel().addMouseMotionListener(drawPanelListener);
        view.getDrawPanel().setColorButtonListener(colorButtonListener);
        view.getDrawPanel().setDrawOperationButtonListener(drawOperationButtonListener);
        view.getDrawPanel().setDrawPanelListener(drawPanelListener);
        view.getMainFrame().addWindowListener(windowListener);
        view.getCommunicationPanel().getSend().addActionListener(communicationPanelListener);
        BoardThread.client.setCommunicationWindow(view.getCommunicationPanel().getCommunicationWindow());
        BoardThread.client.setOnlineUser(view.getCommunicationPanel().getOnlineUser());
        BoardThread.client.setDrawPanel(view.getDrawPanel());
    }

}
