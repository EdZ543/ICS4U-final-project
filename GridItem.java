import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    private boolean trackPosition = false;
    public GridItem(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
    }
    
    public void addedToWorld(World w) {
        updateLocation();
    }
    public void act()
    {
    }
    public void updateLocation() {
        Grid myGrid = (Grid)getWorld();
        updateLocation(myGrid);
    }
    public void updateLocation(Grid w) {
        setLocation(w.getCoordinateX(cellX), w.getCoordinateY(cellY));
    }
    public int getID() {
        return LevelBuilder.getID(this.getClass());
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
    // public GridItem checkItemRight() {
        // Grid grid = (Grid)getWorld();
    // }
    // public GridItem checkItemLeft() {
        // Grid grid = (Grid)getWorld();
    // }
    // public GridItem checkItemUp() {
        // Grid grid = (Grid)getWorld();
    // }
    // public GridItem checkItemDown() {
        // Grid grid = (Grid)getWorld();
    // }
    public ArrayList<GridItem> adjacentGridItems() {
        ArrayList<GridItem> gridItems = new ArrayList<GridItem>();
        return gridItems;
    }
}
