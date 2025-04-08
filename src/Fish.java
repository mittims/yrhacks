import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fish extends Movable{

    public BufferedImage fishSprite;
    public Rectangle fishHitBox;
    public int changeFrame = 10;
    public int localFrame;
    public Fish (int x, int y, int speed) throws IOException {
        super("Sprites/player.png", x, y, speed);
        localFrame = 0;
    }
    public void move (){
        localFrame++;
        x += speed * Math.cos(direction);
        y += speed * Math.sin(direction);
        if (x > 1500 || y > 800 || x < 0 || y < 0) direction += 90;
        if (localFrame == changeFrame) {
            direction = Math.random()*360;
            changeFrame = (int) (Math.random()*100);
            localFrame = 0;
        }
        System.out.println(localFrame);
    }
}
