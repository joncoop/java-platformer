/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel
{
    private JFrame frame;
    private Character player;
    private World world;
    
    public Game(String title) throws IOException
    {
        // make Jframe
        frame = new JFrame(title);
        frame.add(this);
        frame.setSize(16 * 64, 9 * 64);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // make world
        world = new World();
        
        // make player
        BufferedImage playerImg = ImageIO.read(new File("img/baby_tux.png"));
        player = new Character(7 * 64, 7 * 64, playerImg, this);

        addKeyListener(new InputHandler(player));
        requestFocus();
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        world.paint(g);
        player.paint(g);
    }
    
    public void play() throws InterruptedException
    {
        while (true)
        {
            // update game objects
            player.update();
            
            // draw stuff
            repaint();

            // wait a bit
            Thread.sleep(10);
        }
    }
    
    public World getWorld()
    {
        return world;
    }
}