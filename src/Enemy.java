import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Enemy extends Movable {
    public int movementFrameCount;
    public int direction; //N, E, S, W: 1, 2, 3, 4
    public Rectangle hitbox;
    public Enemy(int x, int y) throws IOException {
        super("Sprites/ball.png", x, y, 2);
        movementFrameCount = 0;
    }
    public void update(Graphics g) {
        //Draw sprite
        g.drawImage(sprite, (int) (x - sprite.getWidth() / 2d), (int) (y - sprite.getHeight() / 2d), null);

        //Checking if out of bounds
        if (direction == 1 && y <= sprite.getHeight()) {
            direction = 3;
            movementFrameCount = 100;
        } else if (direction == 2 && x >= Main.screenWidth-sprite.getWidth()) {
            direction = 4;
            movementFrameCount = 100;
        } else if (direction == 3 && y >= Main.screenHeight-sprite.getHeight()) {
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
            y-=speed;
        } else if (direction == 2) {
            x+=speed;
        } else if (direction == 3) {
            y+=speed;
        } else if (direction == 4) {
            x-=speed;
        }

        if (Main.player.distanceTo(x, y) < 500) {
            
        }
    }
}
