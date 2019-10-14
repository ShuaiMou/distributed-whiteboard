package view;

import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    @Getter
    private ArrayList<JMenuItem> menuItems;

     MainFrame(){
        menuItems = new ArrayList<JMenuItem>(9);
        init();
    }

    /**
     * the menue interface
     */
    private void init(){
        JMenuBar jmb = createMenuBar();
        this.setJMenuBar(jmb);
    }

    /**
     *
     *
     * @return JMenuBar object
     */
    private JMenuBar createMenuBar() {
        JMenuBar jmb = new JMenuBar();
        String[] arrayMenu = { "file", "join room","disconnect", "help"};
        String[][] arrayMenuItem = { { "new", "open", "save", "save as", "close"}, { "join room" },
                {"quit"}, { "instructions", "about it" } };
        for (int i = 0; i < arrayMenu.length; i++) {
            JMenu menu = new JMenu(arrayMenu[i]);
            jmb.add(menu);
            for (int j = 0; j < arrayMenuItem[i].length; j++) {
                JMenuItem jmi = new JMenuItem(arrayMenuItem[i][j]);
                menu.add(jmi);
                menuItems.add(jmi);
                if (j < arrayMenuItem[i].length - 1){
                    menu.addSeparator();
                }
            }
        }
        return jmb;
    }


}
