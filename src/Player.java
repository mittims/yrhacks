import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Movable {
    public double direction;

    public void update(Graphics g, int mx, int my) {
        g.drawImage(sprite, (int) (x - sprite.getWidth() / 2d), (int) (y - sprite.getHeight() / 2d), null);
        if (distanceTo(mx, my) < 10) return;
        direction = Math.atan2(my - y, mx - x);
        x += speed * Math.cos(direction);
        y += speed * Math.sin(direction);
    }

    public Player (String spritePath) throws IOException {
        super(spritePath, 0d, 0d, 0d);
    }
}
