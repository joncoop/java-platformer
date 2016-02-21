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

public class Character extends Sprite
{
    private World world;
    private int vx, vy;
    private int runSpeed = 5;
    private int jumpPower = 20;
    
    public Character(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img);
        this.world = world;
        
        vx = 0;
        vy = 0;
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
        }
        
        // apply gravity and vertical movement
        vy += world.getGravity();
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
    
    public void processEnemies()
    {
        
    }
    
    public void processCoins()
    {
        ArrayList<Sprite> coinList = world.getAllCoins();
        ArrayList<Sprite> hitList = getCollisionList(coinList);
        
        for (Sprite coin : hitList)
        {
            coinList.remove(coin);
        }
        
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
        processEnemies();
        processCoins();
        checkWorldBoundaries();
    }
    
    public void moveRight()
    {
        vx = runSpeed;
    }

    public void moveLeft()
    {
        vx = -runSpeed;
    }
    
    public void jump()
    {
        vy = -jumpPower;
    }
    
    public void stop()
    {
        vx = 0;
    }
}
