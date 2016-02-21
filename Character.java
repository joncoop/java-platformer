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
    private Game game;
    private int vx, vy;
    private int runSpeed = 5;
    private int jumpPower = 20;
    
    public Character(int x, int y, BufferedImage img, Game game)
    {
        super(x, y, img);
        this.game = game;
        
        vx = 0;
        vy = 0;
    }

    public void processBlocks()
    {
        World world = game.getWorld();
        ArrayList<Sprite> blockList = world.getAllBlocks();
        ArrayList<Sprite> blockHitList;
        
        // apply horizontal movement
        move(vx, 0);
        
        blockHitList = getCollisionList(blockList);
        
        for (Sprite hit : blockHitList)
        {
            if (vx > 0)
                moveTo((int)(hit.getRect().getX() - this.getRect().getWidth()), (int)this.getRect().getY());
            else if (vx < 0)
                moveTo((int)(hit.getRect().getX() + hit.getRect().getWidth()), (int)this.getRect().getY());
        }
        
        // apply gravity
        vy += world.getGravity();
        move(0, vy);
   
        blockHitList = getCollisionList(blockList);
        
        for (Sprite hit : blockHitList)
        {
            if (vy > 0)
                moveTo((int)this.getRect().getX(), (int)(hit.getRect().getY() - this.getRect().getHeight()));
            else if (vy < 0)
                moveTo((int)this.getRect().getX(), (int)(hit.getRect().getY() + hit.getRect().getHeight()));
            vy = 0;
        }        
    }
    
    public void processEnemies()
    {
        
    }
    
    public void processCoins()
    {
        
    }
    
    public void checkWorldBoundaries()
    {
        World world = game.getWorld();
                
        if (this.getRect().getX() < world.getLeft())
           moveTo((int)world.getLeft(), (int)this.getRect().getY());
        else if (this.getRect().getX() + this.getRect().getWidth() > world.getRight())
           moveTo((int)(world.getRight() - this.getRect().getWidth()), (int)this.getRect().getY());
            
        if (this.getRect().getY() < world.getTop())
            moveTo((int)this.getRect().getX(), world.getTop());
        else if (this.getRect().getY() + this.getRect().getHeight() > world.getBottom())
            moveTo((int)this.getRect().getX(), (int)(world.getBottom() - this.getRect().getHeight()));        
    }
    
    public void update()
    {
        processBlocks();
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
