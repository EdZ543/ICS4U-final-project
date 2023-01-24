import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goal of each level, lets bird snake go to the next level. All fruits must be eaten before it opens.
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class Portal extends InteractiveObject
{
    private boolean active = false; // Whether all fruits are eaten and the bird can enter the portal
    
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
        image = new GreenfootImage("images/portal.png");
        image.setTransparency(50);
        image.scale(cellWidth, cellWidth);
        return image;
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
        image.setTransparency(255);
        setImage(image);
    }
}
