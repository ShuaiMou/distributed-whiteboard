package view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {

    private @Getter JButton[] colorButtons;

    ColorPanel() {

        init();
    }

    /**
     * load the color button add listener in the controller
     */
    private void init() {

        colorButtons = new JButton[16];
        Color[] colors = {Color.BLACK, Color.WHITE, Color.lightGray, Color.GRAY,
                          Color.darkGray, Color.RED, Color.PINK, Color.ORANGE,
                          Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.CYAN,
                          Color.BLUE,new Color(130, 130, 130),
                          new Color(25, 66, 144),
                          new Color(65, 15, 244)};
        this.setLayout(new GridLayout(2, 8));
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton();
            colorButtons[i].setPreferredSize(new Dimension(35,45));
            colorButtons[i].setOpaque(true); // foreground display nothing
            colorButtons[i].setBorderPainted(false); // hide border
            colorButtons[i].setBackground(colors[i]);
            this.add(colorButtons[i]);
        }

    }
}
