import java.awt.*;
import java.io.IOException;

public class Player extends Movable {
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
                    speed = Math.min(speed, 3d - Main.numFishCollected * 0.1d);
                } else {
                    speed = Math.min(speed, 6d - Main.numFishCollected * 0.25d);
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
    }
    public void hurt() {

    }
}
