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
    public Block(int cellX, int cellY) {
        super(cellX, cellY);
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        width = ((Grid)w).getCellSize();
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int w) {
        width = w;
    }
}
