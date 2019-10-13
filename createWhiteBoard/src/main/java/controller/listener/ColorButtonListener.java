package controller.listener;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButtonListener implements ActionListener {
    private @Getter Color color = Color.BLACK;

    /**
     * get the color of the clicked button
     *
     * @param e MouseEvent
     */

    public void actionPerformed(ActionEvent e) {
        color = ((JButton) e.getSource()).getBackground();
    }
}
