import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
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

    public BufferedImage getSprite() {
        return rotateSprite(sprite, direction);
    }

    public void update(Graphics g) throws IOException {
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

    public static BufferedImage rotateSprite(BufferedImage sprite, double angle) {
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        // Create rotated center
        BufferedImage rotated = new BufferedImage(w, h, sprite.getType());
        Graphics2D graphics = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        // Rotate sprite around center
        at.rotate(angle, w / 2d, h / 2d);
        graphics.setTransform(at);
        graphics.drawImage(sprite, null,0, 0);
        graphics.dispose();
        // Return new sprite
        return rotated;
    }
}
