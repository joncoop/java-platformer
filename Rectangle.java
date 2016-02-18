
/**
 * Write a description of class Rect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rectangle
{
    private int x, y, width, height;

    public Rectangle(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getTop()
    {
        return y;
    }

    public int getRight()
    {
        return x + width;
    }
    
    public int getBottom()
    {
        return y + height;
    }

    public int getLeft()
    {
        return x;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight() 
    {
        return height;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setTop(int top)
    {
        y = top;
    }

    public void setRight(int right)
    {
        x = right - width;
    }
    
    public void setBottom(int bottom)
    {
        y = bottom - height;
    }

    public void setLeft(int left)
    {
        x = left;
    }
}
