import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Object that the bird snake can interact with.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public abstract class InteractiveObject extends GridItem
{
    public InteractiveObject(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public abstract void collide(BirdSnakePiece birdSnakePiece);
}
