import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.FeatureDescriptor;
import java.io.*;
import java.util.ArrayList;

public class Enemy extends Movable {
    public BufferedImage plane;
    public int movementFrameCount;
    public int d; //N, E, S, W: 1, 2, 3, 4
    public ArrayList<Missile> missiles = new ArrayList<>();
    public int rocketSpawnDelay = 250;
    public Enemy(int x, int y) throws IOException {
        super("Sprites/Plane.PNG", x, y, 2);
        plane = ImageIO.read(new File("Sprites/Plane.PNG"));
        movementFrameCount = 0;
    }
    public void update(Graphics g) throws IOException {
        //Checking if out of bounds
        if (!Main.paused) {
            if (d == 1 && y <= sprite.getHeight()) {
                d = 3;
                movementFrameCount = 100;
                direction = 0;
            } else if (d == 2 && x >= Main.screenWidth - sprite.getWidth()) {
                d = 4;
                movementFrameCount = 100;
                direction = Math.PI / 2;
            } else if (d == 3 && y >= Main.screenHeight - sprite.getHeight()) {
                d = 1;
                movementFrameCount = 100;
                direction = Math.PI;
            } else if (d == 4 && x <= sprite.getWidth()) {
                d = 2;
                movementFrameCount = 100;
                direction = -Math.PI / 2;
            }


            //Movement frame counter
            if (movementFrameCount == 0) {
                movementFrameCount = (int) (100 + Math.random() * 200);
                d = (int) (1 + Math.random() * 4);
            } else movementFrameCount--;

            //Movement
            if (d == 1) {
                y -= speed;
            } else if (d == 2) {
                x += speed;
            } else if (d == 3) {
                y += speed;
            } else if (d == 4) {
                x -= speed;
            }
        }
        super.update(g);

        if (rocketSpawnDelay > 0) {
            rocketSpawnDelay--;
        }
        if (Main.player.distanceTo(x, y) < 800 && rocketSpawnDelay == 0) {
            missiles.add(new Missile("Sprites/Rocket.PNG", x, y, Main.player.x, Main.player.y));
            rocketSpawnDelay = 250;
        }
    }
}
