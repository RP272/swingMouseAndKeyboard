package z4;

import java.awt.*;

public class Rectangle extends Sprite {
    private int w;

    public Color getColor() {
        return color;
    }

    private Color color;

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    private int h;
    private boolean fill;
    public Rectangle(){
        super();
    }

    public Rectangle(int x, int y, int w, int h, Color c, boolean fill){
        super(x-w/2, y-h/2);
        this.color = c;
        this.w = w;
        this.h = h;
        this.fill = fill;
    }
    @Override
    public void draw(Graphics2D g2d) {
        if(fill)
            g2d.fillRect(x, y, w, h);
        else
            g2d.drawRect(x, y, w, h);
    }

    @Override
    public boolean contains(int x, int y) {
        if(x >= this.x && x <= this.x+this.w && y >= this.y && y <= this.y+this.h) return true;
        return false;
    }
}
