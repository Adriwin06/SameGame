package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * Handles loading a game from a selected file.
 * Gets the selected file path from LoadGameMenu and initializes the game with it.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class LoadSelectedGameListener implements ActionListener {
    private SameGameWindow sameGameWindow;
    private LoadGameMenu loadGameMenu;
    private GamePanel gamePanel;
    private String gameCard;

    /**
     * Constructor.
     * 
     * @param sameGameWindow The main game window.
     * @param gamePanel The game panel to initialize the game on.
     * @param loadGameMenu The load game menu to get the selected file path from.
     * @param gameCard The card name for the game panel.
     */
    public LoadSelectedGameListener(SameGameWindow sameGameWindow, GamePanel gamePanel, LoadGameMenu loadGameMenu, String gameCard) {
        this.sameGameWindow = sameGameWindow;
        this.gamePanel = gamePanel;
        this.loadGameMenu = loadGameMenu;
        this.gameCard = gameCard;
    }

    
    /**
     * Handles the action event when a button is clicked.
     * It gets the selected file path from LoadGameMenu and initializes the game with it.
     * If no file is selected, it shows a warning message dialog.
     * 
     * @param e The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedFilePath = this.loadGameMenu.getSelectedFilePath();
        
        if (selectedFilePath == null || selectedFilePath.isEmpty()) {
            JOptionPane.showMessageDialog(sameGameWindow, 
                "Please select a file to load.", 
                "No File Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        this.sameGameWindow.showMenu(this.gameCard);
        this.gamePanel.initializeGame(selectedFilePath);
    }
}
