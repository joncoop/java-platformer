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

public class Character extends Entity
{
    private World world;
    private Player player;
    private int runSpeed = 5;
    private int jumpPower = 20;
    
    public Character(int x, int y, BufferedImage img, World world, Player player)
    {
        super(x, y, img, world);
        this.world = world;
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }
    
    public void moveRight()
    {
        setVx(runSpeed);
    }

    public void moveLeft()
    {
        setVx(-runSpeed);
    }
    
    public void jump()
    {
        // nudge down 1 px
        move(0, 1);
        
        List<Sprite> blockList = world.getAllBlocks();
        List<Sprite> hitList = getCollisionList(blockList);
        
        // jump if intersects with block
        if (hitList.size() > 0)
            setVy(-jumpPower);
            
        // nudge back up to original position
        move(0, -1);
    }
    
    public void stop()
    {
        setVx(0);
    }
    
    public void moveAndProcessBlocks()
    {
        List<Sprite> blockList = world.getAllBlocks();
        List<Sprite> hitList;
        
        // apply horizontal movement
        move(getVx(), 0);
       
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (getVx() > 0)
                setRectRight(hit.getRectLeft());
            else if (getVx() < 0)
                setRectLeft(hit.getRectRight());
        }
        
        // apply gravity and vertical movement
        applyGravity();
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
    
    public void processEnemies()
    {
        
    }
    
    public void processCoins()
    {
        List<Sprite> coinList = world.getAllCoins();
        List<Sprite> hitList = getCollisionList(coinList);
        
        for (Sprite hit : hitList)
        {
            player.addPoints(((Coin)(hit)).getValue());
            coinList.remove(hit);
        }
        
    }
    
    public void processPowerUps()
    {
        List<Sprite> powerUpList = world.getAllPowerUps();
        List<Sprite> hitList = getCollisionList(powerUpList);
        
        for (Sprite hit : hitList)
        {
            PowerUp p = (PowerUp)hit;
            p.apply(this);
            powerUpList.remove(hit);
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
        processPowerUps();
        checkWorldBoundaries();
    }
}