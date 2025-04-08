import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movable {
    public BufferedImage sprite;
    public Rectangle hitBox;
    public double x;
    public double y;
    public double direction;
    public double speed;

    public Movable (String spritePath, double x, double y, double speed) throws IOException {
        this.sprite = ImageIO.read(new File(spritePath));
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.hitBox = new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
    }

    public void update(Graphics g) {
        g.drawImage(sprite, (int) (x - sprite.getWidth() / 2d), (int) (y - sprite.getHeight() / 2d), null);
        hitBox.x = (int) (x - sprite.getWidth() / 2d);
        hitBox.y = (int) (y - sprite.getHeight() / 2d);
    }

    public double distanceTo(double mx, double my) {
        return Math.sqrt((mx - x) * (mx - x) + (my - y) * ((my - y)));
    }

    public boolean isOnLand() {
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        return Main.land[(int) x][(int) y] || Main.land[(int) x + w][(int) y]
                || Main.land[(int) x][(int) y + h] || Main.land[(int) x + w][(int) y + h];
    }
}
