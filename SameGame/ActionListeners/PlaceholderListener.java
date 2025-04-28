package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is an ActionListener that shows a message dialog when an action is performed.
 * It is used for buttons or menu items that are not yet implemented to display a message like "This feature is not implemented yet." when the user clicks on them.
 * 
 * @author Adriwin
 * @version 1.1
 */
public class PlaceholderListener implements ActionListener {
    private SameGameWindow sameGameWindow;
    private String message = "This feature is not implemented yet.";
    private String title = "Not Implemented";

    /**
     * Constructor that initializes the SameGameWindow reference with a default message.
     * 
     * @param sameGameWindow The main game window reference.
     */
    public PlaceholderListener(SameGameWindow sameGameWindow) {
        this.sameGameWindow = sameGameWindow;
    }

    /**
     * Constructor that initializes the SameGameWindow reference and the message to be displayed.
     * 
     * @param sameGameWindow The main game window reference.
     * @param message The message to be displayed in the dialog.
     */
    public PlaceholderListener(SameGameWindow sameGameWindow, String message) {
        this.sameGameWindow = sameGameWindow;
        this.message = message;
    }

    /**
     * Constructor that initializes the SameGameWindow reference and the message to be displayed.
     * 
     * @param sameGameWindow The main game window reference.
     * @param message The message to be displayed in the dialog.
     */
    public PlaceholderListener(SameGameWindow sameGameWindow, String message, String title) {
        this.sameGameWindow = sameGameWindow;
        this.message = message;
        this.title = title;
    }

    
    /**
     * Handles the action event when a button is clicked.
     * It shows a message dialog with the specified message and title.
     * 
     * @param e The action event triggered by the button click.
     */
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(sameGameWindow, 
                    this.message, 
                    this.title, JOptionPane.INFORMATION_MESSAGE);
    }
}
