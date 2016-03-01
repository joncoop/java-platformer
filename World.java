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
import java.util.Collections;
import java.util.Scanner;
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
    
    // player start
    private int characterStartX, characterStartY;
    
    // world physics
    private double gravity = 1.0;
    
    // world objects
    private Sprite character;
    private List<Sprite> startBlockList = new ArrayList<Sprite>();
    private List<Sprite> startCoinList = new ArrayList<Sprite>();
    private List<Sprite> startEnemyList = new ArrayList<Sprite>();
    private List<Sprite> startPowerUpList = new ArrayList<Sprite>();
    
    private List<Sprite> blockList = new ArrayList<Sprite>();
    private List<Sprite> coinList = new ArrayList<Sprite>();
    private List<Sprite> enemyList = new ArrayList<Sprite>();
    private List<Sprite> powerUpList = new ArrayList<Sprite>();    
    // images
    private BufferedImage blockImg = ImageIO.read(new File("img/block.png"));
    private BufferedImage coinImg = ImageIO.read(new File("img/coin.png"));
    private BufferedImage slimeImg = ImageIO.read(new File("img/slime.png"));
    private BufferedImage monsterImg = ImageIO.read(new File("img/monster.png"));
    private BufferedImage oneUpImg = ImageIO.read(new File("img/potion.png"));
    
    private List<String> tokens = new ArrayList<String>();
    
    private String[] levelFiles = {"data/level1.txt",
                                   "data/level2.txt",
                                   "data/level3.txt"};
    private int currentLevel = 0;
    
    public World() throws IOException
    {
        load(levelFiles[currentLevel]);
    }
    
    public void load(String fileName) throws IOException
    {
        Scanner inFile1 = new Scanner(new File(fileName)).useDelimiter("\n");
        
        while (inFile1.hasNext()) {
            tokens.add(inFile1.nextLine());
        }
        
        int longest = 0;
        
        int row = 0;
        for (String t : tokens) {
            for (int col=0; col < t.length(); col++) {
                if (t.charAt(col) == '1') {
                    this.characterStartX = col * Game.SCALE;
                    this.characterStartY = row * Game.SCALE;
                }
                else if (t.charAt(col) == 'B') {
                    startBlockList.add(new Block(col * Game.SCALE, row * Game.SCALE, blockImg, this));
                }
                else if (t.charAt(col) == 'S') {
                    startEnemyList.add(new Slime(col * Game.SCALE, row * Game.SCALE, slimeImg, this));
                }
                else if (t.charAt(col) == 'M') {
                    startEnemyList.add(new Monster(col * Game.SCALE, row * Game.SCALE, monsterImg, this));
                }
                else if (t.charAt(col) == 'C') {
                    startCoinList.add(new Coin(col * Game.SCALE, row * Game.SCALE, coinImg, this));
                }
                else if (t.charAt(col) == 'U') {
                    startPowerUpList.add(new OneUp(col * Game.SCALE, row * Game.SCALE, oneUpImg, this));
                }
                
                longest = Math.max(longest, t.length());
            }
            
            row++;
        }        
        
        // set boundaries
        this.top = 0;
        this.left = 0;
        this.bottom = tokens.size() * Game.SCALE;
        this.right = longest * 64;
        
        // initial state has all entities
        blockList = new ArrayList<Sprite>(startBlockList);
        coinList = new ArrayList<Sprite>(startCoinList);
        enemyList = new ArrayList<Sprite>(startEnemyList);
        powerUpList = new ArrayList<Sprite>(startPowerUpList);
    }
    
    public void reset()
    {
        ((Entity)character).reset();
        
        for (Sprite block : blockList)
            ((Entity)block).reset();
            
        for (Sprite enemy : enemyList)
            ((Entity)enemy).reset();
            
        for (Sprite coin : coinList)
            ((Entity)coin).reset();
            
        for (Sprite powerUp : powerUpList)
            ((Entity)powerUp).reset();
    }
    
    public void addPlayer(Character character)
    {
        this.character = character;
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
    
    public int getPlayerStartX()
    {
        return characterStartX;
    }
    
    public int getPlayerStartY()
    {
        return characterStartY;
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

    public List<Sprite> getAllEnemies()
    {   
        return enemyList;
    }

    public int getLevel()
    {
        return currentLevel + 1;
    }
    
    public void paint(Graphics g)
    {
        int shift;
        
        if (character.getRectLeft() < Game.WIDTH / 2 * Game.SCALE)
            shift = 0;
        else if (character.getRectLeft() > this.right - Game.WIDTH / 2 * Game.SCALE)
            shift = -(this.right - Game.WIDTH * Game.SCALE);
        else
            shift = -(this.left + character.getRectLeft()) + Game.WIDTH / 2 * Game.SCALE;
            
        g.translate(shift, 0);
        
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
            
        character.paint(g);
        
        g.translate(-shift, 0);
    }
    
    public void update() throws IOException
    {
        character.update();
        
        for (Sprite enemy : enemyList)
            enemy.update();
            
        if (character.getRectRight() == this.right)
        {
             currentLevel++;
             load(levelFiles[currentLevel]);
             reset();
        }
    }
}