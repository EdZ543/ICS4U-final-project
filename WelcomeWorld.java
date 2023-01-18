import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeWorld here.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{
    private StartButton startBtn;
    private Label titleLabel;
    private GreenfootImage bg;
    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    public WelcomeWorld()
    {
        super(1200, 800, 1);
        bg = new GreenfootImage("temp/bg.png");
        setBackground(bg);
        titleLabel = new Label("BirdSnake!", 80);
        addObject(titleLabel, getWidth()/2, 100);
        startBtn = new StartButton();
        addObject(startBtn, getWidth()/2, getHeight()-100);
        
    }
}
