/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Level {
    
    // level data file
    String fileName;
    
    // world
    World world;
    
    // level boundaries
    private int top;
    private int bottom;
    private int right;
    private int left;

    // player start
    private int characterStartX, characterStartY;

    // entities
    private List<Sprite> blockList;
    private List<Sprite> coinList;
    private List<Sprite> enemyList;
    private List<Sprite> powerUpList;
    
    // images
    private BufferedImage blockImg = ImageIO.read(new File("img/block.png"));
    private BufferedImage coinImg = ImageIO.read(new File("img/coin.png"));
    private BufferedImage slimeImg = ImageIO.read(new File("img/slime.png"));
    private BufferedImage monsterImg = ImageIO.read(new File("img/monster.png"));
    private BufferedImage oneUpImg = ImageIO.read(new File("img/potion.png"));
    
    public Level(String fileName, World world) throws IOException {
        this.fileName = fileName;
        this.world = world;
    }

    public void load() throws IOException {
        blockList = new ArrayList<Sprite>();
        coinList = new ArrayList<Sprite>();
        enemyList = new ArrayList<Sprite>();
        powerUpList = new ArrayList<Sprite>();
        
        Scanner dataFile = new Scanner(new File(fileName)).useDelimiter("\n");
        
        List<String> lines = new ArrayList<String>();

        while (dataFile.hasNext()) {
            lines.add(dataFile.nextLine());
        }
        
        int longest = 0;
        
        int row = 0;
        for (String line : lines) {
            for (int col=0; col < line.length(); col++) {
                int x = col * Game.SCALE;
                int y = row * Game.SCALE;
                
                char c = line.charAt(col);
                
                if (c == '1') {
                    this.characterStartX = x;
                    this.characterStartY = y;
                }
                else if (c == 'B') { blockList.add(new Block(x, y, blockImg, world)); }
                else if (c == 'S') { enemyList.add(new Slime(x, y, slimeImg, world)); }
                else if (c == 'M') { enemyList.add(new Monster(x, y, monsterImg, world)); }
                else if (c == 'C') { coinList.add(new Coin(x, y, coinImg, world)); }
                else if (x == 'U') { powerUpList.add(new OneUp(x, y, oneUpImg, world)); }
                
                longest = Math.max(longest, line.length());
            }
            
            row++;
        }        
 
        // set boundaries
        this.top = 0;
        this.left = 0;
        this.bottom = lines.size() * Game.SCALE;
        this.right = longest * Game.SCALE;
    }

    public int getTop()    { return top; }
    public int getRight()  { return right; }
    public int getBottom() { return bottom; }
    public int getLeft()   { return left; }
    
    public int getPlayerStartX() { return characterStartX; }
    public int getPlayerStartY() { return characterStartY; }
    
    public List<Sprite> getAllBlocks()   { return blockList; }
    public List<Sprite> getAllCoins()    { return coinList; } 
    public List<Sprite> getAllPowerUps() { return powerUpList; }
    public List<Sprite> getAllEnemies()  { return enemyList; }
    
    public List<Sprite> getAllSprites() {
        List<Sprite> allSprites = new ArrayList<Sprite>();
        allSprites.addAll(blockList);
        allSprites.addAll(coinList);
        allSprites.addAll(enemyList);
        allSprites.addAll(powerUpList);
        
        return allSprites;
    }
    
    public void reset() {
        //load();
    }
}