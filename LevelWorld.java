import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelWorld extends World
{

    private GridItem[][] grid;
    private int cellWidth, gridXOffset, gridYOffset;
    private int level;
    private Label b;
    Outline gridTracker;
    public LevelWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        // Setting critical values;
        cellWidth = 50;
        gridXOffset = 0;
        gridYOffset = 0;
        
        gridTracker = new Outline(cellWidth, Color.CYAN);
        addObject(gridTracker, 0, 0);
    }
    
    public GridItem[][] getGrid() {
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
     * Understanding cellX/cellY & coordinateX/coordinateY
     * [A][ ][ ][ ]
     * [ ][ ][B][ ]
     * [ ][ ][ ][ ]
     * cellX of A : 0           coordinateX of A or B : x-value of respective cell in the greenfoot world
     * cellY of A : 0           coordinateY of A or B : y-value of respective cell in the greenfoot world
     *                                                      ^^points to the center of the cell
     * cellX of B : 2
     * cellY of B : 1
     */
    
    /**
     * @param cellNumber        row number pertaining to the grid
     * @return int              x-coordinate in Greenfoot world pertaining to given row number
     */
    public int getCoordinateX (int cellNumber){
        return (cellNumber * cellWidth) + gridXOffset + cellWidth/2;
    }
    
    /**
     * @param coordinate        x-coordinate in the Greenfoot world
     * @return int              row number where the x-coordinate is located
     */
    public int getCellX(int coordinate){
        return (coordinate - gridXOffset) / cellWidth;
    }
    
    /**
     * @param cellNumber        column number pertaining to the grid
     * @return int              y-coordinate in Greenfoot world pertaining to given column number
     */
    public int getCoordinateY (int cellNumber){
        return (cellNumber * cellWidth) + gridYOffset + cellWidth/2;
    }
    
    /**
     * @param coordinate        y-coordinate in the Greenfoot world
     * @return int              column number where the y-coordinate is located
     */
    public int getCellY(int coordinate){
        return (coordinate - gridYOffset) / cellWidth;
    }
    
    /**
     * @return int          Cell width
     */
    public int getCellWidth() {
        return cellWidth;
    }
    
    /**
     * @param size          Cell width
     */
    public void setCellWidth(int w) {
        cellWidth = w;
    }
}
