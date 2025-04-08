import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JPanel implements MouseListener, KeyListener, Runnable {
    public static int gameState = -1;
    public static boolean paused;
    public static boolean mousePressed = true;
    public static JFrame frame;
    public static Player player;
    public static Enemy enemy;
    public static ArrayList<Fish> fish = new ArrayList<>();
    public static BufferedImage map;
    public static BufferedImage start;
    public static int numFishCollected = 0;
    public static int numFish = 10;
    public static int fishFrame = 0, targetFishFrame = 50;
    public static int screenWidth = 1500;
    public static int screenHeight = 800;
    public static boolean [][] land;
    public static Rectangle nest;

    public static void main(String[] args) throws IOException {
        player = new Player("Sprites/player.png");
        Main panel = new Main();
        start = ImageIO.read(new File("Sprites/IMG_0027.PNG"));
        frame = new JFrame("skibstri");
        frame.add(panel);
        frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making enemies:
        enemy = new Enemy(50, 50);
    }

    public Main() {
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(this);
        addMouseListener(this);
        this.setFocusable(true);
        Thread thread = new Thread(this);
        thread.start();
    }

    public void setLevel() throws IOException {
        map = ImageIO.read(new File("Sprites/map.PNG"));
        land = new boolean [map.getWidth()][map.getHeight()];
        nest = new Rectangle(1080, 370, 50, 50);
        Color c;
        for (int i = 0; i < map.getWidth(); i++){
            for (int j = 0; j < map.getHeight(); j++){
                c = new Color(map.getRGB(i, j));
                if (c.getRed() > 200) land[i][j] = true; //If red value is bigger than 200, it isn't blue, and since there are only two colours, it must be white
            }
        }
    }
    public void run() {
        while (true) {
            repaint();
            try {Thread.sleep(10);}
            catch (Exception e) {throw new RuntimeException(e);}
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == -1) {
            g.drawImage(start, 0, 0, null);
            try {
                setLevel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (gameState == 0) {
            g.drawImage(map, 0, 0, null);
            g.drawString(String.format("Fish Collected: %d", numFishCollected), 50, 50);

            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            // Player updating
            try {
                player.update(g, mouseLocation.x, mouseLocation.y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (mousePressed && player.speed < 6d) player.speed += player.acceleration;
            if (!mousePressed) {
                player.speed += player.deceleration;
                if (player.speed < 0) player.speed = 0;
            }
            // Player updating
            try {
                player.update(g, mouseLocation.x, mouseLocation.y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(player.isOnLand())

            // Enemy updating
            try {
                enemy.update(g);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Add fish
            int side;
            int sx = 0;
            int sy = 0;
            double direction = 0;
            if (fish.size() < numFish && !paused){
                fishFrame++;
                if (fishFrame == targetFishFrame){
                    side = (int) (Math.random()*4);
                    if (side < 1){
                        sy = (int) (Math.random()*800);
                        direction = 3.1415926535;
                    }
                    else if (side < 2){
                        sx = (int) (Math.random()*1500);
                        direction = -3*3.141592653/2;
                    }
                    else if (side < 3){
                        sx = 1600;
                        sy = (int) (Math.random()*800);
                        direction = 3.14159265;
                    }
                    else if (side < 4){
                        sx = (int) (Math.random()*1500);
                        sy = 850;
                        direction = 3*3.14159265358979323/2;
                    }

                    try {
                        fish.add(new Fish (sx,sy, 1.5d, direction));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    fishFrame = 0;
                }
            }
            for (int i = fish.size() - 1; i >= 0; i--) {
                try {
                    fish.get(i).update(g);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                double x = fish.get(i).x;
                double y = fish.get(i).y;
                if (x > 1550 || y > 850 || x < -50 || y < -50) {
                    fish.remove(i);
                }
                else if (player.hitBox.intersects(fish.get(i).hitBox)) {
                    fish.remove(i);
                    numFishCollected++;
                }
            }
        }

        //Draw and update missiles
        for (int i = 0; i < enemy.missiles.size(); i++) {
            enemy.missiles.get(i).update(g);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) paused = !paused;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) gameState--;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) gameState++;
    }

    public void keyReleased(KeyEvent e) {}
    public boolean clickRectangle(MouseEvent e, int x1, int y1, int x2, int y2) {
        return (x1 <= e.getX() && e.getX() <= x2 && y1 <= e.getY() && e.getY() <= y2);
    }
    public void mouseClicked(MouseEvent e) {
        if (clickRectangle(e, 528,367,1064,520)){
            gameState = 0;
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
