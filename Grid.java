import greenfoot.*;
import java.util.HashMap;
/**
 * Class containing all fundamental values pertaining to our game (i.e. world size, grid dimensions, etc.)
 * 
 * @author (your name) 
 * @version 2023.01.05
 */
public abstract class Grid extends World 
{
    protected int[][] grid;
    private static int blockSize = 50;  // non-final, in case we want levels with smaller/bigger block sizes for whatever funky reason : D
    // blockSize = 50 for earlier levels, when map is smaller; blockSize = 40 for later levels, when map gets 'bigger'
    private final static int X_OFFSET = 0;
    private final static int Y_OFFSET = 0;
    // NOTE: helpful if W and H are divisible by all possible blockSize values
    private final static int WORLD_W = 1200, WORLD_H = 800;  //**TBD
    
    private static HashMap<Integer, Class> objectIDs; // integers represent block types, fruit types, portal, etc
    public Grid() {
        super(WORLD_W, WORLD_H, 1);
        setPaintOrder(Outline.class);
    }
    /**
     * Initialize grid variable with 2-d array representing a level;
     */
    public abstract void buildLevelGrid();
    /**
     * Create level world based on <code>int[][] grid</code>
     */
    public void buildLevelWorld() {
        
    }
    
    public void addGridItem(GridItem item) {
        addObject(item, 0, 0); // `item` will position itself in the world
        grid[item.getCellX()][item.getCellY()] = item.getID();
    }
    
    public int[][] getGrid() {
        return grid;
    }
    /**
     * Assumes that the 2-d array creates a perfect square shape
     * return int       Number of columns in the grid (# of cells per row)
     */
    public int getGridXLength() {
        return grid[0].length;
    }
    /**
     * Assumes that the 2-d array creates a perfect square shape
     * return int       Number of rows in the grid (# of cells per column)
     */
    public int getGridYLength() {
        return grid.length;
    }
    
    /**
     * @param cellNumber        row number pertaining to the grid
     * @return int              x-coordinate in Greenfoot world pertaining to given row number
     */
    public static int getCoordinateX (int cellNumber){
        return (cellNumber * blockSize) + X_OFFSET + blockSize/2;
    }
    
    /**
     * @param coordinate        x-coordinate in the Greenfoot world
     * @return int              row number where the x-coordinate is located
     */
    public static int getCellX(int coordinate){
        return (coordinate - X_OFFSET) / blockSize;
    }
    
    /**
     * @param cellNumber        column number pertaining to the grid
     * @return int              y-coordinate in Greenfoot world pertaining to given column number
     */
    public static int getCoordinateY (int cellNumber){
        return (cellNumber * blockSize) + Y_OFFSET + blockSize/2;
    }
    
    /**
     * @param coordinate        y-coordinate in the Greenfoot world
     * @return int              column number where the y-coordinate is located
     */
    public static int getCellY(int coordinate){
        return (coordinate - Y_OFFSET) / blockSize;
    }
    
    /**
     * @return int          Width of the Greenfoot world
     */
    public static int getWorldW() {
        return WORLD_W;
    }
    
    /**
     * @return int          Height of the Greenfoot world
     */
    public static int getWorldH() {
        return WORLD_H;
    }
    
    /**
     * @return int          Block size
     */
    public static int getBlockSize() {
        return blockSize;
    }
    
    /**
     * @param size          Block size
     */
    public static void setBlockSize(int size) {
        blockSize = size;
    }
}
