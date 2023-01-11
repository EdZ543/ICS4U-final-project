import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdSnakeHead here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */
public class BirdSnakeHead extends BirdSnakePiece
{
    private int facingDirection; // 0 = right, 1 = down, 2 = left, 3 = up (or something like this)
    
    /**
     * @param cellX         The x-position of the head
     * @param cellY         The y-position of the head
     */
    public BirdSnakeHead(int cellX, int cellY) {
        super(cellX, cellY, null);
        image = new GreenfootImage("temp/birdsnakehead.png");
    }
    
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        int cellWidth = ((LevelWorld)w).getCellWidth();
        image.scale(cellWidth, cellWidth);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
    /**
     * @return int      The direction that the BirdSnake is facing
     */
    public int getFacingDirection() {
        return facingDirection;
    }
    /**
     * @param d         The direction to make the BirdSnake face.
     */
    public void setFacingDirection(int d) {
        facingDirection = d;
        updateSprite(facingDirection);
    }
    /**
     * Update the head's sprite
     */
    public void updateSprite(int facingDirection) {
        
    }
    
}
