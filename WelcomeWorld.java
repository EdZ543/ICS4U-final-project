import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{
    private StartButton startBtn;
    private Label titleLabel;
    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    public WelcomeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Grid.getWorldW(), Grid.getWorldH(), 1);
        
        titleLabel = new Label("BirdSnake!", 80);
        addObject(titleLabel, getWidth()/2, 100);
        startBtn = new StartButton();
        addObject(startBtn, getWidth()/2, getHeight()-100);
        
    }
}
