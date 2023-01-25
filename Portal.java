import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goal of each level, lets bird snake go to the next level. All fruits must be eaten before it opens.
 * 
 * @author Eddie Zhuang, Caden Chan
 * @version Jan. 24, 2023
 */
public class Portal extends InteractiveObject
{
    private boolean active = false; // Whether all fruits are eaten and the bird can enter the portal
    private PulsingImage pulsingPortal;
    private int rotateCount, rotateSpeed=3;
    
    /**
     * Class constructor.
     * 
     * @param cellX The x-coordinate in the grid
     * @param cellY The y-coordinate in the grid
     */
    public Portal(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    protected GreenfootImage drawImage(int cellWidth) {
        pulsingPortal = new PulsingImage("images/portal.png", cellWidth, cellWidth, 1, 2);
        image = new GreenfootImage("images/portal.png");
        image.setTransparency(100);
        image.scale(cellWidth+10, cellWidth+10);
        return image;
    }
    public void act() {
        super.act();
        // rotate portal for better animated effect
        pulsingPortal.setRotation(rotateCount);
        rotateCount += 4;
    }
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public void collide(BirdSnakePiece birdSnakePiece) {
        if (active) {
            LevelWorld lw = (LevelWorld)getWorld();
            
            int currentLevel = lw.getLevel();
            int lastLevel = Levels.LEVELS.length - 1;
            if (currentLevel == lastLevel) {
                Greenfoot.setWorld(new EndWorld());
                if (UserInfo.isStorageAvailable()) {
                    UserInfo myInfo = UserInfo.getMyInfo();
                    myInfo.setScore(lastLevel+1);
                    myInfo.store();  // write back to server
                }
            } else {
                lw.setLevel(lw.getLevel() + 1);
            }
        }
    }
    
    /**
     * Set portal to active
     */
    public void activate() {
        active = true;
        rotateCount = 0;
        // make invisible, then add PulsatingImage to cover it => portal animation
        image.setTransparency(0);
        setImage(image);
        getWorld().addObject(pulsingPortal, getX(), getY());
    }
    /**
     * Remove this portal and its animated image
     */
    public void removeFromWorld() {
        if(pulsingPortal.getWorld() != null) {
            getWorld().removeObject(pulsingPortal);
        }
        getWorld().removeObject(this);
    }
}
