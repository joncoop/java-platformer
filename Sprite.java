/**
 * Abstract class Sprite - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Sprite
{
    private BufferedImage img;
    private Rectangle rect;
    
    public Sprite(int x, int y, BufferedImage img)
    {
        int w = img.getWidth();
        int h = img.getHeight();
        
        this.img = img;
        this.rect = new Rectangle(x, y, w, h);
    }
    
    public Rectangle getRect()
    {
        return rect;
    }
    
    public void move(int dx, int dy)
    {
        rect.translate(dx, dy);
    }
    
    public void moveTo(int x, int y)
    {
        rect.setLocation(x, y);
    }
    
    public boolean collidesWith(Sprite other)
    {
        return this.rect.intersects(other.rect);
    }
    
    public ArrayList<Sprite> getCollisionList(ArrayList<Sprite> spriteList) {
       ArrayList<Sprite> collidedSprites = new ArrayList<Sprite>();
       
       for (Sprite s : spriteList)
           if (this.collidesWith(s))
               collidedSprites.add(s);
        
       return collidedSprites;
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img, (int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), null);   
    }
    
    public abstract void update();
}
