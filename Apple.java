import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fruit that causes the bird snake's length to increase by 1.
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class Apple extends Fruit
{
    /**
     * Class constructor.
     * 
     * @param cellX The x-coordinate in the grid
     * @param cellY The y-coordinate in the grid
     */
    public Apple(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("images/apple.png");
        image.scale(cellWidth, cellWidth);
        return image;
    }

    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public void collide(BirdSnakePiece birdSnakePiece) {
        LevelWorld lw = (LevelWorld)getWorld();
        lw.getBirdSnakeHead().grow();
        lw.removeObject(this);
        lw.eatFruit();
    }
    
    /**
     * Whether the object should fall currently
     * 
     * @return boolean Whether the object should fall currently
     */
    public boolean shouldFall() {
        return false;
    }
}
