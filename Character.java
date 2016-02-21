/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character extends Sprite
{
    private Game game;
    private int vx, vy;
    private int runSpeed = 5;
    private int jumpPower = 20;
    
    public Character(int x, int y, BufferedImage img, Game game)
    {
        super(x, y, img);
        this.game = game;
        
        vx = 0;
        vy = 0;
    }

    public void update()
    {
        World world = game.getWorld();

        // apply gravity
        vy += world.getGravity();
        
        // check world boundaries
        int nextX = (int)getRect().getX() + vx;
        int nextY = (int)getRect().getY() + vy;
        int width = (int)getRect().getWidth();
        int height = (int)getRect().getHeight();
        
        if (nextX < world.getLeft())
            nextX = world.getLeft();
        else if (nextX + width > world.getRight())
            nextX = world.getRight() - width;
            
        if (nextY < world.getTop())
            nextY = world.getTop();
        else if (nextY + height > world.getBottom())
            nextY = world.getBottom() - height;
        
            
        // process blocks
        /* not working because of type errors, need SpriteGroup
        ArrayList<Block> blockList = world.getAllBlocks();
        ArrayList<Sprite> blockHitList = getCollisionList(blockList);
        
        for (Block hit : blockHitList)
            // do something
            ;
         */
        
        moveTo(nextX, nextY);
    }
    
    public void moveRight()
    {
        vx = runSpeed;
    }

    public void moveLeft()
    {
        vx = -runSpeed;
    }
    
    public void jump()
    {
        vy = -jumpPower;
    }
    
    public void stop()
    {
        vx = 0;
    }
}
