package z5;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException{
        super("Kanwa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        Kanwa kanwa = new Kanwa(800, 600, this);
        mainPanel.add(kanwa);
        kanwa.addMouseWheelListener(kanwa.getMouseWheelListener());
        kanwa.addKeyListener(kanwa.getKeyListener());
        kanwa.addMouseListener(kanwa.getMouseListener());
    }
}
