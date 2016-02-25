/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class World
{
    // world boundaries
    private int top;
    private int bottom;
    private int right;
    private int left;
    
    // world physics
    private double gravity = 1.0;
    
    // world objects
    private Sprite player;
    private List<Sprite> blockList = new ArrayList<Sprite>();
    private List<Sprite> coinList = new ArrayList<Sprite>();
    private List<Sprite> enemyList = new ArrayList<Sprite>();
    private List<Sprite> powerUpList = new ArrayList<Sprite>();
    
    public World() throws IOException
    {
        /*
         * Everything in this constructor can load from a file later.
         */
        
        // set boundaries
        this.top = 0;
        this.bottom = 9 * 64;
        this.left = 0;
        this.right = 16 * 64;
        
        // make blocks
        BufferedImage blockImg = ImageIO.read(new File("img/block.png"));
        int x = 0;
        int y = 550;
        while (x < right)
        {
            blockList.add(new Block(x, 8 * 64, blockImg, this));
            x += 64;
        }
        blockList.add(new Block(3 * 64, 6 * 64, blockImg, this));
        blockList.add(new Block(10 * 64, 7 * 64, blockImg, this));

        blockList.add(new Block(10 * 64, 3 * 64, blockImg, this));
        blockList.add(new Block(11 * 64, 3 * 64, blockImg, this));
        blockList.add(new Block(12 * 64, 3 * 64, blockImg, this));
        blockList.add(new Block(13 * 64, 3 * 64, blockImg, this));
        
        // make some coins
        BufferedImage coinImg = ImageIO.read(new File("img/coin.png"));
        coinList.add(new Coin(3 * 64, 2 * 64, coinImg, this));
        coinList.add(new Coin(5 * 64, 4 * 64, coinImg, this));
        coinList.add(new Coin(10 * 64, 5 * 64, coinImg, this));       

        // make some enemies
        BufferedImage slimeImg = ImageIO.read(new File("img/slime.png")); 
        enemyList.add(new Slime(3 * 64, 7 * 64, slimeImg, this));      
        enemyList.add(new Slime(11 * 64, 2 * 64, slimeImg, this));      

        BufferedImage monsterImg = ImageIO.read(new File("img/monster.png")); 
        enemyList.add(new Monster(13 * 64, 2 * 64, monsterImg, this));      

        // add a powerup
        BufferedImage powerUpImg = ImageIO.read(new File("img/potion.png")); 
        powerUpList.add(new ExtraLife(14 * 64, 7 * 64, powerUpImg, this));      
        
    }
    
    public void addPlayer(Character player)
    {
        this.player = player;
    }

    public int getTop()
    {
        return top;
    }

    public int getRight()
    {
        return right;
    }

    public int getBottom()
    {
        return bottom;
    }
    
    public int getLeft()
    {
        return left;
    }
    
    public double getGravity()
    {
        return gravity;
    }
    
    public List<Sprite> getAllBlocks()
    {   
        return blockList;
    }
    
    public List<Sprite> getAllCoins()
    {   
        return coinList;
    }
    
    public List<Sprite> getAllPowerUps()
    {   
        return powerUpList;
    }
    
    public void paint(Graphics g)
    {
        g.setColor(new Color(125, 200, 255)); // sky blue
        g.fillRect(left, top, right, bottom);
            
        for (Sprite block : blockList)
            block.paint(g);
            
        for (Sprite coin : coinList)
            coin.paint(g);
            
        for (Sprite enemy : enemyList)
            enemy.paint(g);
            
        for (Sprite powerUp : powerUpList)
            powerUp.paint(g);
            
        player.paint(g);
    }
    
    public void update()
    {
        player.update();
        
        for (Sprite enemy : enemyList)
            enemy.update();        
    }
}