/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;

public class Coin extends Entity
{
    private int value = 1;
    
    public Coin(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img, world);
    }
    
    public int getValue()
    {
        return value;
    }
    
    public void update()
    {
        // do nothing
    }
}