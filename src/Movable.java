import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movable {
    public static BufferedImage sprite;
    public double x;
    public double y;
    public double direction;
    public double speed;

    public Movable (String spritePath, double x, double y, double speed) throws IOException {
        sprite = ImageIO.read(new File(spritePath));
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public double distanceTo(int mx, int my) {
        return Math.sqrt((mx - x) * (mx - x) + (my - y) * ((my - y)));
    }


}
