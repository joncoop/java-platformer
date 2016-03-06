/**
 * Write a description of class ExtraLife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OneUp extends Enemy
{   
    public OneUp(int x, int y, World world) throws IOException
    {
        super(x, y, ImageIO.read(new File("img/potion.png")), world);
    }
    
    public void apply(Character character)
    {
        Player player = character.getPlayer();
        player.setLives(player.getLives() + 1);
    }
    
    public void update()
    {
        // do nothing
    }
}