/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel
{
    // window settings
    public static final int WIDTH = 16;
    public static final int HEIGHT = 9;
    public static final int SCALE = 64;
    public static final String TITLE = "Game Title";
    
    // stages
    public static final int START = 0;
    public static final int PLAYING = 1;
    public static final int PAUSED = 2;
    public static final int OVER = 3;
       
    private JFrame frame;
    private Player player;
    private Character character;
    private World world;
    private int state;
    
    public Game() throws IOException
    {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        // make Jframe
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        
        // make world
        world = new World();
        
        // make player
        player = new Player(this);
        
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
    
    public Player getPlayer()
    {
        return player;
    }
    
    public void setState(int state)
    {
        this.state = state;
    }
    
    public void togglePause()
    {
        if (state == PLAYING)
            state = PAUSED;
        else if (state == PAUSED)
            state = PLAYING;
    }

    public void restart()
    {
        player.setScore(0);
        player.setLives(0);
        world.reset();
        state = START;
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
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        world.paint(g);
        
        String scoreText = "Score: " + player.getScore();
        String livesText = "Lives: " + player.getLives();
        String startText = "Press any key to start.";
        String pauseText = "Press 'p' to resume.";
        String overText = "Game Over";
        String newText = "Press 'n' to start a new game.";
        
        Font small = new Font("Serif", Font.PLAIN, 32);
        Font medium = new Font("Serif", Font.PLAIN, 64);
        Font large = new Font("Serif", Font.PLAIN, 96);
        
        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(scoreText, 32, 64);
        g.drawString(livesText, 32, 96);
        
        
        if (state == START)
        {
            g.setFont(large);
            g.drawString(TITLE, 220, 250);
            g.setFont(small);
            g.drawString(startText, 320, 300);
        }
        else if (state == PAUSED)
        {
            g.setFont(small);
            g.drawString(pauseText, 340, 280);
        }
        else if (state == OVER)
        {
            g.setFont(large);
            g.drawString(overText, 240, 250);
            g.setFont(small);
            g.drawString(newText, 285, 300);
        }
        
    }
}