
/**
 * Abstract class Sprite - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Sprite
{
    private Rectangle rect;
    
    public Sprite(Rectangle rect)
    {
        this.rect = rect;
    }
    
    public abstract void update();
    
    public abstract void paint(Graphics g);
    

}
