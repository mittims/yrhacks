import java.awt.*;
import java.awt.image.BufferedImage;

public class Fish extends Movable{

    public BufferedImage fishSprite;
    public Rectangle fishHitBox;
    public int changeFrame = 10;
    public int localFrame;
    public Fish (int x, int y, int speed) {
        super(x, y, speed);
        localFrame = 0;
    }
    public void move (){
        localFrame++;
        x += speed * Math.cos(direction);
        y += speed * Math.sin(direction);
        if (localFrame == changeFrame) {
            direction = Math.random()*360;
            changeFrame = (int) (Math.random()*1000);
        }
    }
}
