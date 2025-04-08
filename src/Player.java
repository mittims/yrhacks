import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Movable{
    public BufferedImage sprite;
    public int x;
    public int y;
    public double direction;
    public double speed;
    public Rectangle hitBox;

    public void update(int mx, int my) {
        direction = Math.atan2(my - y, mx - x);
        x += (int) (speed * Math.cos(direction));
        y += (int) (speed * Math.sin(direction));
    }

    public Player (int x, int y){
        super(x, y);
    }
}
