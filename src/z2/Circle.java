package z2;

import java.awt.*;

public class Circle extends Sprite {
    private final int radius = 20;

    private Color color;

    public Color getColor() {
        return color;
    }

    public Circle(){
        super();
    }
    public Circle(int x, int y, Color color){
        super(x, y);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillOval(this.x - this.radius, this.y - this.radius, this.radius*2, this.radius*2);
    }
}
