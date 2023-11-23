package kanwa;

import java.awt.*;

public class Rectangle extends Sprite{
    private final int width = 40;
    private final int height = 20;

    public Rectangle(){
        super();
    }
    public Rectangle(int x, int y, Color color){
        super(x, y, color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect(this.x - Math.round(this.width/2), this.y - Math.round(this.height/2), this.width, this.height);
    }
}
