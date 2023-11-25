package z6;

import java.awt.*;

public class Circle extends Sprite {
    private int radius;
    public Circle(){
        super();
    }
    public Circle(int x, int y, int radius, Color color){
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillOval(this.x - this.radius, this.y - this.radius, this.radius*2, this.radius*2);
    }

    @Override
    public boolean contains(int x, int y) {
        int circleEquation = (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y);
        if(circleEquation <= this.radius*this.radius) return true;
        return false;
    }
}
