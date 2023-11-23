package z2;

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

    public Rectangle(int x, int y, int x2, int y2, Color c, boolean fill){
        super(x, y);
        this.color = c;
        this.w = Math.abs(x-x2);
        this.h = Math.abs(y-y2);
        if(x > x2) this.x = x2;
        if(y > y2) this.y = y2;
        this.fill = fill;
    }
    @Override
    public void draw(Graphics2D g2d) {
        if(fill)
            g2d.fillRect(x, y, w, h);
        else
            g2d.drawRect(x, y, w, h);
    }
}
