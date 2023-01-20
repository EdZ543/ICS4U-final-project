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
    protected boolean sliding;
    protected int slideToX, slideToY;
    protected int speed;
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
     * @return boolean          GridItem is sliding
     */
    public boolean isSliding() {
        return sliding;
    }
    /**
     * Move GridItem at an offset of speedX and speedY
     * @param speedX            x movement
     * @param speedY            y movement
     */
    public void slide(int speedX, int speedY) {
        setLocation(getX() + speedX, getY() + speedY);
    }
    /**
     * Optional method that can be overridden if GridItem needs to do something after sliding
     */
    public void onSlideFinished() {
        return;
    }
    
    /**
     * Call at the start of the act of any GridItem that needs to slide
     */
    public void slideAct() {
        if(sliding) {
            int xSpeed = slideToX-getX()>0 ? 1 : slideToX-getX()<0 ? -1 : 0;
            int ySpeed = slideToY-getY()>0 ? 1 : slideToY-getY()<0 ? -1 : 0;
            slide(xSpeed*speed, ySpeed*speed);
            if(Math.abs(slideToX-getX()) <= speed && Math.abs(slideToY-getY()) <= speed) {
                LevelWorld lw = (LevelWorld)getWorld();
                setLocation(slideToX, slideToY);
                setCellX(lw.getCellX(slideToX));
                setCellY(lw.getCellY(slideToY));
                lw.checkFalling();
                onSlideFinished();
                sliding = false;
            }
        }
    }
    
    /** 
     * sliding "animation" instead of pieces teleporting btwn cells
     * @param x        The x-position where the piece will end up
     * @param y        The y-position where the piece will end up
     */
    public void startSlideToTargetCell(int x, int y, int speed) {
        LevelWorld lw = (LevelWorld)getWorld();
        slideToX = lw.getCoordinateX(x);
        slideToY = lw.getCoordinateY(y);
        this.speed = speed;
        sliding = true;
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
        LevelWorld lw = (LevelWorld)getWorld();
        if (lw.getItem(cellX, cellY) == this) lw.changeGrid(cellX, cellY, null);
        cellX = cellNumber;
        updateLocation();
        lw.changeGrid(cellX, cellY, this);
    }

    /**
     * Sets y coordinate in grid
     * @param cellNumber y location in terms of grid coordinates
     */
    protected void setCellY(int cellNumber) {
        LevelWorld lw = (LevelWorld)getWorld();
        if (lw.getItem(cellX, cellY) == this) lw.changeGrid(cellX, cellY, null);
        cellY = cellNumber;
        updateLocation();
        lw.changeGrid(cellX, cellY, this);
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
        return lw.getItem(getCellX()+1, getCellY());
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
