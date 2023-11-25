package z4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Kanwa extends JPanel {
    public void moveSelectedComponent(){
        if(spriteToMove != null){
            spriteToMove.x += moveX;
            spriteToMove.y += moveY;
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
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            // Select sprite to animate
            if(spriteToMove == null){
                for(Sprite s : sprites){
                    if(s.contains(x, y)){
                        sprite = null;
                        spriteToMove = s;
                        mainFrame.setTitle("Currently moving: " + spriteToMove.getClass().getName() + ". To stop, click outside of the sprite.");
                        movingTimer.start();
                        return;
                    }
                }
            }else{
                if (! spriteToMove.contains(x, y)){
                    mainFrame.setTitle("Currently selected shape: none");
                    spriteToMove = null;
                    movingTimer.stop();
                }
            }
            drawAtMouseLocation(e);
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
            if(spriteToMove != null){
                activeKeys.add(e.getKeyCode());
                System.out.println(activeKeys);
                moveX = 0;
                moveY = 0;
                for(Integer i : activeKeys){
                    if(i == KeyEvent.VK_UP) moveY += -speed;
                    if(i == KeyEvent.VK_DOWN) moveY += speed;
                    if(i == KeyEvent.VK_LEFT) moveX += -speed;
                    if(i == KeyEvent.VK_RIGHT) moveX += speed;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(spriteToMove != null){
                int c = e.getKeyCode();
                if(c == KeyEvent.VK_UP) moveY -= -speed;
                if(c == KeyEvent.VK_DOWN) moveY -= speed;
                if(c == KeyEvent.VK_LEFT) moveX -= -speed;
                if(c == KeyEvent.VK_RIGHT) moveX -= speed;
                activeKeys.remove(e.getKeyCode());
                return;
            }
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
    private Sprite spriteToMove = null;
    private Timer movingTimer =  new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveSelectedComponent();
        }
    });
    private int moveX, moveY = 0;
    private int speed = 4;
    private HashSet<Integer> activeKeys = new HashSet<>();
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