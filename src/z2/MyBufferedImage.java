package z2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyBufferedImage extends Sprite{
    public BufferedImage getImg() {
        return img;
    }
    private BufferedImage img;
    public MyBufferedImage(int x, int y, BufferedImage image){
        super(x, y);
        this.img = image;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }
}
