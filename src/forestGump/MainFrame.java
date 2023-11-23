package forestGump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MainFrame extends JFrame {
    private boolean stuck = false;
    private int stuckSpace = 10;
    private int personalSpace = 30;
    private void buttonRun(JComponent component, JComponent parent) {
        System.out.println(stuck);
        if(stuck) return;
        int newX = random.nextInt(0, parent.getWidth() - component.getWidth());
        int newY = random.nextInt(0, parent.getHeight() - component.getHeight());
        component.setLocation(newX, newY);
        if((newX <= stuckSpace || newX+component.getWidth() >= parent.getWidth()-stuckSpace) ||
            newY <= stuckSpace || newY+component.getHeight() >= parent.getHeight()-stuckSpace) stuck = true;
    }
    private JPanel mainPanel = new JPanel();
    private JButton runningButton = new JButton("ZOSTAW MNIE!!!");
    private Random random = new Random();
    private int mouseX;
    private int mouseY;

    private void game(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        int btnX = runningButton.getX();
        int btnY = runningButton.getY();
        int btnW = runningButton.getWidth();
        int btnH = runningButton.getHeight();
        setTitle(mouseX + " " + mouseY + " ::: " + btnX + " " + btnY + " " + (btnX + btnW) + " " + (btnY + btnH));
        if (mouseX >= btnX && mouseX <= btnX + btnW && mouseY >= btnY && mouseY <= btnY + btnH)
            buttonRun(runningButton, mainPanel);

        if ((mouseX >= btnX - personalSpace && mouseX <= btnX + btnW + personalSpace &&
                Math.abs(mouseY - btnY) < personalSpace) ||

                (mouseX >= btnX - personalSpace && mouseX <= btnX + btnW + personalSpace &&
                        Math.abs(mouseY - (btnY + btnH)) < personalSpace) ||

                (Math.abs(mouseX - btnX) < personalSpace &&
                        mouseY >= btnY - personalSpace && mouseY <= btnY + btnH + personalSpace ||

                        (Math.abs(mouseX - (btnX + btnW)) < personalSpace &&
                                mouseY >= btnY - personalSpace && mouseY <= btnY + btnH + personalSpace))) {
            buttonRun(runningButton, mainPanel);
        }
    }

    public MainFrame() throws HeadlessException {
        super("ForrestGump - uciekający przycisk");
        mainPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                game(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                game(e);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        mainPanel.setSize(800, 600);
        mainPanel.setBackground(Color.YELLOW);
        mainPanel.setLayout(null);
        runningButton.setSize(150, 50);

        runningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stuck) JOptionPane.showMessageDialog(mainPanel, "ZŁAPANY !!!");
            }
        });

        buttonRun(runningButton, mainPanel);
        mainPanel.add(runningButton);
        setContentPane(mainPanel);
    }
}