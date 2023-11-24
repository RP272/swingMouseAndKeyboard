package z3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String title = "none";
                if(e.getKeyChar() == 'c') {
                    if (kanwa.getSprite() instanceof Circle) kanwa.setSprite(null);
                    else{
                        kanwa.setSprite(new Circle());
                        title = "Circle";
                    }
                }
                if(e.getKeyChar() == 'r'){
                    if (kanwa.getSprite() instanceof Rectangle) kanwa.setSprite(null);
                    else {
                        kanwa.setSprite(new Rectangle());
                        title = "Rectangle";
                    }
                }
                setTitle("Currently selected shape: " + title);
            }
        });
        kanwa.addMouseMotionListener(kanwa.getMotionListener());
        kanwa.addMouseListener(kanwa.getMouseListener());
    }
}
