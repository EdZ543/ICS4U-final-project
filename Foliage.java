import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Foliage here.
 * 
 * @author Caden Chan
 * @version 2023.01.22
 */
public class Foliage extends Actor
{
    private Dirt origin;
    private String type;
    protected int xOffset, yOffset;   // xOffset and yOffset depends on type of foliage
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
    public void act() {
        if(origin.getWorld() == null) {
            getWorld().removeObject(this);
        }
    }
    private void setImageFromType() {
        int rand = Greenfoot.getRandomNumber(100);
        LevelWorld lw = (LevelWorld)getWorld();
        // randomize grass sprite
        if(type.equals("grass")) {
            if(rand < 60) {
                image = new GreenfootImage("grass0.png");
            } else if(rand < 80){
                image = new GreenfootImage("grass1.png");
            } else {
                image = new GreenfootImage("grass2.png");
            }
            image.scale((int)(lw.getCellWidth()*1.2), (int)(lw.getCellWidth()/2));  // scale image
            xOffset = 0;
            yOffset = -18;
        } else if(type.equals("filler")) {
            image = new GreenfootImage(lw.getCellWidth(), 8);
            image.setColor(new Color(170, 131, 86));
            image.fill();
        }
    }
}
