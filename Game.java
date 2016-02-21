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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener
{
    private JFrame frame;
    private Character player;
    private World world;
    private ArrayList<Sprite> blockList = new ArrayList<Sprite>();
    
    public Game() throws IOException
    {
        // make Jframe
        frame = new JFrame("My Game");
        frame.add(this);
        frame.setSize(16 * 64, 9 * 64);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // make world
        world = new World();
        
        // make player
        BufferedImage playerImg = ImageIO.read(new File("img/baby_tux.png"));
        player = new Character(384, 200, playerImg, this);

        // get block list
        blockList = world.getAllBlocks();
        
        addKeyListener(this);
        requestFocus();
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        
        player.paint(g);
        for (Sprite block : blockList)
            block.paint(g);

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
    
    public void keyPressed(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        
        if (code == KeyEvent.VK_LEFT)
            player.moveLeft();

        if (code == KeyEvent.VK_RIGHT)
           player.moveRight();
           
        if (code == KeyEvent.VK_SPACE)
           player.jump();
 
    }
    
    public void keyReleased(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        
        if (code == KeyEvent.VK_LEFT)
            player.stop();
            
        if (code == KeyEvent.VK_RIGHT)
            player.stop();
        
    }
    
    public void keyTyped(KeyEvent e) {
        // does nothing
    }
    
    public World getWorld()
    {
        return world;
    }
}