import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class BirdSnake here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */
public class BirdSnake extends Actor
{
    // ArrayList<BirdSnakePiece> bsPieces;
    Stack<BirdSnakePiece> bsPieces;
    public BirdSnake() {
        bsPieces = new Stack<BirdSnakePiece>();
        // bsPieces = new ArrayList<BirdSnakePiece>();
        // bsPieces.add(new BirdSnakeHead());
    }
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Get the length of the BirdSnake
     */
    public int getBSLength() {
        return 0;
    }
    
    
    public void handleKeys() {
        
    }
    
    public void moveRight() {
        
    }
    
    public void moveLeft() {
        
    }
    
    public void moveUp() {
        
    }
    
    public void moveDown() {
        
    }
    /**
     * Add piece
     */
    public void grow() {
        
    }
    /**
     * Remove piece
     */
    public void shrink() {
        
    }
}
