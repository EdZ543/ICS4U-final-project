import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goal of each level, lets bird snake go to the next level. All fruits must be eaten before it opens.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class Portal extends InteractiveObject
{
    private boolean active = false;
    
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
            int lastLevel = Levels.LEVELS.length;
            if (currentLevel == lastLevel) {
                Greenfoot.setWorld(new EndWorld());
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
