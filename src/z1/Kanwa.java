package z1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Kanwa extends JPanel {
    public Kanwa(){
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.BLACK, 5));
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setLayout(null);
    }
}
