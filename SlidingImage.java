import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SlidingImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlidingImage extends AnimatedImage
{
    private int initialX, initialY;
    private int offsetX, offsetY;
    private int speed;
    private boolean goBack;
    public SlidingImage(String filePath, int offsetX, int offsetY, int speed) {
        super(filePath);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.speed = speed;
        goBack = false;
    }
    public SlidingImage(String filePath, int width, int height, int offsetX, int offsetY, int speed) {
        super(filePath, width, height);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.speed = speed;
        goBack = false;
    }
    public void addedToWorld(World w) {
        initialX = getX();
        initialY = getY();
    }
    public void animate() {
        if(goBack) {
            turnTowards(initialX, initialY);
        } else {
            turnTowards(initialX+offsetX, initialY+offsetY);
        }
       
        // move(speed);
        for(int i = 0;i<speed;i++) {
            move(speed);
            if(getX() == initialX+offsetX && getY() == initialY+offsetY) {
                goBack = true;
                setRotation(0);
                return;
            } else if(getX() == initialX && getY() == initialY) {
                goBack = false;
                setRotation(0);
                return;
            }
        }
        setRotation(0);
    }
}
