import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * BirdSnake's body pieces
 * Follows other pieces, and the orders given by the BirdSnakeHead
 * 
 * @author Caden Chan
 * @version 2023.01.23
 */
public class BirdSnakePiece extends Block
{
    /**
     * BirdSnakePiece constructor
     * @param cellX                 The x-position of the piece
     * @param cellY                 The y-position of the piece
     */
    public BirdSnakePiece(int cellX, int cellY) {
        super(cellX, cellY);
        speed = 8;
    }
    public boolean push(int xOffset, int yOffset) {
        return false;
    }
    protected GreenfootImage drawImage(int cellWidth) {
        LevelWorld lw = (LevelWorld)getWorld();
        image = new GreenfootImage("birdsnakepiece" + (lw.getBirdSnakeHead().getPieces().size()+1)%2 + ".png");
        image.scale(cellWidth, cellWidth);
        return image;
    }
    public void act() {
        LevelWorld lw = (LevelWorld)getWorld();
        slideAct();
        // Collision detection
        InteractiveObject obj = (InteractiveObject)getOneIntersectingObject(InteractiveObject.class);
        if(obj != null && obj.getCellX() == slideToCellX && obj.getCellY() == slideToCellY) {
            obj.collide(this);
        }
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
    
    /**
     * @return boolean      whether or not this piece should be falling
     */
    public boolean shouldFall() {
        LevelWorld lw = (LevelWorld)getWorld();
        GridItem below = getItemBelow();
        if(below == null) return true;  // fall if nothing below it
        if(!(below instanceof BirdSnakePiece) && below instanceof Block && !below.shouldFall()) return false;  // dont fall if on top of block
        if(below instanceof Fruit) return false; // Interesting mechanic: birdsnake can sit on fruits
        return true;
    }
    
    
    
    /**
     * Move piece to a desired cell, given an x and y ofset
     * @param xOffset       the number of cells the piece should move, in the x-direction
     * @param yOffset       the number of cells the piece should move, in the y-direction
     */
    public void movePiece(int xOffset, int yOffset) {
        startSlideToTargetCell(getCellX() + xOffset, getCellY() + yOffset, speed);
    }
    
    /**
     * Return the offset that is needed to move in a certain direction.
     * Valid directions are:
     * "r" = right
     * "l" = left
     * "d" = down
     * "u" = up
     * 
     * @param d             The direction, as a single character
     */
    public int[] getOffsetFromDirection(char d) {
        int[] pos = new int[2];
        switch (d) {
            case 'u': 
                pos[0] = 0;
                pos[1] = -1;
            case 'd':
                pos[0] = 0;
                pos[1] = 1;
            case 'l':
                pos[0] = -1;
                pos[1] = 0;
            case 'r':
                pos[0] = 1;
                pos[1] = 0;
        }
        return pos;
    }
    /**
     * Compare this piece with an adjacent piece.
     * Example:
     * [n] = one piece
     * 
     * [D][C]
     *    [B][A] 
     *  A.directionToAdjacentPiece(B) --> 'l'
     *  B.directionToAdjacentPiece(A) --> 'r'
     *  B.directionToAdjacentPiece(C) --> 'u'
     *  
     *  Useful method to determine the physics of how the pieces move
     */
    public char directionToAdjacentPiece(BirdSnakePiece other) { // return "u","d","l","r" for up down left right
        if(other.getCellX() == getCellX()) {
            int yDiff = other.getCellY() - getCellY();
            if(yDiff == 1) {
                return 'd';
            } else if(yDiff == -1) {
                return 'u';
            }
        } else if(other.getCellY() == getCellY()) {
            int xDiff = other.getCellX() - getCellX();
            if(xDiff == 1) {
                return 'r';
            } else if(xDiff == -1) {
                return 'l';
            } 
        }
        return ' ';
    }
}
