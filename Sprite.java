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
    
    public void move(int dx, int dy)
    {
        rect.translate(dx, dy);
    }
    
    public void moveTo(int x, int y)
    {
        rect.setLocation(x, y);
    }
    
    public void setRectTop(int top)
    {
        int x = (int) (rect.getX());
        int y = top;
        
        rect.setLocation(x, y);
    }
    
    public void setRectRight(int right)
    {
        int x = (int) (right - rect.getWidth());
        int y = (int) (rect.getY());
        
        rect.setLocation(x, y);
    }
    
    public void setRectBottom(int bottom)
    {
        int x = (int) (rect.getX());
        int y = (int) (bottom - rect.getHeight());
        
        rect.setLocation(x, y);
    }
    
    public void setRectLeft(int left)
    {
        int x = left;
        int y = (int) (rect.getY());
        
        rect.setLocation(x, y);
    }
    
    public int getRectTop()
    {
        return (int) (rect.getY());
    }
    
    public int getRectRight()
    {
        return (int) (rect.getX() + rect.getWidth());
    }
    
    public int getRectBottom()
    {
        return (int) (rect.getY() + rect.getHeight());
    }
    
    public int getRectLeft()
    {
        return (int) (rect.getX());
    }
    
    public boolean collidesWith(Sprite other)
    {
        return this.rect.intersects(other.rect);
    }
    
    public ArrayList<Sprite> getCollisionList(ArrayList<Sprite> spriteList) 
    {
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
