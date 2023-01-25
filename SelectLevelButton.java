import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that navigates to the level select screen
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class SelectLevelButton extends UIButton
{
    /**
     * Class constructor.
     */
    public SelectLevelButton() {
        super(null);
        
        // Sets the button image
        changeImage(new GreenfootImage("images/level-select-button.png"));
        changeHoverImage(new GreenfootImage("images/level-select-button-hover.png"));
        clickSound = new GreenfootSound("button.mp3");
        clickSound.setVolume(30);
    }
    
    /**
     * Sets world to level select screen
     */
    public void clicked() {
        LevelWorld lw = (LevelWorld)getWorld();
        int level = lw.getLevel();
        Greenfoot.setWorld(new LevelSelect());
        clickSound.play();
    }
}
