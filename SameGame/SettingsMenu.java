package SameGame;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Represents the settings menu of the game.
 * It contains a button to go back to the main menu. Since this class is noit finished at all.
 * 
 * @author Adriwin
 * @version 1.0
 * 
 * @see JPanel
 */
public class SettingsMenu extends JPanel {
    private JButton backButton;
    
    public SettingsMenu() {
        backButton = new JButton("Back to Main Menu");
        this.add(backButton);
    }

    
    /*
     * Sets the action listener for the back button.
     * 
     * @param listener The ActionListener to be set for the back button.
     */
    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}