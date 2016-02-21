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
    
    public InputHandler(Character player)
    {
        this.player = player;
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
}
