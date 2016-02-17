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
    
    public static void play() throws InterruptedException
    {
        while (true)
        {
            // get input
            
            // game logic
            
            // draw stuff
            
            
            Thread.sleep(10);
        }
    }
    
	public void paint(Graphics g) 
	{
		g.setColor(Color.RED);	
		g.fillRect(50, 50, 30, 30);
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		
		Game game = new Game();
		
		JFrame frame = new JFrame("My Game");
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game.play();
	}
}