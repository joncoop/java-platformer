/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class StatsOverlay
{
    private static Font small = new Font("Serif", Font.PLAIN, 32);
    private static Font medium = new Font("Serif", Font.PLAIN, 64);
    private static Font large = new Font("Serif", Font.PLAIN, 96);
    
    private Game game;
    
    public StatsOverlay(Game game)
    {
        this.game = game;
    }

    public void paint(Graphics g)
    {
        int score = game.getPlayer().getScore();
        int lives = game.getPlayer().getLives();
        int level = game.getWorld().getLevelNum();
        int state = game.getState();
        
        String scoreText = "Score: " + score;
        String livesText = "Lives: " + lives;
        String levelText = "Level: " + level;
        String startText = "Press any key to start.";
        String pauseText = "Press 'p' to resume.";
        String overText = "Game Over";
        String newText = "Press 'n' to start a new game.";    

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(scoreText, 32, 64);
        g.drawString(livesText, 32, 96);
        g.drawString(levelText, 32, 128);
        
        if (state == Game.START) {
            g.setFont(large);
            g.drawString(Game.TITLE, 220, 250);
            g.setFont(small);
            g.drawString(startText, 320, 300);
        }
        else if (state == Game.PAUSED) {
            g.setFont(small);
            g.drawString(pauseText, 340, 280);
        }
        else if (state == Game.OVER) {
            g.setFont(large);
            g.drawString(overText, 240, 250);
            g.setFont(small);
            g.drawString(newText, 285, 300);
        }       
    }
}