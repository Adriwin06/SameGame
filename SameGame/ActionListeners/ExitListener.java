package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Handles the action events for the exit button in the game window.
 * It prompts the user for confirmation before exiting the game.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class ExitListener implements ActionListener {
    private SameGameWindow sameGameWindow;

    /**
     * Constructor that initializes the SameGameWindow reference.
     * 
     * @param sameGameWindow The main game window reference.
     */
    public ExitListener(SameGameWindow sameGameWindow) {
        this.sameGameWindow = sameGameWindow;
    }

    
    /** 
     * Handles the action event when the exit button is clicked.
     * It shows a confirmation dialog to the user asking if they want to exit the game.
     * If the user confirms, the application exits.
     * 
     * @param e The action event triggered by the button click.
     */
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(sameGameWindow, 
                    "Are you sure you want to exit?", 
                    "Exit Game", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
