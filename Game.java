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
    private static int START = 0;
    private static int PLAYING = 1;
    private static int PAUSED = 2;
    private static int END = 3;
    
    private JFrame frame;
    private Character player;
    private World world;
    private int state = PLAYING;
    
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
        player = new Character(7 * 64, 7 * 64, playerImg, world);

        world.addPlayer(player);
        
        addKeyListener(new InputHandler(player, this));
        requestFocus();
    }
    
    public void togglePause()
    {
        if (state != PLAYING)
            state = PLAYING;
        else
            state = PAUSED;
    }
    
    public void play() throws InterruptedException
    {
        while (true)
        {
            if (state == PLAYING)
            {
                world.update();
            }
            
            repaint();
            Thread.sleep(10);
        }
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        world.paint(g);
    }
}