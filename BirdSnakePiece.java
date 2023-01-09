import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdSnakePiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BirdSnakePiece extends GridItem
{
    public BirdSnakePiece(int cellX, int cellY) {
        super(cellX, cellY);
    }
    public void act()
    {
        // Add your action code here.
    }
    public void moveRight() {
        
    }
    
    public void moveLeft() {
        
    }
    
    public void moveUp() {
        
    }
    
    public void moveDown() {
        
    }
    // sliding "animation" instead of pieces teleporting btwn cells
    public void slideToTargetCell(int targetCellX, int targetCellY) {
        
    }
    /**
     * Compare this piece with an adjacent piece.
     * Example:
     * [n] = one piece
     * 
     * [D][C]
     *    [B][A] 
     *  A.directionFromPiece(B) --> "r"
     *  B.directionFromPiece(A) --> "l"
     *  B.directionFromPiece(C) --> "d"
     *  
     *  Useful method to (hopefully) determine the physics of how the pieces move
     */
    public String directionFromPiece(BirdSnakePiece other) { // return "u","d","l","r" for up down left right
        return "";
    }
}
