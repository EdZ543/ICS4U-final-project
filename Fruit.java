import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A consumable food.
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public abstract class Fruit extends InteractiveObject
{
    private int rotateCount, rotateFactor, rotateSpeed;
    /**
     * Class constructor.
     * 
     * @param cellX The object's X position
     * @param cellY The object's Y position
     */
    public Fruit(int cellX, int cellY) {
        super(cellX, cellY);
        rotateCount = 0;
        rotateSpeed = Greenfoot.getRandomNumber(2) + 2;
        rotateFactor = Greenfoot.getRandomNumber(2) == 0 ? -1 : 1;
    }
    
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public abstract void collide(BirdSnakePiece birdSnakePiece);
    
    public void act() {
        setRotation(rotateCount);
        rotateCount += rotateSpeed * rotateFactor;
    }
}
