import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spike extends InteractiveObject
{
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
        // lw.setLevel(lw.getLevel());
        lw.getBirdSnakeHead().startDying();
    }
}
