/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public abstract class Enemy extends Entity
{
    public Enemy(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img, world);

    }

    // common enemy behaviors
    public void checkWorldBoundaries()
    {                
        if (getRectLeft() <= getWorld().getLeft())
        {
            setRectLeft(getWorld().getLeft());
            reverse();
        }
        else if (getRectRight() >= getWorld().getRight())
        {
            setRectRight(getWorld().getRight());
            reverse();
        }
            
        if (getRectTop() < getWorld().getTop())
            setRectTop(getWorld().getTop());
        else if (getRectBottom() > getWorld().getBottom())
            setRectBottom(getWorld().getBottom());
    }
}