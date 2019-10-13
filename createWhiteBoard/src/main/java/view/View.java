package view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class View {
    private MainFrame mainFrame;
    private ColorPanel colorPanel;
    private OperationPanel operationPanel;
    private DrawPanel drawPanel;
    private CommunicationPanel communicationPanel;

    public View(){
        mainFrame = new MainFrame();
        colorPanel = new ColorPanel();
        operationPanel = new OperationPanel();
        drawPanel = new DrawPanel();
        communicationPanel = new CommunicationPanel();
        mainFrame.setTitle("Shared Whiteboard");
        showFrame();
    }

    /**
     * init the whole interface
     */
    private void showFrame(){
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.setBackground(Color.gray);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(100,100);
        mainFrame.setLayout(new BorderLayout());

        operationPanel.setPreferredSize(new Dimension(100, 500));
        operationPanel.setBackground(Color.gray);
        mainFrame.add(operationPanel, BorderLayout.WEST);

        drawPanel.setBackground(Color.WHITE);
        mainFrame.add(drawPanel, BorderLayout.CENTER);

        communicationPanel.setPreferredSize(new Dimension(150,500));
        communicationPanel.setBackground(Color.gray);
        mainFrame.add(communicationPanel, BorderLayout.EAST);

        colorPanel.setPreferredSize(new Dimension(600, 50));
        colorPanel.setBackground(Color.WHITE);

        mainFrame.add(colorPanel, BorderLayout.SOUTH);


        mainFrame.setVisible(true);
        mainFrame.pack();
    }
}
