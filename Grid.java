import greenfoot.*;
/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Grid extends World 
{
    // instance variables - replace the example below with your own
    protected int[][] grid;
    private static int BLOCK_SIZE = 60;
    private static int xOffset = 0;
    private static int yOffset = 20;
    
    public Grid(int xOffset, int yOffset) {
        super(1100, 720, 1);
    }

    public static int getXCoordinate (int cellNumber){
        return (cellNumber * BLOCK_SIZE) + xOffset;
    }

    public static int getXCell(int coordinate){
        return (coordinate - xOffset) % BLOCK_SIZE;
    }

    public static int getYCoordinate (int cellNumber){
        return (cellNumber * BLOCK_SIZE) + yOffset;
    }

    public static int getYCell(int coordinate){
        return (coordinate - yOffset) % BLOCK_SIZE;
    }
    
    public static int getXAdjustedCoordinate(int coordinate) {
        return coordinate - (coordinate-xOffset)%BLOCK_SIZE + BLOCK_SIZE/2;
    }
    
    public static int getYAdjustedCoordinate(int coordinate) {
        return coordinate - (coordinate-yOffset)%BLOCK_SIZE + BLOCK_SIZE/2;
    }
    
    public static int getBlockSize() {
        return BLOCK_SIZE;
    }
}
