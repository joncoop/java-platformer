/**
 * Copyright (c) 2016 Jon Cooper
 *  
 * This file is part of java-platformer.
 * Documentation, related files, and licensing can be found at
 * 
 *      <https://github.com/joncoop/java-platformer>.
 */

public class Player
{
    private int lives;
    private int score;
    
    public Player()
    {
        this.lives = 0;
        this.score = 0;
    }
    
    public void addPoints(int points)
    {
        score += points;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public void setLives(int lives)
    {
        this.lives = lives;
    }

}