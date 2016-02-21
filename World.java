/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class World
{
    private int top;
    private int bottom;
    private int right;
    private int left;
    private double gravity = 1.0;
    
    private List<Sprite> blockList = new ArrayList<Sprite>();
    private List<Sprite> coinList = new ArrayList<Sprite>();
    private List<Sprite> enemyList = new ArrayList<Sprite>();
            
    public World() throws IOException
    {
        this.top = 0;
        this.bottom = 9 * 64;
        this.left = 0;
        this.right = 16 * 64;
        
        // make blocks (can load from file later)
        BufferedImage blockImg = ImageIO.read(new File("img/block.png"));
        
        int x = 0;
        int y = 550;
        
        while (x < right)
        {
            blockList.add(new Block(x, 8 * 64, blockImg));
            x += 64;
        }
        
        blockList.add(new Block(3 * 64, 6 * 64, blockImg));
        blockList.add(new Block(10 * 64, 7 * 64, blockImg));
        
        // make some coins
        BufferedImage coinImg = ImageIO.read(new File("img/coin.png"));
        
        coinList.add(new Coin(3 * 64, 2 * 64, coinImg));
        coinList.add(new Coin(5 * 64, 4 * 64, coinImg));
        coinList.add(new Coin(10 * 64, 5 * 64, coinImg));       

        // make some enemies
        BufferedImage enemyImg = ImageIO.read(new File("img/slime.png"));
        
        enemyList.add(new Enemy(3 * 64, 7 * 64, enemyImg, this));      
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
    
    public void paint(Graphics g)
    {
        for (Sprite block : blockList)
            block.paint(g);
            
        for (Sprite coin : coinList)
            coin.paint(g);
            
        for (Sprite enemy : enemyList)
            enemy.paint(g);
    }
    
    public void update()
    {
        for (Sprite enemy : enemyList)
            enemy.update();        
    }
}
