import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HomeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
