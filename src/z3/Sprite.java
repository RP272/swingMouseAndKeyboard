package z3;

import java.awt.*;

public abstract class Sprite {
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite(){
        this(1, 1);
    }
    public Sprite(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics2D g2d);

    public abstract boolean contains(int x, int y);
}
