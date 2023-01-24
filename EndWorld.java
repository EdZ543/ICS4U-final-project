import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The player enters this world after completing all levels.
 * 
 * @author Eddie Zhuang
 * @version Jan. 23, 2023
 */
public class EndWorld extends World
{

    /**
     * Constructor for objects of class EndWorld
     */
    public EndWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        GreenfootImage bg = new GreenfootImage("temp/bg.png");
        setBackground(bg);
        Label titleLabel = new Label("You Win!", 80);
        addObject(titleLabel, getWidth() / 2, getHeight() / 2);
    }
}
