import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Movable {
    public BufferedImage sideSprite;
    public BufferedImage upSprite;
    public BufferedImage standSprite;
    public double acceleration = 0.2d;
    public double deceleration = -0.5d;
    public double direction;

    public void update(Graphics g, int mx, int my) throws IOException {
        if (!Main.paused) {
            direction = Math.atan2(my - y, mx - x);
            if (distanceTo(mx, my) > 10) {
                x += speed * Math.cos(direction);
                y += speed * Math.sin(direction);
                speed += acceleration;
                if (x + sprite.getWidth() > 1500) {
                    x = 1499 - sprite.getWidth();
                } else if (y + sprite.getHeight() > 800) {
                    y = 799 - sprite.getHeight();
                }
                if (isOnLand()) {
                    sprite = standSprite;
                    speed = Math.min(speed, 3d * Math.pow(0.9, Main.numFishCollected));
                } else {
                    sprite = rotateSprite(sideSprite, direction - Math.PI / 2d);
                    speed = Math.min(speed, 4d * Math.pow(0.9, Main.numFishCollected));
                }
            } else {
                speed += deceleration;
                speed = Math.max(0, speed);
            }
        }
        super.update(g);
    }

    public Player (String spritePath) throws IOException {
        super(spritePath, 0d, 0d, 0d);
        sideSprite = ImageIO.read(new File("Sprites/player.png"));
        upSprite = ImageIO.read(new File("Sprites/Swimming penguin 2.PNG"));
        standSprite = rotateSprite(ImageIO.read(new File("Sprites/Upright penguin 1.PNG")), -Math.PI / 2);
    }
}
