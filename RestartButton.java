import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that resets the level
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class RestartButton extends UIButton
{
    public RestartButton() {
        super(null);
        changeImage(drawImage());
    }
    
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
