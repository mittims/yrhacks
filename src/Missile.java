import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Missile extends Movable {
    public double targetX;
    public double targetY;
    public int lifespan = 500;
    public boolean alive = true;
    public Missile(String spritePath, double x, double y, double targetX, double targetY) throws IOException {
        super(spritePath, x, y, 5);
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;

        int w = sprite.getWidth();
        int h = sprite.getHeight();
        // Create rotated center
        BufferedImage rotated = new BufferedImage(w, h, sprite.getType());
        Graphics2D graphics = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        // Rotate sprite around center
        at.rotate(Math.atan2(targetY - y, targetX - x), w / 2d, h / 2d);
        graphics.setTransform(at);
        graphics.drawImage(sprite, null,0, 0);
        graphics.dispose();
        // Return new sprite
        sprite = rotated;
    }
    public void update (Graphics g) {
        if (lifespan == 0) {
            alive = false;
        } else {
            g.drawImage(sprite, (int)x, (int)y, null);
            lifespan--;
            //Movement
            x += speed * Math.cos(direction);
            y += speed * Math.sin(direction);
            hitBox.x = (int) x;
            hitBox.y = (int) y;

            //Player hit detection
            if (hitBox.intersects(Main.player.hitBox)) {

            }

            //Decrease projectile lifespan
            lifespan--;
        }
    }
}
