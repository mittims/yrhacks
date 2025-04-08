import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JPanel implements MouseListener, KeyListener, Runnable {
    public static boolean paused;
    public static boolean mousePressed = true;
    public static JFrame frame;
    public static Player player;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Fish> fish = new ArrayList<>();
    public static BufferedImage map;
    public static int numFishCollected = 0;
    public static int numFish = 10;
    public static int fishFrame = 0, targetFishFrame = 50;
    public static int screenWidth = 1500;
    public static int screenHeight = 800;
    public static boolean [][] land;

    public static void main(String[] args) throws IOException {
        player = new Player("Sprites/player.png");
        fish.add(new Fish(500, 10, 6, 0));
        map = ImageIO.read(new File("Sprites/map.PNG"));
        land = new boolean [map.getWidth()][map.getHeight()];
        Color c;
        for (int i = 0; i < map.getWidth(); i++){
            for (int j = 0; j < map.getHeight(); j++){
                c = new Color(map.getRGB(i, j));
                if (c.getRed() > 200) land[i][j] = true; //If red value is bigger than 200, it isn't blue, and since there are only two colours, it must be white
            }
        }
        Main panel = new Main();
        frame = new JFrame("skibstri");
        frame.add(panel);
        frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making enemies:
        enemies.add(new Enemy(0, 0));
    }

    public Main() {
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(this);
        addMouseListener(this);
        this.setFocusable(true);
        Thread thread = new Thread(this);
        thread.start();
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
        g.drawImage(map, 0, 0, null);
        g.drawString(String.format("Fish Collected: %d", numFishCollected), 50, 50);


        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        // Player updating
        player.update(g, mouseLocation.x, mouseLocation.y);
        System.out.println(player.isOnLand());

        // Enemy updating
        for (Enemy enemy : enemies) {
            enemy.update(g);
        }

        // Add fish
        if (fish.size() < numFish && !paused){
            fishFrame++;
            if (fishFrame == targetFishFrame){
                try {
                    fish.add(new Fish ((int) (Math.random()*1800),10, 2.5d, -3*3.14159265358979323/2));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                fishFrame = 0;
            }
        }
        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).update(g);
            double x = fish.get(i).x;
            double y = fish.get(i).y;
            if (x > 1550 || y > 850 || x < -50 || y < -50) {
                fish.remove(i);
            }
        }
        for (int i = fish.size() - 1; i >= 0; i--) {
            fish.get(i).update(g);
            if (player.hitBox.intersects(fish.get(i).hitBox)) {
                fish.remove(i);
                numFishCollected++;
            }
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) paused = !paused;
    }

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
