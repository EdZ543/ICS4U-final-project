import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start the game!
 * 
 * @author Caden Chan 
 * @version 2022.11.21
 */
public class StartButton extends UIButton
{
    /**
     * Button used to start the simulation
     * 
     * @param levelProgress The last level the player was able to reach
     */
    public StartButton() {
        // Preset images
        super(new GreenfootImage("playbtn.png"), null, new GreenfootImage("playbtn-hover.png"), null);
        clickSound = new GreenfootSound("buttonhappy.mp3");
        clickSound.setVolume(30);
    }
    public void act()
    {
        super.act();
    }
    /**
     * When startButton is clicked, start the simulation.
     */
    public void clicked() {
        Greenfoot.setWorld(new LevelSelect());
        clickSound.play();
    }
    public void checkHover() {
        super.checkHover();
    }
}
