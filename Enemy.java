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
        Level level = getWorld().getLevel();

        if (getRectLeft() <= level.getLeft())
        {
            setRectLeft(level.getLeft());
            reverse();
        }
        else if (getRectRight() >= level.getRight())
        {
            setRectRight(level.getRight());
            reverse();
        }
            
        if (getRectTop() < level.getTop())
            setRectTop(level.getTop());
        else if (getRectBottom() > level.getBottom())
            setRectBottom(level.getBottom());
    }
}