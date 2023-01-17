import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goal of each level, lets bird snake go to the next level. All fruits must be eaten before it opens.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class Portal extends InteractiveObject
{
    public Portal(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("images/portal.png");
        image.scale(cellWidth, cellWidth);
        return image;
    }
    
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public void collide(BirdSnakePiece birdSnakePiece) {
        LevelWorld lw = (LevelWorld)getWorld();
        lw.setLevel(lw.getLevel() + 1);
    }
}
