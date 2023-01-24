import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
/**
 * A Label class that allows you to display a textual value on screen.
 * 
 * The Label is an actor, so you will need to create it, and then add it to the world
 * in Greenfoot.  If you keep a reference to the Label then you can change the text it
 * displays.  
 *
 * @author Amjad Altadmri, modified by Caden Chan to include other font styles and better dimensions with java.awt
 * @version 1.1
 */
public class Label extends Actor
{
    private String value;
    private int fontSize;
    private String fontStyle;
    private int width, height;
    private Color fillColor = Color.WHITE;
    
    private static final Color transparent = new Color(0,0,0,0);

    
    /**
     * Create a new label, initialise it with the int value to be shown and the font size 
     */
    public Label(int value, int fontSize)
    {
        this(Integer.toString(value), fontSize, "Times");
        
    }
    
    /**
     * Create a new label, initialise it with the needed text and the font size 
     */
    public Label(String value, int fontSize)
    {
        this(value, fontSize, "Times");
    }
    
    public Label(int value, int fontSize, String fontStyle) {
        this(Integer.toString(value), fontSize, fontStyle);
    }
    
    public Label(String value, int fontSize, String fontStyle) {
        this.value = value;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        updateImage();
    }

    /**
     * Sets the value  as text
     * 
     * @param value the text to be show
     */
    public void setValue(String value)
    {
        this.value = value;
        updateImage();
    }
    
    /**
     * Sets the value as integer
     * 
     * @param value the value to be show
     */
    public void setValue(int value)
    {
        this.value = Integer.toString(value);
        updateImage();
    }
    
    /**
     * Sets the fill color of the text
     * 
     * @param fillColor the fill color of the text
     */
    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
        updateImage();
    }

    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        // Setup FontMetrics to measure pixel width/height of text
        Graphics g = getImage().getAwtImage().createGraphics();
        FontMetrics fm = g.getFontMetrics(new Font(fontStyle, Font.PLAIN, fontSize));
        
        // Create image with text
        GreenfootImage image = new GreenfootImage(fm.stringWidth(value), (int)(fontSize*1.4));
        image.setColor(fillColor);
        image.setFont(new greenfoot.Font(fontStyle, fontSize));
        image.drawString(value, 0, (int)(fontSize));
        
        setImage(image);
    }
}