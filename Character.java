
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;


public class Character extends Sprite
{
    private World world;
    private int vx, vy;
    
    public Character(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img);
        this.world = world;
        
        vx = 0;
        vy = 0;
    }

    public void update()
    {
        move(vx, vy);
    }
    
    public void setVx(int vx)
    {
        this.vx = vx;
    }

    public void setVy(int vy)
    {
        this.vy = vy;
    }
}
