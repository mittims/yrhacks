import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JPanel implements MouseListener, KeyListener, Runnable {
    public static boolean paused;
    public static JFrame frame;
    public static Player player = new Player(0, 0, 10);
    public static ArrayList<Movable> enemies = new ArrayList<>();

    public static void main(String[] args) {
        Main panel = new Main();
        frame = new JFrame("skibstri");
        frame.add(panel);
        frame.setPreferredSize(new Dimension(1500, 800));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        for (Movable enemy : enemies) {

        }
        g.drawRect((int) player.x, (int) player.y, 50, 50);
        player.update(mouseLocation.x, mouseLocation.y);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
