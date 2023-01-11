import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An item that is locked into a grid
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public abstract  class GridItem extends Actor
{
    protected int cellX, cellY;
    protected GreenfootImage image;
    
    /**
     * GridItem constructor
     * @param cellX The x position of the item
     * @param cellY The y position of the item
     */
    public GridItem(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
    }
    
    public void addedToWorld(World w) {
        updateLocation();
    }
    
    /**
     * Update location in grid based on cellX and cellY
     */
    public void updateLocation() {
        LevelWorld lw = (LevelWorld)getWorld();
        setLocation(lw.getCoordinateX(cellX), lw.getCoordinateY(cellY));
    }
    
    /**
     * Returns x coordinate in grid
     * @return y location in terms of grid coordinates
     */
    public int getCellX() {
        return cellX;
    }
    
    /**
     * Returns y coordinate in grid
     * @return y location in terms of grid coordinates
     */
    public int getCellY() {
        return cellY;
    }
    
    /**
     * Sets x coordinate in grid
     * @param cellNumber y location in terms of grid coordinates
     */
    public void setCellX(int cellNumber) {
        cellX = cellNumber;
        updateLocation();
    }
    
    /**
     * Sets y coordinate in grid
     * @param cellNumber y location in terms of grid coordinates
     */
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
    // public ArrayList<GridItem> adjacentGridItems() {
        // ArrayList<GridItem> gridItems = new ArrayList<GridItem>();
        // return gridItems;
    // }
}
