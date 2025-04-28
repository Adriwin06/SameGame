package SameGame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Represents the game grid for the SameGame application.
 * It initializes a grid of cells with random images and provides methods to display the grid.
 * 
 * @see Cell
 * @author Adriwin
 * @version 2.1
 */

public class Grid extends JPanel {
    // Constants
    private static final Color BACKGROUND_COLOR = PlagueTaleLookAndFeel.COAL_GREY; 
    private static final String basePath = "SameGame/Assets/Images/";

    // Variables
    private int rows;
    private int columns;
    private int[][] grid;
    private String[] imagePaths = {
        basePath + "PlagueTale/APTR_Phoenix.png",
        basePath + "PlagueTale/APTR_Rat.png",
        basePath + "PlagueTale/APTR_Flame.png"
    };
    private GamePanel parentGamePanel;

    /**
     * Constructor to create a grid with specified number of rows and columns.
     * It sets the layout to GridLayout and background color to gray.
     * 
     * @param r Number of rows in the grid.
     * @param c Number of columns in the grid.
     * @param parentGamePanel The parent game panel that contains this grid.
     */
    public Grid(int r, int c, GamePanel parentGamePanel) {
        super();
        this.rows = r;
        this.columns = c;
        this.setLayout(new GridLayout(rows, columns));
        this.setBackground(BACKGROUND_COLOR);
        this.parentGamePanel = parentGamePanel;
    }

    
    /**
     * Method to convert the grid in a string format, the same as the one in the file.
     * It uses a switch statement to convert the grid values to their corresponding characters.
     * 
     * @return A string representation of the grid. Each row is separated by a newline character.
     */
    public String toString() {
        String result = "";
        String value;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                switch (grid[i][j]) {
                    case 0:
                        value = "R";
                        break;
                    case 1:
                        value = "V";
                        break;
                    case 2:
                        value = "B";
                        break;
                    default:
                        value = "x";
                        break;
                }
                result += value;
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Method to initialize a random grid with random images.
     * It uses the Random class to fill the grid with random indices corresponding to image paths.
     */
    public void initializeGrid() {
        Random random = new Random();

        this.grid = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.grid[i][j] = random.nextInt(imagePaths.length);
            }
        }
    }

    /**
     * Method to initialize a specific game grid from a given 2D int array
     * The array should contain integers that correspond to the indices of the image paths, so 0, 1, or 2.
     * It overload the initializeGrid method to set the grid based on the specific array.
     * 
     * @param specificGrid 2D int array representing the specific grid to be initialized.
     */
    protected void initializeGrid(int[][] specificGrid) {
        this.grid = specificGrid;
    }

    /**
     * Method to initialize the grid from a file.
     * The file should contain a grid representation with "R", "V", and "B" for different cell types.
     * It overload the initializeGrid method to set the grid based on the file content.
     * 
     * @param path The path to the file containing the grid representation.
     */
    public void initializeGrid(String path) {
        try {
            // Variables initialization
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            int row = 0;
            String[] values;
            this.grid = new int[rows][columns];

            // Read the file line by line and populate the grid based on the characters in the file with a switch
            while((line = bufferedReader.readLine()) != null) {
                values = line.split("");
                if(line.length() != columns) {
                    System.err.println("The format of the file is wrong");                                  // We will ask the user to choose another file later
                    break;
                }
                for (int column = 0; column < values.length; column++) {
                    switch (values[column]) {
                        case "R":
                            this.grid[row][column] = 0;
                            break;
                        case "V":
                            this.grid[row][column] = 1;
                            break;
                        case "B":
                            this.grid[row][column] = 2;
                            break;
                        case "x":
                            this.grid[row][column] = -1; // Empty cell
                            break;
                        default:
                            System.err.println("Unknown character at row " + row + ", column " + column);   // We will ask the user to choose another file later
                            break;
                    }
                }
                row++;
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();            // Error closing the file
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();                // File not found                                           // We will ask the user to choose another file later
        } catch (IOException e) {
            e.printStackTrace();                // Error while opening                                      // We will ask the user to choose another file later
        }
    }

    /**
     * Method to display the grid on the panel.
     * It creates a new Cell for each grid cell and adds it to the panel.
     * If the cell is empty (represented by -1), it creates an empty Cell.
     * If the cell is not empty, it creates a Cell with the corresponding image path.
     * It also prints the grid to the console for debugging.
     * 
     * @see Cell
     */
    public void displayGrid() {
        this.removeAll();
        
        for (int i=0; i < rows; i++) {
            for (int j=0; j < columns; j++) {
                Cell cell;
                if (this.grid[i][j] == -1) {
                    cell = new Cell(i, j, this);
                } else {
                    cell = new Cell(imagePaths[this.grid[i][j]], i, j, this.grid[i][j], this);
                }
                this.add(cell);
            }
        }
        // System.out.println(Arrays.deepToString(this.grid).replace("],", "],\n"));
        System.out.println(this);
        System.out.println("Connected cells of 0,0: " + Arrays.toString(getConnectedCells(0, 0).toArray()));

        this.revalidate();
        this.repaint(); 
    }

    /**
     * Method to get the connected cells of a specific cell in the grid.
     * It uses depth-first search (DFS) to find all connected cells with the same value.
     * 
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     * 
     * @return A list of Point objects representing the coordinates of the connected cells.
     */
    private List<Point> getConnectedCells(int row, int column) {
        int value = grid[row][column];
        if (value == -1) {
            return Collections.emptyList(); // Cell is empty, return empty list
        }
    
        boolean[][] visited = new boolean[rows][columns];
        List<Point> group = new ArrayList<>();
        this.dfs(row, column, value, visited, group);
        return group;
    }
    
    /**
     * Depth-first search (DFS) method to explore the grid and find connected cells.
     * It marks cells as visited and adds them to the group if they have the same value.
     * 
     * @param x The current row index.
     * @param y The current column index.
     * @param value The value of the cell to match.
     * @param visited A 2D boolean array to keep track of visited cells.
     * @param group A list to store the coordinates of connected cells.
     */
    private void dfs(int x, int y, int value, boolean[][] visited, List<Point> group) {
        if (x < 0 || x >= rows || y < 0 || y >= columns) {
            return;
        }
        if (visited[x][y] || grid[x][y] != value) {
            return;
        }
    
        visited[x][y] = true;
        group.add(new Point(x, y));
    
        // Explore all 4 directions
        dfs(x - 1, y, value, visited, group); // up
        dfs(x + 1, y, value, visited, group); // down
        dfs(x, y - 1, value, visited, group); // left
        dfs(x, y + 1, value, visited, group); // right
    }
    

    /**
     * Method to highlight the connected cells of a specific cell in the grid.
     * It first clears any existing highlights and then highlights the connected cells.
     * 
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     */
    public void highlightConnectedCells(int row, int column) {
        this.clearHighlights();
        List<Point> connectedCells = this.getConnectedCells(row, column);
        if (connectedCells.size() < 2) {
            return;
        }
        for (Point point : connectedCells) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            Cell cell = (Cell) this.getComponent(x * columns + y);
            cell.setHighlighted(true);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Method to clear the highlights of all cells in the grid.
     * It iterates through all components and sets their highlighted state to false.
     */
    public void clearHighlights() {
        for (Component component : this.getComponents()) {
            if (component instanceof Cell) {
                ((Cell) component).setHighlighted(false);
            }
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Method to break the connected cells of a specific cell in the grid.
     * It first gets the connected cells and then sets their values to -1 (empty).
     * It also calls the gravity method to update the grid after breaking the cells.
     * 
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     */
    public void breakConnectedCells(int row, int column) {
        List<Point> connectedCells = this.getConnectedCells(row, column);
        int connectedCellsSize = connectedCells.size();
        if (connectedCellsSize < 2) {
            return;
        }
        for(Point cellCoordinate : connectedCells) {
            int x = (int) cellCoordinate.getX();
            int y = (int) cellCoordinate.getY();
            this.grid[x][y] = -1;
        }
        this.gravity();
        this.shiftColumns();
        this.displayGrid();
        this.parentGamePanel.updateScore(connectedCellsSize);
        
        // Check if there are any valid moves left
        if (!hasValidMovesLeft()) {
            this.parentGamePanel.gameOver();
        }
    }

    /**
     * Checks if there are any valid moves left in the grid.
     * A valid move is a group of 2 or more connected cells of the same type.
     * 
     * @return true if there are valid moves left, false otherwise.
     */
    public boolean hasValidMovesLeft() {
        // Check each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // If the cell is not empty
                if (grid[i][j] != -1) {
                    List<Point> connectedCells = getConnectedCells(i, j);
                    // If there are 2 or more connected cells, there is a valid move
                    if (connectedCells.size() >= 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method to apply gravity to the grid.
     * It iterates through each column and moves the non-empty cells down to fill the empty spaces.
     */
    private void gravity() {
        for (int j = 0; j < columns; j++) {
            for (int i = rows - 1; i >= 0; i--) {
                if (grid[i][j] == -1) {
                    int k = i - 1;
                    while (k >= 0 && grid[k][j] == -1) {
                        k--;
                    }
                    if (k >= 0) {
                        grid[i][j] = grid[k][j];
                        grid[k][j] = -1;
                    }
                }
            }
        }
    }

    /**
     * Method to shift the columns of the grid to the left.
     * It removes empty columns and shifts non-empty columns to fill the gaps.
     * It also fills the remaining columns on the right with empty cells.
     */
    private void shiftColumns() {
        int writeCol = 0;
    
        // Move each non-empty column into the next position
        for (int readCol = 0; readCol < this.columns; readCol++) {
            if (!isColumnEmpty(readCol)) {
                if (readCol != writeCol) {
                    for (int i = 0; i < rows; i++) {
                        this.grid[i][writeCol] = this.grid[i][readCol];
                    }
                }
                writeCol++;
            }
        }
    
        // Fill the remaining columns on the right with emptie ones
        for (int col = writeCol; col < this.columns; col++) {
            for (int i = 0; i < this.rows; i++) {
                this.grid[i][col] = -1;
            }
        }
    }
    
    /**
     * Method to check if a column is empty.
     * It iterates through each row of the specified column and checks if all cells are empty (value = -1).
     * 
     * @param col The column index to check.
     * @return true if the column is empty, false otherwise.
     */
    private boolean isColumnEmpty(int col) {
        for (int i = 0; i < rows; i++) {
            if (grid[i][col] != -1) {
                return false;
            }
        }
        return true;
    }
    
    // /**
    //  * Method to get a list of empty columns in the grid.
    //  * It iterates through each column and checks if all cells in that column are empty.
    //  * If a column is empty, it adds its index to the list.
    //  * 
    //  * @return A list of integers representing the indices of empty columns.
    //  *         If no empty columns are found, it returns an empty list.
    //  */
    // private List<Integer> getEmptyColumns() {
    //     List<Integer> emptyColumns = new ArrayList<>();
    
    //     for (int j = 0; j < this.columns; j++) {
    //         boolean emptyColumn = true;
    //         for (int i = 0; i < this.rows; i++) {
    //             if (grid[i][j] != -1) {
    //                 emptyColumn = false;
    //                 break;
    //             }
    //         }
    //         if (emptyColumn) {
    //             emptyColumns.add(j);
    //         }
    //     }
    //     return emptyColumns;
    // }    

    /**
     * Method to get the grid array.
     * 
     * @return The 2D int array representing the game grid.
     */
    public int[][] getGrid() {
        return this.grid;
    }
}