package z1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException{
        super("Canva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JTextField location = new JTextField("Click on canva to write location");
        location.setBounds(400-600/2, 0, 600, 50);
        mainPanel.add(location);

        Kanwa kanwa = new Kanwa();
        kanwa.setBounds(400-600/2, 50, 600, 500);
        mainPanel.add(kanwa);
        kanwa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                location.setText("X: " + e.getX() + " Y: " + e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
