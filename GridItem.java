import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An item that is locked into a grid
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public abstract class GridItem extends Actor
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
        LevelWorld lw = (LevelWorld)w;
        
        updateLocation();
        image = drawImage(lw.getCellWidth());
        setImage(image);
    }
    
    protected abstract GreenfootImage drawImage(int cellWidth);

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
    protected void setCellX(int cellNumber) {
        cellX = cellNumber;
        updateLocation();
    }

    /**
     * Sets y coordinate in grid
     * @param cellNumber y location in terms of grid coordinates
     */
    protected void setCellY(int cellNumber) {
        cellY = cellNumber;
        updateLocation();
    }

    /**
     * Returns the item in the grid to the left
     */
    protected GridItem getItemLeft() {
        LevelWorld lw = (LevelWorld)getWorld();
        return lw.getItem(getCellX() - 1, getCellY());
    }
    
    /**
     * Returns the item in the grid to the right
     */
    protected GridItem getItemRight() {
        LevelWorld lw = (LevelWorld)getWorld();
        return lw.getItem(getCellX(), getCellY() + 1);
    }
    
    /**
     * Returns the item above in the grid
     */
    protected GridItem getItemAbove() {
        LevelWorld lw = (LevelWorld)getWorld();
        return lw.getItem(getCellX(), getCellY() - 1);
    }
    
    /**
     * Returns the item below in the grid
     */
    protected GridItem getItemBelow() {
        LevelWorld lw = (LevelWorld)getWorld();
        return lw.getItem(getCellX(), getCellY() + 1);
    }
    
    /**
     * Returns whether this item should currently be falling
     */
    public abstract boolean shouldFall();
}
