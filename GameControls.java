/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener
{
    Game game;
    
    public GameControls(Game game)
    {
        this.game = game;
    }
    
    public void keyPressed(KeyEvent e) 
    {
        // do nothing
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
        int state = game.getState();
        
        if (state == Game.START)
        {
            // any key starts
            game.setState(Game.PLAYING);
        }
        else if (state == Game.PLAYING || state == Game.PAUSED)
        {
            if (code == KeyEvent.VK_P)
                game.togglePause();
        }
        else if (state == Game.OVER)
        {
            if (code == KeyEvent.VK_N)
                game.reset();
        }
    }
    
    public void keyTyped(KeyEvent e) 
    {
        // do nothing
    }
}