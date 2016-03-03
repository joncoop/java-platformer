/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;

public abstract class Entity extends Sprite {
    private int startX, startY;
    private int vx, vy;
    private World world;
    
    public Entity(int x, int y, BufferedImage img, World world) {
        super(x, y, img);
        
        this.startX = x;
        this.startY = y;
        this.world = world;
        
        this.vx = 0;
        this.vy = 0;
    }
    
    public World getWorld() { return world; }
    public int getVx()      { return vx; }
    public int getVy()      { return vy;}
    
    public void setVx(int vx) {
        this.vx = vx;
    }
    
    public void setVy(int vy) {
        this.vy = vy;
    }
    
    public void applyGravity() {
        vy += world.getGravity();
    }
    
    public void reverse() {
        vx *= -1;
    }

}