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
        
        // TestBlock testBlock = new TestBlock(1, 0);
        // Test
        // addObject(testBlock, 0, 0);
        TestBlock[] blocks = {new TestBlock(1, 1), new TestBlock(1, 2), new TestBlock(1, 3),
        new TestBlock(2, 1), new TestBlock(2, 2), new TestBlock(2, 3), new TestBlock(3, 1),
        new TestBlock(3, 2), new TestBlock(3, 3)};
        for(TestBlock b: blocks) {
            addObject(b, 0, 0);
        }
    }
    
    public void buildLevelGrid() {
        grid = new int[][] // level grid
        {
                
        };
    }
}
