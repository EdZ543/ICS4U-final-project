import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdSnakeHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BirdSnakeHead extends BirdSnakePiece
{
    public int facingDirection; // 0 = right, 1 = down, 2 = left, 3 = up (or something like this)
    public BirdSnakeHead(int cellX, int cellY) {
        super(cellX, cellY);
    }
    public void act()
    {
        // Add your action code here.
    }
    public int getFacingDirection() {
        return facingDirection;
    }
    public void setFacingDirection(int d) {
        facingDirection = d;
        updateSprite(facingDirection);
    }
    public void updateSprite(int facingDirection) {
        
    }
}
