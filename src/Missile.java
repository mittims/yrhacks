import java.io.IOException;

public class Missile extends Movable {
    public double targetX;
    public double targetY;
    public int deathTimer = 500;
    public Missile(String spritePath, double x, double y, double targetX, double targetY) throws IOException {
        super(spritePath, x, y, 5);
        this.targetX = targetX;
        this.targetY = targetY;
    }
    public void update () {

    }
}
