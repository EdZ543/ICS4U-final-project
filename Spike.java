import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A dangerous block that annihalates the bird snake on contact.
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class Spike extends InteractiveObject
{
    /**
     * Class constructor.
     * 
     * @param cellX The x-coordinate in the grid
     * @param cellY The y-coordinate in the grid
     */
    public Spike(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("images/spike.png");
        image.scale(cellWidth, cellWidth);
        return image;
    }
    
    /**
     * What happens when birdsnake hits this object
     * @param birdSnakePiece         The BirdSnakePiece that is touching this object
     */
    public void collide(BirdSnakePiece birdSnakePiece) {
        LevelWorld lw = (LevelWorld)getWorld();
        lw.getBirdSnakeHead().startDying();
    }
}
