package SameGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * MainMenu class represents the main menu of the game.
 * It contains buttons for starting a new game, continuing a game,
 * loading a game, accessing settings, and exiting the game.
 * It also displays the logo at the top of the menu.
 * 
 * @author Adriwin
 * @version 1.0
 * 
 * @see JPanel
 */
public class MainMenu extends JPanel {
    private JButton newGameButton;
    private JButton continueButton;
    private JButton loadGameButton;
    private JButton settingsButton;
    private JButton exitButton;

    /**
     * Constructor for MainMenu.
     * Initializes the layout, logo, and buttons.
     */
    public MainMenu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        // Game Logo
        MainMenuLogo logo = new MainMenuLogo("SameGame/Assets/Images/Logo.png");
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setPreferredSize(new Dimension(800, 200));
        logo.setMaximumSize(new Dimension(1600, 400));
    
        this.add(Box.createVerticalGlue());
        this.add(logo);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
    
        // Button Wrapper Panel with Padding
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        // Create and add buttons
        newGameButton = makeButton("New Game");
        continueButton = makeButton("Continue");
        loadGameButton = makeButton("Load Game");
        settingsButton = makeButton("Settings");
        exitButton = makeButton("Exit");
    
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(continueButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(loadGameButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(exitButton);
    
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonsPanel);
        this.add(Box.createVerticalGlue());
    }
    

    
    /**
     * Creates a button with the specified text and sets its properties.
     * 
     * @param text The text to be displayed on the button.
     * 
     * @return The created JButton.
     */
    private JButton makeButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setPreferredSize(new Dimension(200, 40));
        button.setMinimumSize(new Dimension(200, 40));
        button.setFocusable(false);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        return button;
    }

    // Listeners
    public void setNewGameButtonListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }
    
    public void setContinueButtonListener(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
    
    public void setLoadGameButtonListener(ActionListener listener) {
        loadGameButton.addActionListener(listener);
    }
    
    public void setSettingsButtonListener(ActionListener listener) {
        settingsButton.addActionListener(listener);
    }
    
    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    /**
     * Removes all action listeners from the continue button.
     * This helps prevent multiple listeners when refreshing the button state.
     */
    public void clearContinueButtonListeners() {
        ActionListener[] listeners = continueButton.getActionListeners();
        for (ActionListener listener : listeners) {
            continueButton.removeActionListener(listener);
        }
    }
}
