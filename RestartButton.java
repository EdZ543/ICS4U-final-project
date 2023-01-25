import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that resets the level
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class RestartButton extends UIButton
{
    private int clickIndex;
    private GreenfootSound[] clickSounds;
    /**
     * Class constructor.
     */
    public RestartButton() {
        super(null);
        changeImage(new GreenfootImage("restart-button.png"));
        changeHoverImage(new GreenfootImage("restart-button-hover.png"));
        clickSounds = new GreenfootSound[5];
        for(int i=0;i<clickSounds.length;i++) {
            clickSounds[i] = new GreenfootSound("button.mp3");
            clickSounds[i].setVolume(40);
        }
        clickIndex = 0;
    }
    
    /**
     * Resets the level.
     */
    public void clicked() {
        LevelWorld lw = (LevelWorld)getWorld();
        lw.setLevel(lw.getLevel());
        clickSounds[clickIndex].play();
        clickIndex = (clickIndex+1)%clickSounds.length;
    }
}
