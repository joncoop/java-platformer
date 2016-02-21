/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public abstract class Sprite
{
    private BufferedImage img;
    private int x, y;
    
    public Sprite(int x, int y, BufferedImage img)
    {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    
    public void move(int dx, int dy)
    {
        x += dx;
        y += dy;
    }
    
    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setRectTop(int top)
    {
        y = top;
    }
    
    public void setRectRight(int right)
    {
        x = right - img.getWidth();
    }
    
    public void setRectBottom(int bottom)
    {
        y = bottom - img.getHeight();
    }
    
    public void setRectLeft(int left)
    {
        x = left;
    }
    
    public int getRectTop()
    {
        return y;
    }
    
    public int getRectRight()
    {
        return x + img.getWidth();
    }
    
    public int getRectBottom()
    {
        return y + img.getHeight();
    }
    
    public int getRectLeft()
    {
        return x;
    }
    
    public boolean collidesWith(Sprite other)
    {
        return !( this.getRectLeft()   >= other.getRectRight()  ||
                  this.getRectRight()  <= other.getRectLeft()   ||
                  this.getRectTop()    >= other.getRectBottom() ||
                  this.getRectBottom() <= other.getRectTop() );
    }
    
    public List<Sprite> getCollisionList(List<Sprite> spriteList) 
    {
        List<Sprite> collidedSprites = new ArrayList<Sprite>();
        
        for (Sprite s : spriteList)
            if (this.collidesWith(s))
                collidedSprites.add(s);
        
        return collidedSprites;
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);   
    }
    
    public abstract void update();
}