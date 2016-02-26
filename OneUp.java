/**
 * Write a description of class ExtraLife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;

public class OneUp extends PowerUp
{
    public OneUp(int x, int y, BufferedImage img, World world)
    {
        super(x, y, img, world);
    }
    
    public void apply(Character character)
    {
        Player player = character.getPlayer();
        player.setLives(player.getLives() + 1);
    }
    
    public void update()
    {
        
    }
}