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
    
    // game
    Game game;
    
    // world physics
    private double gravity = 1.0; // move to level (maybe for moon/underwater, etc)
        
    private Character character;
    
    private List<Level> levels;
    private Level currentLevel;
    private int levelNum = 0;
    
    public World(List<Level> levels, Game game) {
        this.levels = levels;
        this.game = game;
    }
    
    public void addCharacter(Character character) {
        this.character = character;
    }
    
    public int getLevelNum() {
        return levelNum; 
    }
    
    public Level getLevel() {
        return currentLevel;
    }
    
    public void advance() {
        levelNum++;
        currentLevel = levels.get(levelNum);
        currentLevel.load();
        currentLevel.start();
    }
    
    public int calculateShift() {
        int shift;
        
        if (character.getRectLeft() < Game.WIDTH / 2 * Game.SCALE)
            shift = 0;
        else if (character.getRectLeft() > currentLevel.getRight() - Game.WIDTH / 2 * Game.SCALE)
            shift = -(currentLevel.getRight() - Game.WIDTH * Game.SCALE);
        else
            shift = -(currentLevel.getLeft() + character.getRectLeft()) + Game.WIDTH / 2 * Game.SCALE;
        
        return shift;
    }
    
    public void paint(Graphics g) {
        int shift = calculateShift();
        
        
        g.translate(shift, 0);
        /*
        g.setColor(new Color(125, 200, 255)); // sky blue
        g.fillRect(currentLevel.getLeft(), currentLevel.getTop(), currentLevel.getRight(), currentLevel.getBottom());
        
        
        List<Sprite> spriteList = currentLevel.getAllSprites();
            
        for (Sprite s : spriteList)
            s.paint(g);
        */
        character.paint(g);
        
        g.translate(-shift, 0);
        
    }
    
    public void update() {
        
        List<Sprite> spriteList = currentLevel.getAllSprites();
            
        for (Sprite s : spriteList) {
            s.update();
        }
        
        character.update();

        if (currentLevel.isComplete()) {
             advance();
        }
    }
 
}