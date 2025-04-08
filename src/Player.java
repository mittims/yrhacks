import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Movable{
    public BufferedImage sprite;
    public double direction;
    public Rectangle hitBox;

    public void update(int mx, int my) {
        if (distanceTo(mx, my) < 10) return;
        direction = Math.atan2(my - y, mx - x);
        x += speed * Math.cos(direction);
        y += speed * Math.sin(direction);
        System.out.println(speed);
        System.out.println(x + " " + y);
    }

    public Player (int x, int y, double speed){
        super(x, y, speed);
    }
}
