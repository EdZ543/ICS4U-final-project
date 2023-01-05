import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * METHOD FOR TESTING GRID THINGS
 * 
 * @author Caden Chan 
 * @version 2022.01.03
 */
public class Outline extends Actor
{
    private boolean openCell;
    private int x, y;
    private int size;
    private GreenfootImage image;
    
    public Outline(Color color) {
        size = Grid.getBlockSize();
        image = new GreenfootImage(size, size);
        image.setColor(color);
        image.drawRect(0, 0, size-1, size-1);
        setImage(image);
        update(null);
    }
    public void act() {
        if(!Greenfoot.mouseMoved(this)) {
            MouseInfo cursor = Greenfoot.getMouseInfo();
            update(cursor);
        }
    }
    public void update(MouseInfo cursor) {
        if(cursor == null) {
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
            int posX = Grid.getXAdjustedCoordinate(cursor.getX());
            int posY = Grid.getYAdjustedCoordinate(cursor.getY());
            // int[] pos = Grid.getCellXY(cursor.getX(), cursor.getY(), false);
            setLocation(posX, posY);
        }
    }
}
