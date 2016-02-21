
/**
 * Write a description of class World here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class World
{
    private int top;
    private int bottom;
    private int right;
    private int left;
    private double gravity = 1.0;
    
    public World()
    {
        this.top = 0;
        this.bottom = 600;
        this.left = 0;
        this.right = 800;
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
}
