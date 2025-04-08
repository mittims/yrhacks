import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Enemy extends Movable {
    public int movementFrameCount;
    public int direction; //N, E, S, W: 1, 2, 3, 4
    public ArrayList<Missile> missiles = new ArrayList<>();
    public int rocketSpawnDelay = 250;
    public Enemy(int x, int y) throws IOException {
        super("Sprites/ball.png", x, y, 2);
        movementFrameCount = 0;
    }
    public void update(Graphics g) throws IOException {
        //Draw sprite
        g.drawImage(sprite, (int) (x - sprite.getWidth() / 2d), (int) (y - sprite.getHeight() / 2d), null);

        //Checking if out of bounds
        if (!Main.paused) {
            if (direction == 1 && y <= sprite.getHeight()) {
                direction = 3;
                movementFrameCount = 100;
            } else if (direction == 2 && x >= Main.screenWidth - sprite.getWidth()) {
                direction = 4;
                movementFrameCount = 100;
            } else if (direction == 3 && y >= Main.screenHeight - sprite.getHeight()) {
                direction = 1;
                movementFrameCount = 100;
            } else if (direction == 4 && x <= sprite.getWidth()) {
                direction = 2;
                movementFrameCount = 100;
            }


            //Movement frame counter
            if (movementFrameCount == 0) {
                movementFrameCount = (int) (100 + Math.random() * 200);
                direction = (int) (1 + Math.random() * 4);
            } else movementFrameCount--;

            //Movement
            if (direction == 1) {
                y -= speed;
            } else if (direction == 2) {
                x += speed;
            } else if (direction == 3) {
                y += speed;
            } else if (direction == 4) {
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
