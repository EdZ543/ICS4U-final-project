import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Back to welcome screen.
 * 
 * @author Caden Chan
 * @version 2023.01.24
 */
public class HomeButton extends UIButton
{
    public HomeButton() {
        super(null);
        changeImage(new GreenfootImage("home-button.png"));
        changeHoverImage(new GreenfootImage("home-button-hover.png"));
        
    }
    
    /**
     * Resets the level.
     */
    public void clicked() {
        Greenfoot.setWorld(new WelcomeWorld());
    }
}
