/**
 * Write a description of class World here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class World
{
    private int top;
    private int bottom;
    private int right;
    private int left;
    private double gravity = 1.0;
    
    private ArrayList<Sprite> blockList = new ArrayList<Sprite>();
            
    public World() throws IOException
    {
        this.top = 0;
        this.bottom = 600;
        this.left = 0;
        this.right = 800;
        
        // make blocks (can load from file later)
        BufferedImage blockImg = ImageIO.read(new File("img/block.png"));
        
        int x = 0;
        int y = 550;
        
        while (x < 800)
        {
            Block b = new Block(x, 500, blockImg);
            blockList.add(b);
            x += b.getRect().getWidth();
        }
    }

    public int getTop()
    {
        return top;
    }

    public int getRight()
    {
        return right;
    }

    public int getBottom()
    {
        return bottom;
    }
    
    public int getLeft()
    {
        return left;
    }
    
    public double getGravity()
    {
        return gravity;
    }
    
    public ArrayList<Sprite> getAllBlocks()
    {   
        return blockList;
    } 
}
