import java.awt.*;
import java.io.IOException;

public class Fish extends Movable{
    public int changeFrame = 75;
    public int localFrame;

    public Fish (int x, int y, double speed, double direction) throws IOException {
        super("Sprites/player.png", x, y, speed);
        this.direction = direction;
        localFrame = 0;
    }
    public void update(Graphics g){
        if (!Main.paused) {
            localFrame++;
            x += speed * Math.cos(direction);
            y += speed * Math.sin(direction);
            if (distanceTo(Main.player.x, Main.player.y) < 200){
                direction = Math.atan2(Main.player.y - y, Main.player.x - x) - 180;
            }
            else if (localFrame == changeFrame) {
                direction = Math.random()*360;
                changeFrame = (int) (Math.random()*((75 - 25) + 1));
                localFrame = 0;
            }
        }

        super.update(g);
    }
}
