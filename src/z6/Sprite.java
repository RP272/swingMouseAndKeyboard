package z6;

import java.awt.*;

public abstract class Sprite {
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected int x, y;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    protected Color color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite(){
        this(1, 1, Color.BLACK);
    }
    public Sprite(int x, int y, Color c){
        super();
        this.x = x;
        this.y = y;
        this.color = c;
    }

    public abstract void draw(Graphics2D g2d);

    public abstract boolean contains(int x, int y);
}
