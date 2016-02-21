/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;

public class Enemy extends Sprite
{
    private World world;
    
    public Enemy(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img);
        this.world = world;
    }

    public void moveAndProcessBlocks()
    {
        
    }
    
    public void checkWorldBoundaries()
    {                
        if (getRectLeft() < world.getLeft())
            setRectLeft(world.getLeft());
        else if (getRectRight() > world.getRight())
            setRectRight(world.getRight());
            
        if (getRectTop() < world.getTop())
            setRectTop(world.getTop());
        else if (getRectBottom() > world.getBottom())
            setRectBottom(world.getBottom());
    }
    
    public void update()
    {
        moveAndProcessBlocks();
        checkWorldBoundaries();
    }
}
