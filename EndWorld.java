import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The player enters this world after completing all levels.
 * 
 * @author Eddie Zhuang
 * @version Jan. 23, 2023
 */
public class EndWorld extends World
{
    // private Label titleLabel;
    private HomeButton hb;
    private PulsingImage winMessage;
    /**
     * Constructor for objects of class EndWorld
     */
    public EndWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        GreenfootImage bg = new GreenfootImage("bg0.png");
        setBackground(bg);
        
        // titleLabel = new Label("You Win!", 80, "Segoe Print");
        // addObject(titleLabel, getWidth() / 2, getHeight() / 2);
        winMessage =  new PulsingImage("win-message.png", 1.9, 1.1);
        addObject(winMessage, getWidth()/2, 370);
        
        hb = new HomeButton();
        addObject(hb, getWidth()/2, 600);
    }
    public void started() {
        WelcomeWorld.getMusic().playLoop();
    }

    public void stopped() {
        WelcomeWorld.getMusic().stop();
    }
}
