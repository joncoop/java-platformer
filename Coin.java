/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;

public class Coin extends Sprite
{
    private int value = 1;
    
    public Coin(int x, int y, BufferedImage img)
    {
        super(x, y, img);
    }
    
    public int getValue()
    {
        return value;
    }
    
    public void update()
    {
        
    }
}