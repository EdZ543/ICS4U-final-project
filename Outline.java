import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    private boolean trackPosition;
    
    public Outline(int size, Color color) {
        this.size = size;
        image = new GreenfootImage(size, size);
        image.setColor(color);
        image.drawRect(0, 0, size-1, size-1);
        setImage(image);
        update(null);
        trackPosition = true;
    }
    public void act() {
        if(!Greenfoot.mouseMoved(this)) {
            MouseInfo cursor = Greenfoot.getMouseInfo();
            update(cursor);
        }
        if(Greenfoot.mousePressed(this)) {
            LevelWorld w = (LevelWorld)getWorld();
            MouseInfo cursor = Greenfoot.getMouseInfo();
            GridItem clickedItem = (GridItem)getOneIntersectingObject(GridItem.class);
            if(clickedItem != null) {
                System.out.println("X: " + w.getCellX(cursor.getX())  + ", Y: " + w.getCellY(cursor.getY()));
            }
        }
    }
    public void update(MouseInfo cursor) {
        LevelWorld w = (LevelWorld)getWorld();
        if(cursor == null) {
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
            int posX = w.getCoordinateX(w.getCellX(cursor.getX()));
            int posY = w.getCoordinateY(w.getCellY(cursor.getY()));
            setLocation(posX, posY);
        }
    }
}
