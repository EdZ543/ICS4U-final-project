import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass shared among all actors that stay within the confines of the game's grid
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class GridItem extends Actor
{
    protected int cellX, cellY;
    protected GreenfootImage image;
    
    public GridItem(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
    }
    
    public void addedToWorld(World w) {
        updateLocation();
    }
    public void act()
    {
        // Add your action code here.
    }
    public void updateLocation() {
        setLocation(Grid.getCoordinateX(cellX), Grid.getCoordinateY(cellY));
    }
    public int getCellX() {
        return cellX;
    }
    public int getCellY() {
        return cellY;
    }
    public void setCellX(int cellNumber) {
        cellX = cellNumber;
        updateLocation();
    }
    public void setCellY(int cellNumber) {
        cellY = cellNumber;
        updateLocation();
    }
}
