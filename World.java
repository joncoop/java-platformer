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
    
    List<String> tokens = new ArrayList<String>();
    
    public World() throws IOException
    {
        Scanner inFile1 = new Scanner(new File("data/level1.txt")).useDelimiter("\n");
        
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
                    blockList.add(new Block(col * Game.SCALE, row * Game.SCALE, blockImg, this));
                }
                else if (t.charAt(col) == 'S') {
                    enemyList.add(new Slime(col * Game.SCALE, row * Game.SCALE, slimeImg, this));
                }
                else if (t.charAt(col) == 'M') {
                    enemyList.add(new Monster(col * Game.SCALE, row * Game.SCALE, monsterImg, this));
                }
                else if (t.charAt(col) == 'C') {
                    coinList.add(new Coin(col * Game.SCALE, row * Game.SCALE, coinImg, this));
                }
                else if (t.charAt(col) == 'U') {
                    powerUpList.add(new OneUp(col * Game.SCALE, row * Game.SCALE, oneUpImg, this));
                }
                
                longest = Math.max(longest, t.length());
            }
            
            row++;
        }        
        
        // set boundaries
        this.top = 0;
        this.left = 0;
        this.bottom = tokens.size() * Game.SCALE;
        this.right = 16 * 64;
    }
    
    public void load()
    {
        // reads file and stores locations of Entites
    }
    
    public void reset()
    {
        // need to save locations when file is read so reloading isn't necessary.
        // should save lots of ioexception handling.
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
            
        character.paint(g);
    }
    
    public void update()
    {
        character.update();
        
        for (Sprite enemy : enemyList)
            enemy.update();        
    }
}