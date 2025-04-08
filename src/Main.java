import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JPanel implements MouseListener, KeyListener, Runnable {
    public static JFrame frame;
    public static ArrayList<Movable> entities = new ArrayList<>();

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

    public void paintComponent(Graphics g) {

    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void run() {

    }
}
