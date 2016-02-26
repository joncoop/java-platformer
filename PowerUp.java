/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.image.BufferedImage;

public abstract class PowerUp extends Entity
{
    public PowerUp(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img, world);
    }
    
    public abstract void apply(Character character);
    
    public abstract void update();
}