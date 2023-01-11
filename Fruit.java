import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A consumable food.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public abstract class Fruit extends InteractiveObject
{
    public Fruit(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public abstract void collide(BirdSnakePiece birdSnakePiece);
}
