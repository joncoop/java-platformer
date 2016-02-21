/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;

public class Block extends Sprite
{
    public Block(int x, int y, BufferedImage img)
    {
        super(x, y, img);
    }
    
    public void update()
    {
        // blocks don't do anything
    }
    
}