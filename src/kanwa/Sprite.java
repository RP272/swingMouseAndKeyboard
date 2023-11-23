package kanwa;

import java.awt.*;

public abstract class Sprite {
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
}
