/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Dimension;

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
        
        // resize panel
        this.setPreferredSize(new Dimension(16 * 64, 9 * 64));
        
        // make world
        world = new World();
        
        // make player
        player = new Player();
        
        // make character
        BufferedImage characterImg = ImageIO.read(new File("img/baby_tux.png"));
        int x = world.getPlayerStartX();
        int y = world.getPlayerStartY();
        character = new Character(x, y, characterImg, world, player);

        world.addPlayer(character);
        
        // add input handlers
        addKeyListener(new GameControls(this));
        addKeyListener(new CharacterControls(character, this));
        
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
        if (state == PLAYING)
            state = PAUSED;
        else if (state == PAUSED);
            state = PLAYING;
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
        
        String scoreText = Integer.toString(player.getScore());
        String livesText = Integer.toString(player.getLives());
        
        g.setColor(Color.BLACK);
        Font font = new Font("Serif", Font.PLAIN, 48);
        g.setFont(font);
        g.drawString(scoreText, 48, 80);
        
        g.setColor(Color.RED);

        g.setFont(font);
        g.drawString(livesText, 48, 130);
    }
}