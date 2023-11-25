package z5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Kanwa extends JPanel {
    public void resizeSelectedComponent(){
        if(spriteToResize != null){
            spriteToResize.resize(resizeBy);
            resizeBy = 0;
            repaint();
        }
    }
    public void drawAtMouseLocation(MouseEvent e){
        if(sprite instanceof Circle){
            Circle newRing = new Circle(e.getX(), e.getY(), 20, Color.GREEN);
            sprites.add(newRing);
        }
        else if(sprite instanceof Rectangle){
            Rectangle newRect = new Rectangle(e.getX(), e.getY(), 40, 20,Color.ORANGE, true);
            sprites.add(newRect);
        }
        repaint();
    }

    public MouseWheelListener getMouseWheelListener() {
        return mouseWheelListener;
    }

    private final MouseWheelListener mouseWheelListener = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if(e.getWheelRotation() < 0) resizeBy = -resizeSpeed;
            else resizeBy = resizeSpeed;
        }
    };

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse clicked");
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            // Select sprite to animate
            if(spriteToResize == null){
                for(Sprite s : sprites){
                    if(s.contains(x, y)){
                        sprite = null;
                        spriteToResize = s;
                        resizeBy = 0;
                        mainFrame.setTitle("Currently resizing: " + spriteToResize.getClass().getName() + ". To stop, click outside of the sprite.");
                        resizingTimer.start();
                        return;
                    }
                }
            }else{
                if (! spriteToResize.contains(x, y)){
                    mainFrame.setTitle("Currently selected shape: none");
                    spriteToResize = null;
                    resizingTimer.stop();
                }
            }
            drawAtMouseLocation(e);
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("Kanwa entered");
        }
        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("Kanwa exited");

        }
    };

    public KeyListener getKeyListener() {
        return keyListener;
    }

    private final KeyListener keyListener = new KeyListener() {
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
                if (sprite instanceof Circle) sprite = null;
                else{
                    sprite = new Circle();
                    title = "Circle";
                }
            }
            if(e.getKeyChar() == 'r'){
                if (sprite instanceof Rectangle) sprite = null;
                else {
                    sprite = new Rectangle();
                    title = "Rectangle";
                }
            }
            mainFrame.setTitle("Currently selected shape: " + title);
        }
    };
    public Sprite getSprite() {
        return sprite;
    }

    private Sprite sprite = null;
    private Sprite spriteToResize = null;
    private Integer resizeBy = 0;
    private Integer resizeSpeed = 5;
    private Timer resizingTimer =  new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resizeSelectedComponent();
        }
    });
    private BufferedImage kanwaImage;
    private List<Sprite> sprites = new ArrayList<>();
    private JFrame mainFrame;
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    public Kanwa(int width, int height, JFrame mainFrame){
        this.mainFrame = mainFrame;
        setFocusable(true);
        setBackground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setLayout(null);
        setBounds(0, 0, width, height);
        kanwaImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D kanwa2D = (Graphics2D) g.create();
        Graphics2D g2 = kanwaImage.createGraphics();
        try{
            // Create white background
            g2.setPaint(Color.WHITE);
            g2.fillRect(0, 0, kanwaImage.getWidth(), kanwaImage.getHeight());

            // Draw sprites
            for(Sprite s : sprites){{
                if(s instanceof Circle)
                    g2.setColor(((Circle) s).getColor());
                else if(s instanceof Rectangle)
                    g2.setColor(((Rectangle) s).getColor());
                s.draw(g2);
            }}
        } finally {
            kanwa2D.drawImage(kanwaImage, 0, 0, null);
            kanwa2D.dispose();
        }
    }
}