import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Movable{
    public BufferedImage sprite;
    public int x;
    public int y;
    public Rectangle hitBox;

    public int speed;

    public Player (int x, int y){
        super(x, y);
    }
}
