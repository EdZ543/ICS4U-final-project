import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that navigates to the level select screen
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class SelectLevelButton extends UIButton
{
    public SelectLevelButton() {
        super(null);
        
        GreenfootImage image = new GreenfootImage("images/level-select-button.png");
        image.scale(150, 50); 
        setImage(image);
    }
    
    public void clicked() {
        LevelWorld lw = (LevelWorld)getWorld();
        int level = lw.getLevel();
        Greenfoot.setWorld(new LevelSelect());
    }
}
