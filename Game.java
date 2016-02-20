/**
 * Write a description of class Application here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel  implements KeyListener
{
    private JFrame frame;
    private Character player;
    
    public Game() throws IOException
    {
        frame = new JFrame("My Game");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        World world = new World();
        
        BufferedImage playerImg = ImageIO.read(new File("img/baby_tux.png"));
        player = new Character(384, 284, playerImg, world);
        
        addKeyListener(this);
        requestFocus();
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        player.paint(g);
    }
    
    public void play() throws InterruptedException
    {
        System.out.println("Game started");
        while (true)
        {
            // get input
            
            // game logic
            player.update();
            
            // draw stuff
            repaint();

            Thread.sleep(10);
        }
    }
    
    public void keyPressed(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        
        if (code == KeyEvent.VK_UP)
            player.setVy(-5);
        if (code == KeyEvent.VK_DOWN)
            player.setVy(5);
        
        if (code == KeyEvent.VK_LEFT)
            player.setVx(-5);
        if (code == KeyEvent.VK_RIGHT)
           player.setVx(5);
    }
    
    // from the KeyListener interface
    public void keyReleased(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        
        if (code == KeyEvent.VK_UP)
            player.setVy(0);
        if (code == KeyEvent.VK_DOWN)
            player.setVy(0);
        
        if (code == KeyEvent.VK_LEFT)
            player.setVx(0);
        if (code == KeyEvent.VK_RIGHT)
           player.setVx(0);
        
    }
    
    // from the KeyListener interface
    public void keyTyped(KeyEvent e) {
    
    }
}