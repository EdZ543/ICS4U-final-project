import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Foliage here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */
public class Foliage extends Actor
{
    private Dirt origin;
    private String type;
    private int xOffset, yOffset;   // xOffset and yOffset depends on type of foliage
    GreenfootImage image;
    /**
     * @param origin        Foliage positioned relative to the center of this Dirt block
     * @param type          Type of foliage, as a String
     */
    public Foliage(Dirt origin, String type) {
        this.origin = origin;
        this.type = type;
    }
    public void addedToWorld(World w) {
        setImageFromType();
        setImage(image);
        updateLocation();
    }
    private void updateLocation() {
        setLocation(origin.getX() + xOffset, origin.getY() + yOffset);
    }
    private void setImageFromType() {
        int rand = Greenfoot.getRandomNumber(100);
        LevelWorld lw = (LevelWorld)getWorld();
        if(type.equals("grass")) {
            if(rand < 60) {
                image = new GreenfootImage("grass0.png");
            } else {
                image = new GreenfootImage("grass1.png");
            }
            image.scale((int)(lw.getCellWidth()*1.2), (int)(lw.getCellWidth()/2));
            xOffset = 0;
            yOffset = -15;
        }
    }
}
