package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is an ActionListener that handles the action of going back to the main menu.
 * It prompts the user with a confirmation dialog before proceeding.
 * 
 * @author Adriwin
 * @version 1.0
 */

public class GoBackToMainMenu implements ActionListener {
    private SameGameWindow sameGameWindow;
    private String card;
    private String message = "Are you sure you want to go back to the main menu? Any unsaved progress will be lost.";
    private String title = "Go back to main menu";


    /**
     * Constructor that initializes the SameGameWindow reference with a default message.
     * 
     * @param sameGameWindow The main game window reference.
     */
    public GoBackToMainMenu(SameGameWindow sameGameWindow, String card) {
        this.sameGameWindow = sameGameWindow;
        this.card = card;
    }

    
    /**
     * Handles the action event when a button is clicked.
     * It shows a confirmation dialog to the user and if confirmed, it goes back to the main menu.
     * 
     * @param e The action event triggered by the button click.
     */
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(sameGameWindow, 
                    this.message, 
                    this.title, JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            this.sameGameWindow.showMenu(this.card);
        }
        
    }
}
