import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that resets the level
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class RestartButton extends UIButton
{
    /**
     * Class constructor.
     */
    public RestartButton() {
        super(null);
        changeImage(new GreenfootImage("restart-button.png"));
        changeHoverImage(new GreenfootImage("restart-button-hover.png"));
        
    }
    
    /**
     * Resets the level.
     */
    public void clicked() {
        LevelWorld lw = (LevelWorld)getWorld();
        lw.setLevel(lw.getLevel());
    }
}
