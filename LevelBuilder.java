// import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
// // Data structures
// import java.util.HashMap;
// // Scanner
// import java.util.Scanner;
// import java.util.StringTokenizer;
// // Read/Write to files
// import java.io.File;
// import java.io.FileWriter;
// import java.io.PrintWriter;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// // Instantiating Classes
// import java.lang.reflect.Constructor;
// /**
 // * Write a description of class LevelBuilder here.
 // * 
 // * Level File Format Example:
 // *                            vv gridWidth      vv gridHeight
 // * -Line1:          cellSize,cellsPerRow,cellsPerColumn
 // * -Line2 (row1):   0,0,0,0,0,1
 // * -Line3 (row2):   0,0,2,0,3,4
 // * etc.
 // * @author (your name) 
 // * @version (a version number or a date)
 // */
// public class LevelBuilder extends World
// {
    // private static HashMap<Class, Integer> gridItemIDs = itemClassToID();
    // private int currentID;
    // private static final Scanner sc = new Scanner(System.in);
    // private Outline gridOutline;
    // /**
     // * Constructor for objects of class LevelBuilder.
     // * 
     // */
    // public LevelBuilder()
    // {    
        // super();
        // grid = new int[getWorldW()/getCellSize()][getWorldH()/getCellSize()];
        // currentID = 1;
        // gridOutline = new Outline(getCellSize(), Color.CYAN);
        // addObject(gridOutline, 10, 20);
        // // grid = loadLevel("levels/NewLevel.txt");
        // // buildLevelWorld();
    // }
    
    // public void act() {
        // handleKeys();
        // if(Greenfoot.mousePressed(gridOutline)){
            // System.out.println("Hi");
            // MouseInfo cursor = Greenfoot.getMouseInfo();
            // if(cursor.getButton() == 1) {
                // newGridItem(currentID, getCellX(cursor.getX()), getCellY(cursor.getY()));
            // }
        // }
    // }
    // public void handleKeys() {
        // if(Greenfoot.isKeyDown("n")) {
            // while(true) {
                // System.out.print("Select item: ");
                // String item = sc.nextLine().toLowerCase();
                // System.out.println(item);
                // for(Class c : gridItemIDs.keySet()) {
                    // System.out.println(c.getName());
                    // if(item.equals(c.getName().toLowerCase())) {
                        // currentID = getID(c);
                        // return;
                    // } else if(item.equals("q")){
                        // return;
                    // }
                // }
                // System.out.println("Invalid item name!");
            // }
        // }
        // if(Greenfoot.isKeyDown("s")) {
            // while(true) {
                // System.out.print("Save and Exit? (y/n): ");
                // String ans = sc.nextLine().toLowerCase();
                // if(ans.equals("y")) {
                    // saveLevelToFile("levels/NewLevel.txt", this);
                    // Greenfoot.setWorld(new WelcomeWorld());
                    // return;
                // } else if(ans.equals("n")){
                    // return;
                // }
                // System.out.println("Invalid input!");
            // }
        // }
    // }
    // public void buildLevelGrid() {
        
    // }
    // public void buildLevelWorld() {
        // for(int i=0;i<grid.length;i++) {
            // for(int j=0;j<grid[i].length;j++) {
                // // addObject(createGridItem(getClass(grid[i][j]
                // int id = grid[i][j];
                // System.out.println(id);
                // if(id!= 0) {
                    // System.out.println("hi");
                    // addObject(createGridItem(getClass(id), i, j), 0, 0);
                // }
                
            // }
        // }
    // }
    // // PROBLEM: Need way to deal with different orientations
    // public static HashMap<Class, Integer> itemClassToID() {
        // HashMap<Class, Integer> ids = new HashMap<Class, Integer>();
        // // 0 = empty space
        // ids.put(BirdSnakePiece.class, 1);
        // ids.put(BirdSnakeHead.class, 2);
        // ids.put(Portal.class, 3);
        // // Blocks
        // ids.put(TestBlock.class, 10);
        // // Fruits
        // ids.put(Apple.class, 100);
        // return ids;
    // }
    // public void newGridItem(int id, int cellX, int cellY) {
        // GridItem item = createGridItem(getClass(id), cellX, cellY);
        // System.out.print(item);
        // addGridItem(item);
    // }
    // private static GridItem createGridItem(Class c, int cellX, int cellY) {
        // try {
            // Constructor<GridItem> constructor = c.getConstructor(int.class, int.class);
            // return constructor.newInstance(cellX, cellY);
        // } catch(Exception e) {}
        // return null;
    // }
    // public static int getID(Class c) {
        // return gridItemIDs.get(c);
    // }
    // public static Class getClass(int id) {
        // for(Class key : gridItemIDs.keySet()) {
            // if(gridItemIDs.get(key) == id) {
                // return key;
            // }
        // }
        // return null;
    // }
    // public static int[][] loadLevel(String fileName) {
        // // Get file scanner
        // Scanner fileScanner;
        // StringTokenizer tokenizer;
        // int[][] grid;
        // try {
            // fileScanner = new Scanner(new File(fileName));
            // String arrayInfo = fileScanner.nextLine(); //cellSize, gridWidth (num of cells), gridHeight (num of cells)
            // tokenizer = new StringTokenizer(arrayInfo, ",");
            // int blockSize = Integer.parseInt(tokenizer.nextToken());
            // int gridWidth = Integer.parseInt(tokenizer.nextToken());
            // int gridHeight = Integer.parseInt(tokenizer.nextToken());
            // grid = new int[gridHeight][gridWidth];
            
            // for(int i=0;i<grid.length;i++) {
                // tokenizer = new StringTokenizer(fileScanner.nextLine(), ",");
                // for(int j=0;j<grid[i].length;j++) { // populate one row
                    // grid[i][j] = Integer.parseInt(tokenizer.nextToken());
                // }
            // }
            // return grid;
        // } catch (FileNotFoundException e) {
            // System.out.println("Error - Level not found");
            // System.exit(1);
        // }
        // return null;
    // }
    
    // public static void saveLevelToFile(String fileName, Grid myGrid) {
        // saveLevelToFile(fileName, myGrid.getGrid(), myGrid.getCellSize());
    // }
    // public static void saveLevelToFile(String fileName, int[][] grid, int blockSize) {
        // try {
            // File levelFile = new File(fileName);
            // levelFile.createNewFile();  // create new file if does not exist
        // } catch(IOException e) {
            // System.out.println("Error - " + e);
            // System.exit(1);
        // }
        // String[] lines = new String[grid.length + 1];
        // lines[0] = blockSize+","+grid[0].length+","+grid.length;
        // for(int i=0;i<grid.length;i++) {
            // String line = "";
            // for(int j=0;j<grid[i].length;j++) {
                // line += j==grid[i].length-1 ? grid[i][j] + "" : grid[i][j] + ",";
            // }
            // lines[i+1] = line;
        // }
        // arrayWriteToFile(fileName, lines, false);
    // }
    
    // // Text File Helper Methods
    // /**
     // * Write a string to the file
     // * @param text          The text that should be written to the file. If null, flush the printwriter (empty string)
     // * @param append        If true, append text to file; if false, overwrite file with text
     // */
    // private static void writeToFile(String fileName, String text, boolean append) {
        // try {
            // // Create FileWriter and PrintWriter objects
            // FileWriter out = new FileWriter (fileName, append);
            // PrintWriter output = new PrintWriter (out);
            // if(text == null) {  // if text == null, output is an empty string; work-around to output.println("") creating an unwanted empty line
                // output.flush();
            // } else {
                // output.println(text);
            // }
            // // Close FileWriter and PrintWriter objects
            // output.close();
            // out.close();
        // } catch(IOException e) {
            // System.out.println("Error - " + e);
        // }
    // }
    // /**
     // * Write an array of string to the file
     // * @param textArr       An array of strings that should be written to the file
     // * @param append        If true, append text to file; if false, overwrite file with text
     // */
    // private static void arrayWriteToFile(String fileName, String[] textArr, boolean append) {
        // writeToFile(fileName, null, append); // if append is false, empty file. if append is true, does nothign.
        // for(String text : textArr) {
            // writeToFile(fileName, text, true);  // add onto previous line
        // }
    // }
// }
