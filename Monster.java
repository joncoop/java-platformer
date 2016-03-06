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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Monster extends Enemy
{   
    public Monster(int x, int y, World world) throws IOException
    {
        super(x, y, ImageIO.read(new File("img/monster.png")), world);
        
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
        
        // apply gravity and vertical movement
        applyGravity();;
        move(0, getVy());
        
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (getVy() > 0)
                setRectBottom(hit.getRectTop());
            else if (getVy() < 0)
                setRectTop(hit.getRectBottom());
            setVy(0);
        }
    } 

    public void update()
    {
        moveAndProcessBlocks();
        checkWorldBoundaries();
    }
}