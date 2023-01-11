import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdSnakePiece here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */
public class BirdSnakePiece extends Block
{
    BirdSnakePiece followPiece;
    /**
     * @param cellX                 The x-position of the piece
     * @param cellY                 The y-position of the piece
     * @param followPiece           The BirdSnakePiece that this piece is behind
     */
    public BirdSnakePiece(int cellX, int cellY, BirdSnakePiece followPiece) {
        super(cellX, cellY);
        this.followPiece = followPiece;
    }
    public void act() {
        
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
    /**
     * Piece moves one cell to the right
     */
    public void moveRight() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(lw.getGridXLength() == cellX-1) {
            
        }
    }
    
    /**
     * Piece moves one cell to the left
     */
    public void moveLeft() {
        LevelWorld lw = (LevelWorld)getWorld();
    }
    
    /**
     * Piece moves one cell up
     */
    public void moveUp() {
        LevelWorld lw = (LevelWorld)getWorld();
    }
    
    /**
     * Piece moves one cell down
     */
    public void moveDown() {
        LevelWorld lw = (LevelWorld)getWorld();
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
