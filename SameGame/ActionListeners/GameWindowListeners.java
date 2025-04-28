package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;

/**
 * Handles the action events for the game window.
 * It listens for button clicks and shows the appropriate menu based on the card name.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class GameWindowListeners implements ActionListener {
    private SameGameWindow sameGameWindow;
    private String card;
    private boolean isContinue;

    /**
     * Constructor that initializes the SameGameWindow and the card name.
     * 
     * @param sameGameWindow The main game window reference.
     * @param card The card name to show when the action is performed.
     */
    public GameWindowListeners(SameGameWindow sameGameWindow, String card) {
        this.sameGameWindow = sameGameWindow;
        this.card = card;
        this.isContinue = false;
    }

    /**
     * Constructor that initializes the SameGameWindow, the card name, and the continuation flag.
     * Overload the constructor to set a specific continuation state.
     * 
     * @param sameGameWindow The main game window reference.
     * @param card The card name to show when the action is performed.
     * @param isContinue Indicates if the game is a continuation.
     */
    public GameWindowListeners(SameGameWindow sameGameWindow, String card, boolean isContinue) {
        this.sameGameWindow = sameGameWindow;
        this.card = card;
        this.isContinue = isContinue;
    }

    
    /**
     * Handles the action event when a button is clicked.
     * It sets the continuation state and shows the appropriate menu based on the card name.
     * 
     * @param e The action event triggered by the button click.
     */
    public void actionPerformed(ActionEvent e) {
        this.sameGameWindow.isContinue = isContinue;
        this.sameGameWindow.showMenu(this.card);
    }
}
