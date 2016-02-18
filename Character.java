
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Color;
import java.awt.Graphics;

public class Character extends Sprite
{
    private World world;
    private int x, y, w, h;
    
    public Character(int x, int y, int w, int h)
    {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public void update()
    {
        x += 1;
        y += 1;
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x, y, w, h);
    }
    
}
