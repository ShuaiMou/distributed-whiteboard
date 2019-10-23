package view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class CommunicationPanel extends JPanel {
    private @Getter JTextArea communicationWindow;
    private @Getter JTextArea onlineUser;
    private @Getter JTextField messageInput;
    private @Getter JButton send;
    private @Getter JTextArea editingUser;

    CommunicationPanel(){
        init();
    }

    private void init(){
        onlineUser = new JTextArea();
        onlineUser.setLineWrap(true);
        onlineUser.setEditable(false);
        onlineUser.setPreferredSize(new Dimension(150,50));
        onlineUser.append("online users: \n");
        this.add(onlineUser);

        editingUser = new JTextArea();
        editingUser.setLineWrap(true);
        editingUser.setEditable(false);
        editingUser.setPreferredSize(new Dimension(150,50));
        this.add(editingUser);

        communicationWindow = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(communicationWindow);
        scrollPane.setPreferredSize(new Dimension(150,300));
        communicationWindow.setLineWrap(true);
        communicationWindow.setEditable(false);
        this.add(scrollPane);

        messageInput = new JTextField();
        messageInput.setColumns(10);
        messageInput.setPreferredSize(new Dimension(150,30));
        send = new JButton("send");
        send.setPreferredSize(new Dimension(80,30));
        this.add(messageInput);
        this.add(send);

    }
}
