import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The most basic fruit. It causes the bird snake's length to increase by 1.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
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
        
        BirdSnakeHead head = birdSnakePiece instanceof BirdSnakeHead ? (BirdSnakeHead)birdSnakePiece : birdSnakePiece.getHeadPiece() ;
        head.grow();
        lw.removeObject(this);
        lw.eatFruit();
    }
    
    public boolean shouldFall() {
        return false;
    }
}
