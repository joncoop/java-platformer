/**
 * Write a description of class SpriteGroup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class SpriteGroup
{
    ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
    
    public SpriteGroup()
    {
        
    }
    
    public void add(Sprite s)
    {
        spriteList.add(s);
    }
    
    public void add(ArrayList<Sprite> sprites)
    {
        for (Sprite s : sprites)
            spriteList.add(s);
    }
    
    public void paint(Graphics g)
    {
        for (Sprite s : spriteList)
            s.paint(g);
    }
    
    public void update()
    {
        for (Sprite s : spriteList)
            s.update();
    }
}
