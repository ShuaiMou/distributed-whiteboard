package view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class OperationPanel extends JPanel {

    private @Getter JButton[] drawOperationButtons;

    OperationPanel(){
        init();
    }

    /**
     * 1.load the operation button such as line, oval, eraser. and add it to drawOperationButtons.
     * 2. add attributes of this panel, such as layout.
     * add button listener in the controller
     */
    private void init(){
        drawOperationButtons = new JButton[10];
        String[] buttons = {"line", "circle", "rectangle", "oval", "freehand", "small eraser", "middle eraser", "big eraser","clear","text"};
        for (int i = 0; i < buttons.length; i++) {
            drawOperationButtons[i] = new JButton(buttons[i]);
            drawOperationButtons[i].setPreferredSize(new Dimension(100,40));
            this.add(drawOperationButtons[i]);
        }
    }

}
