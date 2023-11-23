package kanwa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Kanwa extends JPanel {
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    private Sprite sprite = null;

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    private List<Sprite> sprites = new ArrayList<>();

    public Kanwa(){
        setBackground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setLayout(null);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        try{
            if(sprite == null) return;
            if(sprites.isEmpty()) return;
            g2d.setColor(Color.GREEN);
            for(Sprite s : sprites){{
                g2d.setColor(s.color);
                s.draw(g2d);
            }}
        } finally {
            g2d.dispose();
        }
    }
}
