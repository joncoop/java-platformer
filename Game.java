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
    public static final int START = 0;
    public static final int PLAYING = 1;
    public static final int PAUSED = 2;
    public static final int END = 3;
    
    private JFrame frame;
    private Player player;
    private Character character;
    private World world;
    private int state;
    
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
        player = new Player();
        
        // make character
        BufferedImage playerImg = ImageIO.read(new File("img/baby_tux.png"));
        character = new Character(7 * 64, 7 * 64, playerImg, world, player);

        world.addPlayer(character);
        
        // add input handlers
        addKeyListener(new GameControls(this));
        addKeyListener(new PlayerControls(character, this));
        
        // set game state
        state = START;
        
        // request focus
        requestFocus();
    }
    
    public int getState()
    {
        return state;
    }
    
    public void setState(int state)
    {
        this.state = state;
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
        super.paint(g); // doesn't seem to do much other than clear the JPanel
        world.paint(g);
    }
}