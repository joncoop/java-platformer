
/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;

public abstract class Entity extends Sprite
{
    private int vx, vy;
    
    public Entity(int x, int y, BufferedImage img)
    {
        super(x, y, img);
        
        vx = 0;
        vy = 0;
    }
    
    public int getVx()
    {
        return vx;
    }
    
    public int getVy()
    {
        return vy;
    }
    
    public void setVx(int vx)
    {
        this.vx = vx;
    }
    
    public void setVy(int vy)
    {
        this.vy = vy;
    }
    
    public void reverse()
    {
        vx *= -1;
    }
}
