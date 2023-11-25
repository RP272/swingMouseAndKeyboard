package z6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Kanwa extends JPanel {
    public void drawAtMouseLocation(MouseEvent e){
        if(sprite == SpriteEnum.CIRCLE){
            Circle newRing = new Circle(e.getX(), e.getY(), 20, spriteColor);
            sprites.add(newRing);
        }
        else if(sprite == SpriteEnum.RECTANGLE){
            Rectangle newRect = new Rectangle(e.getX(), e.getY(), 40, 20, spriteColor, true);
            sprites.add(newRect);
        }
        repaint();
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(sprite != null){
                if(e.getKeyCode() == KeyEvent.VK_1){
                    if(sprite == SpriteEnum.CIRCLE) spriteColor = Color.RED;
                    if(sprite == SpriteEnum.RECTANGLE) spriteColor = Color.BLUE;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(sprite != null){
                if(e.getKeyCode() == KeyEvent.VK_1){
                    if(sprite == SpriteEnum.CIRCLE) spriteColor = Color.GREEN;
                    if(sprite == SpriteEnum.RECTANGLE) spriteColor = Color.ORANGE;
                }
            }
        }
    };

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }

    private final MouseMotionListener mouseMotionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            drawAtMouseLocation(e);
        }
    };
    private void setSprite(SpriteEnum s){
        sprite = s;
        if(s == SpriteEnum.CIRCLE) spriteColor = Color.GREEN;
        if(s == SpriteEnum.RECTANGLE) spriteColor = Color.ORANGE;
        mainFrame.setTitle("Currently selected shape: " + s.getClass().getSimpleName());
    }
    private SpriteEnum sprite = null;
    private Color spriteColor;
    private enum SpriteEnum {
        CIRCLE, RECTANGLE
    }
    private BufferedImage kanwaImage;
    private List<Sprite> sprites = new ArrayList<>();
    private JFrame mainFrame;

    public Kanwa(int width, int height, JFrame mainFrame){
        this.mainFrame = mainFrame;
        setFocusable(true);
        setBackground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setLayout(null);
        setBounds(0, 0, width, height);
        kanwaImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "ctrl+c");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), "ctrl+r");
        getActionMap().put("ctrl+c", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSprite(SpriteEnum.CIRCLE);
            }
        });
        getActionMap().put("ctrl+r", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSprite(SpriteEnum.RECTANGLE);
            }
        });
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