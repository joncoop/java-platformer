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

public class InputHandler implements KeyListener
{
    Character player;
    Game game;
    
    public InputHandler(Character player, Game game)
    {
        this.player = player;
        this.game = game;
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getExtendedKeyCode();
        
        if (code == KeyEvent.VK_LEFT)
            player.moveLeft();
            
        else if (code == KeyEvent.VK_RIGHT)
           player.moveRight();
           
        else if (code == KeyEvent.VK_SPACE)
           player.jump();
 
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getExtendedKeyCode();
        
        if (code == KeyEvent.VK_LEFT)
            player.stop();
            
        else if (code == KeyEvent.VK_RIGHT)
            player.stop();
            
        else if (code == KeyEvent.VK_P)
            game.togglePause();
        
    }
    
    public void keyTyped(KeyEvent e) 
    {
        // do nothing
    }
}  
