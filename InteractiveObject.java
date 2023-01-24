import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Object that the bird snake can interact with.
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public abstract class InteractiveObject extends GridItem
{
    /**
     * Class constructor.
     * 
     * @param cellX The object's X position
     * @param cellY The object's Y position
     */
    public InteractiveObject(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public abstract void collide(BirdSnakePiece birdSnakePiece);
    
    /**
     * Whether the object should fall currently
     * 
     * @return boolean Whether the object should fall currently
     */
    public boolean shouldFall() {
        return false;
    }
}
