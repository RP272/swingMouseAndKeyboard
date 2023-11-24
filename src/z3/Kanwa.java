package z3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Kanwa extends JPanel {
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

    public MouseMotionListener getMotionListener() {
        return motionListener;
    }

    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Select sprite to animate
            if(spriteToAnimate == null){
                for(Sprite s : sprites){
                    if(s.contains(e.getX(), e.getY())){
                        sprite = null;
                        spriteToAnimate = s;
                        animationTimeInSeconds = 0;
                        animationTimer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                animationTimeInSeconds++;
                                parent.setTitle(animationTimeInSeconds.toString());
                            }
                        });
                        animationTimer.start();
                        return;
                    }
                }
            }else{
                if(duringAnimation)
                    return;
                duringAnimation = true;
                animationTimer.stop();
                animationTimeInSeconds = animationTimeInSeconds > 0 ? animationTimeInSeconds : 1;
                endX = e.getX();
                endY = e.getY();
                if(spriteToAnimate instanceof Rectangle){
                    endX -= ((Rectangle) spriteToAnimate).getW()/2;
                    endY -= ((Rectangle) spriteToAnimate).getH()/2;
                }
                double distX = endX - spriteToAnimate.getX();
                double distY = endY - spriteToAnimate.getY();
                animationSpeedX = (int) Math.floor(distX/animationTimeInSeconds);
                animationSpeedY = (int) Math.floor(distY/animationTimeInSeconds);
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (duringAnimation){
                            if(spriteToAnimate.contains(endX, endY)){
                                duringAnimation = false;
                                spriteToAnimate = null;
                                endX = null;
                                endY = null;
                                ((Timer)e.getSource()).stop();
                                return;
                            }
                            if(spriteToAnimate.x != endX)
                                spriteToAnimate.x += animationSpeedX;
                            if(spriteToAnimate.y != endY)
                                spriteToAnimate.y += animationSpeedY;
                            parent.setTitle((--animationTimeInSeconds).toString());
                            repaint();
                        }
                    }
                });
                timer.start();
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

    private final MouseMotionListener motionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    };
    public Sprite getSprite() {
        return sprite;
    }

    private Sprite sprite = null;
    private Integer endX, endY;
    private int animationSpeedX;
    private int animationSpeedY;
    private boolean duringAnimation = false;
    private Integer animationTimeInSeconds = 0;
    private Timer animationTimer = null;
    private Sprite spriteToAnimate = null;
    private BufferedImage kanwaImage;
    private List<Sprite> sprites = new ArrayList<>();
    private JFrame parent;
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    public Kanwa(int width, int height, JFrame parent){
        this.parent = parent;
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
