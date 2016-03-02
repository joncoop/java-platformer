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
import java.io.IOException;

public class World {  
    
    // world physics
    private double gravity = 1.0;
        
    private Character character;
    
    private String[] levelFiles = {"data/level1.txt",
                                   "data/level2.txt",
                                   "data/level3.txt"};
    private Level level;
    private int levelNum = 0;
    
    public World() throws IOException {
        this.level = new Level(levelFiles[0], this);
        level.load();
    }
    
    public void addPlayer(Character character) {
        this.character = character;
    }
    
    public double getGravity() { return gravity; }
    public Level getLevel()    { return level; }
    public int getLevelNum()   { return levelNum; }
    
    public void advance() {
        levelNum++;
    }
    
    public int calculateShift() {
        int shift;
        
        if (character.getRectLeft() < Game.WIDTH / 2 * Game.SCALE)
            shift = 0;
        else if (character.getRectLeft() > level.getRight() - Game.WIDTH / 2 * Game.SCALE)
            shift = -(level.getRight() - Game.WIDTH * Game.SCALE);
        else
            shift = -(level.getLeft() + character.getRectLeft()) + Game.WIDTH / 2 * Game.SCALE;
        
        return shift;
    }
    
    public void paint(Graphics g) {
        int shift = calculateShift();
        
        g.translate(shift, 0);
        
        g.setColor(new Color(125, 200, 255)); // sky blue
        g.fillRect(level.getLeft(), level.getTop(), level.getRight(), level.getBottom());
            
        List<Sprite> spriteList = level.getAllSprites();
            
        for (Sprite s : spriteList)
            s.paint(g);
            
        character.paint(g);
        
        g.translate(-shift, 0);
    }
    
    public void update() {
        
        List<Sprite> spriteList = level.getAllSprites();
            
        character.update();

        for (Sprite s : spriteList) {
            s.update();
        }
            
        if (character.getRectRight() == level.getRight()) {
             // advance to next level
        }
    }
}