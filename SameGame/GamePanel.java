package SameGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents the game screen with the grid and additional UI components
 * like score display and control buttons.
 * 
 * @author Adriwin
 * @version 1.4
 */
public class GamePanel extends JPanel {
    // Constants
    private final Color BACKGROUND_COLOR = PlagueTaleLookAndFeel.PARCHMENT;

    // Components
    private SameGameWindow parentWindow;
    private Grid gameGrid;
    private int score = 0;
    private JPanel controlPanel;
    private JLabel scoreLabel;
    private JButton menuButton;
    private JButton saveButton;

    /**
     * Constructor for the GamePanel class.
     * Initializes the game grid and control panel with buttons and score label.
     * 
     * @param rows Number of rows in the grid.
     * @param columns Number of columns in the grid.
     */
    public GamePanel(SameGameWindow parentWindow, int rows, int columns) {
        this.parentWindow = parentWindow;
        this.scoreLabel = new JLabel("Score: " + this.score);
        this.menuButton = new JButton("Return to Menu");
        this.saveButton = new JButton("Quick Save");
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(BACKGROUND_COLOR);
        
        this.gameGrid = new Grid(rows, columns, this);
        
        this.createControlPanel();
        
        // Add components to panel
        this.add(this.gameGrid, BorderLayout.CENTER);
        this.add(this.controlPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Creates the control panel with score label and buttons.
     * The panel is set to a BorderLayout with the score label on the left and buttons on the right.
     * The control panel is the south component of the main panel, just below the grid where we can see the score, 
     * go back to the menu or save the game.
     */
    private void createControlPanel() {
        this.controlPanel = new JPanel(new BorderLayout());
        this.controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        this.controlPanel.setBackground(BACKGROUND_COLOR);
        
        this.scoreLabel.setFont(PlagueTaleLookAndFeel.MEDIEVAL_FONT.deriveFont(Font.BOLD, 18));
        
        // Create a sub-panel for buttons using FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(this.saveButton);
        buttonPanel.add(this.menuButton);
        
        // Add components to control panel
        this.controlPanel.add(this.scoreLabel, BorderLayout.WEST);
        this.controlPanel.add(buttonPanel, BorderLayout.EAST);
    }
    
    /**
     * Sets the action listener for the menu button.
     * @param listener
     */
    public void setMenuButtonListener(ActionListener listener) {
        this.menuButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the save button.
     * @param listener
     */
    public void setSaveButtonListener(ActionListener listener) {
        this.saveButton.addActionListener(listener);
    }
    
    /**
     * Initializes the game grid with a specific file path.
     * The file should contain a 2D array of integers representing the grid.
     * The integers should correspond to the indices of the image paths, so 0, 1, or 2, or -1 for empty cells.
     * 
     * @param filePath
     */
    public void initializeGame(String filePath) {
        this.gameGrid.initializeGrid(filePath);
        this.readScoreFile();
        this.defaultConstructor();
    }

    /**
     * Initializes the game grid with random images.
     * It sets the score to 0 and updates the display.
     * Overload the initializeGame method to set the grid randomly when no file path is provided.
     */
    public void initializeGame() {
        this.gameGrid.initializeGrid();
        this.score = 0;
        this.defaultConstructor();
    }

    private void defaultConstructor() {
        this.gameGrid.displayGrid();
        this.updateScoreLabel();
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Reads the score from a file and updates the score variable.
     * The file should contain a single integer representing the score.
     * If the file is not found or cannot be read, it prints an error message and will use the default score of 0.
     */
    private void readScoreFile() {
		try {
            // Variables initialization
            BufferedReader bufferedReader = new BufferedReader(new FileReader("SameGame/SaveGame/score.sav"));
			try {
				this.score = Integer.parseInt(bufferedReader.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    /**
     * Updates the score label to display the current score.
     */
    private void updateScoreLabel() {
        this.scoreLabel.setText("Score: " + this.score);
    }

    /**
     * Updates the score based on the number of broken cells.
     * The score is calculated as the square of the difference between the number of broken cells and 2: (n-2)^2
     * 
     * @param brokenCellsCount Number of broken cells in the grid.
     */
    public void updateScore(int brokenCellsCount) {
        this.score += Math.pow((brokenCellsCount - 2), 2);
        this.updateScoreLabel();   
    }
    
    /**
     * Returns the current score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns the game grid.
     * @return
     */
    public Grid getGameGrid() {
        return this.gameGrid;
    }

    /**
     * Called when the game is over (no more valid moves).
     * Notifies the parent SameGameWindow to show the end screen.
     */
    public void gameOver() {
        if (this.parentWindow != null) {
            this.parentWindow.showEndScreen(this.score);
        }
    }
}
