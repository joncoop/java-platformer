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

public class Monster extends Enemy
{   
    int vx, vy;
    
    public Monster(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img, world);
        
        vx = -2;
        vy = 0;
    }
    
    public void moveAndProcessBlocks()
    {
        List<Sprite> blockList = getWorld().getAllBlocks();
        List<Sprite> hitList;
        boolean rev;
        
        // apply horizontal movement
        move(vx, 0);
        rev = false;
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (vx > 0)
                setRectRight(hit.getRectLeft());
            else if (vx < 0)
                setRectLeft(hit.getRectRight());
                
            rev = true;
        }
        
        if (rev)
            reverse();
        
        // apply gravity and vertical movement
        vy += getWorld().getGravity();
        move(0, vy);
        
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (vy > 0)
                setRectBottom(hit.getRectTop());
            else if (vy < 0)
                setRectTop(hit.getRectBottom());
            vy = 0;
        }
    } 

    public void update()
    {
        moveAndProcessBlocks();
        checkWorldBoundaries();
    }
}