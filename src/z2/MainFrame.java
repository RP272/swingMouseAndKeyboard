package z2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException{
        super("Kanwa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        Kanwa kanwa = new Kanwa(800, 600);
        mainPanel.add(kanwa);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == 'c') {
                    kanwa.setSelectionImage(null);
                    kanwa.setSelectionRectangle(null);
                    if (kanwa.getSprite() != null) kanwa.setSprite(null);
                    else kanwa.setSprite(new Circle());
                }
            }
        });
        kanwa.addMouseMotionListener(kanwa.getMotionListener());
        kanwa.addMouseListener(kanwa.getMouseListener());
    }
}
