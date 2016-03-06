
/**
 * Write a description of class GameFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GameFrame extends JFrame
{
    public GameFrame(String title, Game game)
    {
        super(title);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
        pack();
        setResizable(true);
        setVisible(true);
    }


}
