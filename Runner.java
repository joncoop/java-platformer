/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

import java.io.IOException;

public class Runner
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game g = new Game();
        g.play();
    }
}