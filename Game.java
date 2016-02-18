/**
 * Write a description of class Application here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JPanel 
{
    private JFrame frame;
    private Character player;
    
    public Game()
    {
        frame = new JFrame("My Game");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        World world = new World();
        
        player = new Character(384, 284, 32, 32, world);
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        player.paint(g);
    }
    
    public void play() throws InterruptedException
    {
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
}