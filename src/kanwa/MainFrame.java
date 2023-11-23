package kanwa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public void drawAtMouseLocation(MouseEvent e, Kanwa kanwa){
        if(kanwa.getSprite() == null) return;
        if(kanwa.getSprite() instanceof Circle){
            Circle newRing = new Circle(e.getX(), e.getY(), Color.GREEN);
            kanwa.getSprites().add(newRing);
        }
        if(kanwa.getSprite() instanceof Rectangle){
            Rectangle newRectangle = new Rectangle(e.getX(), e.getY(), Color.ORANGE);
            kanwa.getSprites().add(newRectangle);
        }
        kanwa.repaint();
    }
    public MainFrame() throws HeadlessException{
        super("Kanwa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        Kanwa kanwa = new Kanwa();
        kanwa.setBounds(0, 50, 800, 600);
        mainPanel.add(kanwa);

        JButton clearButton = new JButton("Wyczyść kanwę");
        clearButton.setFocusable(false);
        clearButton.setBounds(400 - 75, 0, 150, 50);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainPanel.getParent(), "Zawartość ekranu zostanie usunięta. Czy na pewno chcesz wyczyścić kanwę?");
                if(result == 0){
                    kanwa.getSprites().clear();
                    kanwa.repaint();
                }
            }
        });
        mainPanel.add(clearButton);


        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == 'c') kanwa.setSprite(new Circle());
                if(e.getKeyChar() == 'r') kanwa.setSprite(new Rectangle());
            }
        });

        kanwa.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawAtMouseLocation(e, kanwa);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        kanwa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawAtMouseLocation(e, kanwa);
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
