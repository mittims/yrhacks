import java.awt.*;
import java.io.IOException;

public class Player extends Movable {
    public double acceleration = 0.2d;
    public double deceleration = -0.5d;
    public double direction;

    public void update(Graphics g, int mx, int my) {
        if (!Main.paused) {
            if (distanceTo(mx, my) > 10) {
                direction = Math.atan2(my - y, mx - x);
                x += speed * Math.cos(direction);
                y += speed * Math.sin(direction);
                speed += acceleration;
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
}
