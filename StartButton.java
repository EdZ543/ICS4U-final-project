import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start the simulation!
 * 
 * @author Caden Chan 
 * @version 2022.11.21
 */
public class StartButton extends MenuButton
{
    /**
     * Button used to start the simulation
     */
    public StartButton() {
        // Preset images
        super(new GreenfootImage("temp/playbtn.png"), null, new GreenfootImage("temp/playbtn-hover.png"), null);
    }
    public void act()
    {
        super.act();
    }
    /**
     * When startButton is clicked, start the simulation.
     */
    public void clicked() {
        Greenfoot.setWorld(new GameWorld());
    }
    public void checkHover() {
        super.checkHover();
    }
}