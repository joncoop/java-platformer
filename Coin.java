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

public class Coin extends Enemy
{   
    private int value = 1;
    
    public Coin(int x, int y, World world) throws IOException
    {
        super(x, y, ImageIO.read(new File("img/coin.png")), world);
    }
    
    public int getValue()
    {
        return value;
    }
    
    public void update()
    {
        // do nothing
    }
}