package z2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Kanwa extends JPanel {
    public void drawAtMouseLocation(MouseEvent e){
        if(getSprite() instanceof Circle){
            Circle newRing = new Circle(e.getX(), e.getY(), Color.GREEN);
            sprites.add(newRing);
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
            System.out.println("mouseClicked");
            selectionImage = null;
            selectionRectangle = null;
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed");
            startX = e.getX();
            startY = e.getY();
            dragSelectedImage = false;
            if(selectionImage != null){
                int x = selectionImage.getX();
                int y = selectionImage.getY();
                int w = selectionImage.getImg().getWidth();
                int h = selectionImage.getImg().getHeight();
                if(startX >= x &&
                    startX <= x + w &&
                    startY >= y &&
                    startY <= y + h){
                    sprites.add(new Rectangle(x, y, x+w+1, y+h+1, Color.WHITE, true));
                    dragSelectedImage = true;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased");
            if(dragSelectedImage){
                MyBufferedImage imageToSave = new MyBufferedImage(
                        selectionImage.getX(),
                        selectionImage.getY(),
                        selectionImage.getImg());
                sprites.add(imageToSave);
                dragSelectedImage = false;
                selectionImage = null;
                repaint();
                return;
            }

            if(selectionRectangle != null){
                int w = selectionRectangle.getW()-1;
                int h = selectionRectangle.getH()-1;
                if(w > 1 && h > 1){

                    BufferedImage selectedArea = kanwaImage.getSubimage(
                    selectionRectangle.x+1,
                    selectionRectangle.y+1, w, h
                    );
                    BufferedImage tmp = new BufferedImage(w-1, h-1, BufferedImage.TYPE_INT_RGB);
                    selectionImage = new MyBufferedImage(
                        selectionRectangle.x+1,
                        selectionRectangle.y+1,
                        tmp
                    );
                    Graphics g = selectionImage.getImg().createGraphics();
                    g.drawImage(selectedArea, 0, 0, null);
                }
            }
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
            if(selectionImage != null && dragSelectedImage){
                selectionRectangle = null;
                selectionImage.setX(e.getX() - selectionImage.getImg().getWidth()/2);
                selectionImage.setY(e.getY() - selectionImage.getImg().getHeight()/2);
                repaint();
                return;
            }

            if(startX != null && startY != null){
                endX = e.getX();
                endY = e.getY();
                selectionRectangle = new Rectangle(startX, startY, endX, endY, Color.BLACK, false);
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            drawAtMouseLocation(e);
        }
    };
    public Sprite getSprite() {
        return sprite;
    }

    private Sprite sprite = null;
    private Integer startX, startY, endX, endY;
    private BufferedImage kanwaImage;

    public void setSelectionRectangle(Rectangle selectionRectangle) {
        this.selectionRectangle = selectionRectangle;
    }

    public void setSelectionImage(MyBufferedImage selectionImage) {
        this.selectionImage = selectionImage;
    }

    private Rectangle selectionRectangle = null;
    private MyBufferedImage selectionImage = null;
    private boolean dragSelectedImage = false;
    private List<Sprite> sprites = new ArrayList<>();
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    public Kanwa(int width, int height){
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

            // Draw selected area
            if(selectionImage != null){
                g2.drawImage(
                    selectionImage.getImg(),
                    selectionImage.getX(),
                    selectionImage.getY(),
            null);
            }

            // Draw selection rectangle
            if (selectionRectangle != null){
                g2.setColor(selectionRectangle.getColor());
                selectionRectangle.draw(g2);
            }
        } finally {
            kanwa2D.drawImage(kanwaImage, 0, 0, null);
            kanwa2D.dispose();
        }
    }
}
