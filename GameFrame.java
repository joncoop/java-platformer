/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
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