public class Movable {
    public double x;
    public double y;
    public double direction;
    public double speed;

    public Movable (float x, float y, double speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public double distanceTo(int mx, int my) {
        return Math.sqrt((mx - x) * (mx - x) + (my - y) * ((my - y)));
    }
}
