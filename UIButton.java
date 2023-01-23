import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass for buttons used in the menu screen, clickable by the user. (I/O buttons).
 * 
 * @author Caden Chan 
 * @version 2022.12.04
 */
public abstract class UIButton extends Actor
{
    protected GreenfootImage image, clickedImage, hoverImage, inactiveImage;
    protected int clickCount;
    protected boolean active = true;
    public abstract void clicked();
    /**
     * Fully static button
     * @param image             The button's default image
     */
    public UIButton(GreenfootImage image) {
        this(image, null, null, null);
    }
    /**
     * clickedImage, hoverImage and inactiveImage may be set to null if the respective
     * functionality is undesired.
     * @param image             The button's default image
     * @param clickedImage      The button's image upon being clicked
     * @param hoverImage        The button's image upon being hovered
     * @param inactiveImage     The button's image when inactive
     */
    public UIButton(GreenfootImage image, GreenfootImage clickedImage, GreenfootImage hoverImage, GreenfootImage inactiveImage) {
        this.image = image;
        this.clickedImage = clickedImage;
        this.hoverImage = hoverImage;
        this.inactiveImage = inactiveImage;
        setImage(image);
    }
    public void act()
    {
        if (!active) {
            return;
        }
        // Show clickedImage for 15 acts, then set back to regular image
        if(clickCount > 0) {
            clickCount --;
            if(clickCount == 0) {
                setImage(image);
            }
        }
        // When mouse is pressed, show clickedImage
        if(Greenfoot.mousePressed(this) && clickedImage != null){
            clickCount=15;
            setImage(clickedImage);
        }
        // When mouse is released, activate clicked() functionality
        if(Greenfoot.mouseClicked(this)) {
            clicked();
        }
        // When button has not just been clicked, check for hovering cursor
        if(clickCount == 0) {
            checkHover();
        }
    }
    /**
     * If user cursor is hovering over the button, change image to hoverImage
     */
    public void checkHover() {
        if(hoverImage == null) return;
        if(Greenfoot.mouseMoved(this)) {
            setImage(hoverImage);
        } else if(Greenfoot.mouseMoved(null)){
            setImage(image);
        }
    }
    /**
     * @return boolean          Button's <code>active</code> state
     */
    public boolean isActive() {
        return active;
    }
    /**
     * If inactive, set image to inactiveImage
     * @param a             Set <code>active</code> state to boolean value of a
     */
    public void setActive(boolean a) {
        active = a;
        if(inactiveImage == null) return;
        if(a) {
            setImage(image);
        } else {
            setImage(inactiveImage);
        }
    }
    
    /**
     * Change the button's image
     */
    public void changeImage(GreenfootImage image) {
        this.image = image;
        this.clickedImage = clickedImage;
        this.hoverImage = hoverImage;
        this.inactiveImage = inactiveImage;
        setImage(image);
    }
}
