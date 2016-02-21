/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;

public class Block extends Sprite
{
    public Block(int x, int y, BufferedImage img)
    {
        super(x, y, img);
    }
    
    public void update()
    {
        // blocks don't do anything
    }
    
}