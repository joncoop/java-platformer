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

public abstract class Enemy extends Sprite
{
    private World world;
    private int vx, vy;
    
    public Enemy(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img);
        this.world = world;
        
        vx = -2;
    }

    public World getWorld()
    {
        return world;
    }
}