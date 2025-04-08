import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fish extends Movable{
    public int changeFrame = 150;
    public int swimFrame = 0;
    public int localChangeFrame;
    public BufferedImage fish1 = ImageIO.read(new File("Sprites/Fish 1.PNG"));
    public BufferedImage fish2 = ImageIO.read(new File("Sprites/Fish 2.PNG"));
    public Fish (int x, int y, double speed, double direction) throws IOException {
        super("Sprites/Fish 1.PNG", x, y, speed);

        this.direction = direction;
        localChangeFrame = 0;
    }
    public void update(Graphics g) throws IOException {
        if (!Main.paused) {
            localChangeFrame++;
            swimFrame++;
            if (swimFrame == 10){
                sprite = fish1;
            }
            if (swimFrame == 20){
                sprite = fish2;
                swimFrame = 0;
            }
            x += speed * Math.cos(direction);
            y += speed * Math.sin(direction);
            if (distanceTo(Main.player.x, Main.player.y) < 200){
                direction = Math.atan2(Main.player.y - y, Main.player.x - x) - 180;
            }
            else if (localChangeFrame == changeFrame) {
                direction = Math.random()*2*3.1415926535;
                changeFrame = (int) (Math.random()*((75 - 50) + 50));
                localChangeFrame = 0;
                System.out.println(changeFrame);
            }
            if (x > 0 && x < 1500 && y > 0 && y < 800 && Main.land[(int) x][(int) y]){
                x += 20 * Math.cos(direction - 3.1415926535);
                y += 20 * Math.sin(direction - 3.1415926535);
                direction = Math.random()*360;
            }
        }

        super.update(g);
    }
}
