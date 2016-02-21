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
        
        if (game.getState() == game.START)
            game.setState(game.PLAYING);
        else if (code == KeyEvent.VK_P)
            game.togglePause();
    }
    
    public void keyTyped(KeyEvent e) 
    {
        // do nothing
    }
}