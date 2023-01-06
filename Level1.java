import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends Grid
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    private Outline gridTester;
    
    public Level1()
    {
        super();
        buildLevelGrid();
        gridTester = new Outline(Color.CYAN);
        addObject(gridTester, 10, 20);
        
        TestBlock testBlock = new TestBlock(1, 0);
        addObject(testBlock, 0, 0);
    }
    
    public void buildLevelGrid() {
        grid = new int[][] // level grid
        {
                
        };
    }
}
