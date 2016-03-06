/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.io.IOException;

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
       
    private GameFrame frame;
    private Player player;
    private Character character;
    private List<Level> levels;
    private World world;
    private int state;
    
    private String[] levelFileNames = {"data/level1.txt",
                                       "data/level2.txt", 
                                       "data/level3.txt"};
                                                              
    public Game() throws IOException
    {
        // set panel size
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        // make Jframe
        frame = new GameFrame(TITLE, this);
        
        // make player
        player = new Player(this);
        
        // make character
        character = new Character(player, this);

        // make levels
        levels = new ArrayList<Level>();
        for (String fileName : levelFileNames) {
            Level level = new Level(fileName, this);
            levels.add(level);
        }
        
        // make world
        world = new World(levels, this);
        world.addCharacter(character);
                
        // add input handlers
        addKeyListener(new GameControls(this));
        addKeyListener(new CharacterControls(character, this));
        
        // set game state
        state = START;
        
        // request focus
        requestFocus();
    }
     
    public Player getPlayer() {
        return player;
    }
    
    public Character getCharacter() {
        return character;
    }
    
    public World getWorld() {
        return world;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public void togglePause() {
        if (state == PLAYING)
            state = PAUSED;
        else if (state == PAUSED)
            state = PLAYING;
    }

    public void reset() {
        // not implemented;
    }
    
    public void play() throws InterruptedException {
        while (true)
        {
            if (state == PLAYING) {
                world.update();
            }
            
            repaint();  
            Thread.sleep(10);
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //world.paint(g);
        
        // all of the code below would probably be better in a GameStats class
        String scoreText = "Score: " + player.getScore();
        String livesText = "Lives: " + player.getLives();
        String levelText = "Level: " + world.getLevelNum();
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
        g.drawString(levelText, 32, 128);
        
        if (state == START) {
            g.setFont(large);
            g.drawString(TITLE, 220, 250);
            g.setFont(small);
            g.drawString(startText, 320, 300);
        }
        else if (state == PAUSED) {
            g.setFont(small);
            g.drawString(pauseText, 340, 280);
        }
        else if (state == OVER) {
            g.setFont(large);
            g.drawString(overText, 240, 250);
            g.setFont(small);
            g.drawString(newText, 285, 300);
        }    
    }
}