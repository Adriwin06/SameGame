package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * ActionListener for the "Save Game" button in the SameGame application.
 * This class handles the action of saving the current game state and score to files.
 * It prompts the user for confirmation before saving.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class SaveGameButtonListener implements ActionListener {
	private Grid grid;
	private GamePanel gamePanel;
	private SameGameWindow sameGameWindow;

	/**
	 * Constructor
	 * 
	 * @param sameGameWindow The main window of the game.
	 * @param gamePanel The game panel containing the grid and score.
	 */
	public SaveGameButtonListener(SameGameWindow sameGameWindow, GamePanel gamePanel) {
		this.grid = gamePanel.getGameGrid();
		this.gamePanel = gamePanel;
		this.sameGameWindow = sameGameWindow;
	}
	
	/**
	 * Handles the action of the "Save Game" button.
	 * It prompts the user for confirmation and saves the game state and score if confirmed.
	 * 
	 * @param e The ActionEvent triggered by the button click.
	 */
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(sameGameWindow,
                    "Do you want to save? It will overwrite any existing save.", 
                    "Save Game", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            createSaveDirectory();
            this.writeContinueFile();
			this.writeScoreFile();
            this.sameGameWindow.refreshContinueButtonState();
        }
	}

    /**
     * Creates the save directory if it doesn't exist.
     */
    private void createSaveDirectory() {
        File saveDir = new File("SameGame/SaveGame/");
        if (!saveDir.exists()) {
            boolean created = saveDir.mkdirs();
            if (!created) {
                JOptionPane.showMessageDialog(sameGameWindow,
                    "Failed to create save directory.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

	/**
	 * Writes the current game state (grid) to a file.
	 * The grid is saved in a text file that acts as a save slot to be able to "load the save" later.
	 */
	private void writeContinueFile() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("SameGame/SaveGame/continue.sav"));
			try {
				bufferedWriter.write(this.grid.toString());
			} catch(IOException e1) {
				e1.printStackTrace();
			} catch(NumberFormatException e2) {
				e2.printStackTrace();
			}
			try {
				bufferedWriter.close();
			} catch(IOException e3) {
				e3.printStackTrace();
			}
		} catch(FileNotFoundException e4) {
			e4.printStackTrace();
		} catch (IOException e5) {
			e5.printStackTrace();
		}
	}

	/**
	 * Writes the current score to a file.
	 * The score is saved in a text file that acts as a save slot to be able to "load the save" later.
	 */
	private void writeScoreFile() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("SameGame/SaveGame/score.sav"));
			try {	
				int score = this.gamePanel.getScore();
        		bufferedWriter.write(Integer.toString(score));
			} catch(IOException e1) {
				e1.printStackTrace();
			} catch(NumberFormatException e2) {
				e2.printStackTrace();
			}
			try {
				bufferedWriter.close();
			} catch(IOException e3) {
				e3.printStackTrace();
			}
		} catch(FileNotFoundException e4) {
			e4.printStackTrace();
		} catch (IOException e5) {
			e5.printStackTrace();
		}
	}
}
