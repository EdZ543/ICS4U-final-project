import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version 2023.01.05
 */
public abstract class Block extends GridItem
{
    // Basic properties
    protected int width;  // NOTE: if rectangles are needed, add a length variable
    // Interesting features  -  optional
    protected boolean movable;
    // protected boolean slippery;  - an idea, but unsure how to implement with grid-based game
    public Block(int cellX, int cellY) {
        this(cellX, cellY, false);
    }
    public Block(int cellX, int cellY, boolean movable) {
        super(cellX, cellY);
        this.movable = movable;
        width = Grid.getBlockSize();
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int w) {
        width = w;
    }
    
    public boolean isMovable() {
        return movable;
    }
    
    public void setMovable(boolean a) {
        movable = a;
    }
}
