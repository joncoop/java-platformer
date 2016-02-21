/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Sprite
{
    private World world;
    private int vx, vy;
    
    public Enemy(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img);
        this.world = world;
        
        vx = -2;
    }

    public void reverse()
    {
        vx *= -1;
    }
    
    public void moveAndProcessBlocks()
    {
        ArrayList<Sprite> blockList = world.getAllBlocks();
        ArrayList<Sprite> hitList;
        
        // apply horizontal movement
        move(vx, 0);
       
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (vx > 0)
                setRectRight(hit.getRectLeft());
            else if (vx < 0)
                setRectLeft(hit.getRectRight());
                
            reverse();
        }
                   
    }
    
    public void checkWorldBoundaries()
    {                
        if (getRectLeft() < world.getLeft())
        {
            setRectLeft(world.getLeft());
            reverse();
        }
        else if (getRectRight() > world.getRight())
        {
            setRectRight(world.getRight());
            reverse();
        }
            
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
