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

public class Character extends Entity {
    //public static BufferedImage img = ;

    private Game game;
    private Player player;
    private int runSpeed = 5;
    private int jumpPower = 20;
    
    public Character(Player player, Game game) throws IOException
    {
        super(0, 0, ImageIO.read(new File("img/baby_tux.png")), game.getWorld());
        this.game = game;
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
        World world = game.getWorld();
        Level level = world.getLevel();
        
        // nudge down 1 px
        move(0, 1);
        
        List<Sprite> blockList = level.getAllBlocks();
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
    
    public void die()
    {
        Player p = getPlayer();
        p.setLives(p.getLives() - 1);

    }
    
    public void moveAndProcessBlocks()
    {
        World world = game.getWorld();
        Level level = world.getLevel();

        List<Sprite> blockList = level.getAllBlocks();
        List<Sprite> hitList;
        
        int xVel = getVx();
        int yVel = getVy();
        
        // apply horizontal movement
        move(xVel, 0);

        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (xVel > 0)
                setRectRight(hit.getRectLeft());
            else if (xVel < 0)
                setRectLeft(hit.getRectRight());
        }
        
        // apply gravity and vertical movement
        applyGravity();
        move(0, yVel);
        
        hitList = getCollisionList(blockList);
        
        for (Sprite hit : hitList)
        {
            if (yVel > 0)
                setRectBottom(hit.getRectTop());
            else if (yVel < 0)
                setRectTop(hit.getRectBottom());
            setVy(0);
        }
    }
    
    public void processEnemies()
    {
        World world = game.getWorld();
        Level level = world.getLevel();

        List<Sprite> enemyList = level.getAllEnemies();
        List<Sprite> hitList = getCollisionList(enemyList);
        
        if (hitList.size() > 0)
            die();     
    }
    
    public void processCoins()
    {
       World world = game.getWorld();
       Level level = world.getLevel();

        List<Sprite> coinList = level.getAllCoins();
        List<Sprite> hitList = getCollisionList(coinList);
        
        for (Sprite hit : hitList)
        {
            player.addPoints(((Coin)(hit)).getValue());
            coinList.remove(hit);
        }  
    }
    
    public void processPowerUps()
    {
        World world = game.getWorld();
        Level level = world.getLevel();

        List<Sprite> powerUpList = level.getAllPowerUps();
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
        World world = game.getWorld();
        Level level = world.getLevel();

        if (getRectLeft() < level.getLeft())
            setRectLeft(level.getLeft());
        else if (getRectRight() > level.getRight())
            setRectRight(level.getRight());
            
        if (getRectTop() < level.getTop())
            setRectTop(level.getTop());
        else if (getRectTop() > level.getBottom() + 2 * Game.SCALE)
            die();
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