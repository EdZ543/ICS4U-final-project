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
        image.fillRect(0, 0, size-1, size-1);
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
            MouseInfo cursor = Greenfoot.getMouseInfo();
            GridItem clickedItem = (GridItem)getOneIntersectingObject(GridItem.class);
            if(clickedItem != null) {
                System.out.println(clickedItem.getID());
                System.out.println("X: " + clickedItem.getCellX()  + ", Y: " + clickedItem.getCellY());
            }
        }
    }
    public void update(MouseInfo cursor) {
        Grid g = (Grid)getWorld();
        if(cursor == null) {
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
            int posX = g.getCoordinateX(g.getCellX(cursor.getX()));
            int posY = g.getCoordinateY(g.getCellY(cursor.getY()));
            // int[] pos = Grid.getCellXY(cursor.getX(), cursor.getY(), false);
            setLocation(posX, posY);
        }
    }
}
