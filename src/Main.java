import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements MouseListener, KeyListener, Runnable {
    public static boolean paused;
    public static boolean mousePressed = false;
    public static JFrame frame;
    public static Player player;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Fish> fish = new ArrayList<>();
    public static int numFish = 10;
    public static int fishFrame = 0, targetFishFrame = 50;
    public static int screenWidth = 1500;
    public static int screenHeight = 800;

    public static void main(String[] args) throws IOException {
        player = new Player("Sprites/player.png");
        fish.add(new Fish(50, 0, 6));
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
        if (paused) return;
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        for (Enemy enemy : enemies) {
            enemy.update(g);
        }
        g.drawRect((int) player.x, (int) player.y, 50, 50);
        player.update(g, mouseLocation.x, mouseLocation.y);

        if (mousePressed && player.speed < 7.5d) player.speed += player.acceleration;
        if (!mousePressed) {
            player.speed += player.deceleration;
            if (player.speed < 0) player.speed = 0;
        }
        player.update(g, mouseLocation.x, mouseLocation.y);

        if (fish.size() < numFish){
            fishFrame++;
            if (fishFrame == targetFishFrame){
                try {
                    fish.add(new Fish (50,0, 6));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                fishFrame = 0;
            }
        }
        for (int i = fish.size() - 1; i >= 0; i--) {
            fish.get(i).update(g);
            if (player.hitBox.intersects(fish.get(i).hitBox)) {
                fish.remove(i);
            }
        }

        player.update(g, mouseLocation.x, mouseLocation.y);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
