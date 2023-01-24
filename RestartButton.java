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
        changeImage(drawImage());
    }
    
    /**
     * Resets the level.
     */
    public void clicked() {
        LevelWorld lw = (LevelWorld)getWorld();
        lw.setLevel(lw.getLevel());
    }
    
    private GreenfootImage drawImage() {
        GreenfootImage image = new GreenfootImage("images/restart-button.png");
        image.scale(50, 50);
        return image;
    }
}
