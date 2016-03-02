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

public class Slime extends Enemy
{       
    public Slime(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img, world);
        
        setVx(-2);
    }
    
    public void moveAndProcessBlocks()
    {
        Level level = getWorld().getLevel();
        
        List<Sprite> blockList = level.getAllBlocks();
        List<Sprite> hitList;
        boolean rev;
        
        // apply horizontal movement
        move(getVx(), 0);
        rev = false;
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (getVx() > 0)
                setRectRight(hit.getRectLeft());
            else if (getVx() < 0)
                setRectLeft(hit.getRectRight());
                
            rev = true;
        }
        
        if (rev)
            reverse();
        
        // check for platform edges
        move(0, 1);
        rev = true;
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            setRectBottom(hit.getRectTop());
            
            if (getVx() < 0)
            {
                if (this.getRectLeft() >= hit.getRectLeft())
                    rev = false;
            }
            else if (getVx() > 0)
            {
                if (this.getRectRight() <= hit.getRectRight())
                    rev = false;
            }
        }
    
        if (rev)
            reverse(); 
    } 
    
    public void update()
    {
        moveAndProcessBlocks();
        checkWorldBoundaries();
    }
}