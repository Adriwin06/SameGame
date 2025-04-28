package SameGame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import SameGame.ActionListeners.*;

/**
 * Represents the main window of the game.
 * It contains all the necessary panels.
 * It uses a CardLayout to switch between different views such as the game panel, main menu, settings menu, load game menu, and end screen.
 * 
 * @author Adriwin
 * @version 2.3
 */
public class SameGameWindow extends GenericWindows {
    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel(cardLayout);
    private GamePanel gamePanel;
    private MainMenu mainMenu = new MainMenu();
    private SettingsMenu settingsMenu = new SettingsMenu();
    private LoadGameMenu loadGameMenu = new LoadGameMenu();
    private EndScreen endScreen = new EndScreen();
    private boolean continueExist = true;
    public boolean isContinue = false; // Check if the game is a continuation
    
    // Constants for card names
    private static final String MENU_CARD = "MENU";
    private static final String SETTINGS_CARD = "SETTINGS";
    private static final String GAME_CARD = "GAME";
    private static final String LOAD_GAME_CARD = "LOAD_GAME";
    private static final String END_SCREEN_CARD = "END_SCREEN";

    /**
     * Constructor that initializes the game panel and sets up the card layout and event listeners.
     * 
     * @param title The title of the window.
     * @param width The width of the window.
     * @param height The height of the window.
     */
    public SameGameWindow(String title, int width, int height) {
        super(title, width, height);
        
        // Create the game panel with 10x15 grid
        this.gamePanel = new GamePanel(this, 10, 15);
        
        this.initializeCardLayout();
        this.setupEventListeners();

        // Show the main menu by default
        this.showMenu(MENU_CARD);
    }

    /**
     * Initializes the card layout for the game window.
     * It adds the game panel, main menu, settings menu, load game menu, and end screen to the card layout.
     */
    private void initializeCardLayout() {
        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(this.cardLayout);

        this.contentPanel.add(this.gamePanel, GAME_CARD);
        this.contentPanel.add(this.mainMenu, MENU_CARD);
        this.contentPanel.add(this.settingsMenu, SETTINGS_CARD);
        this.contentPanel.add(this.loadGameMenu, LOAD_GAME_CARD);
        this.contentPanel.add(this.endScreen, END_SCREEN_CARD);
        this.add(this.contentPanel);
    }
    
    /**
     * Set up the event listeners for the buttons in the game window.
     */
    private void setupEventListeners() {
        this.setupMainMenuListeners();
        this.setupGamePanelListeners();
        this.setupSettingsMenuListeners();
        this.setupLoadGameMenuListeners();
        this.setupEndScreenListeners();
    }

    /**
     * Set up the event listeners for the load game menu.
     */
    private void setupLoadGameMenuListeners() {
        this.loadGameMenu.setBackButtonListener(new GameWindowListeners(this, MENU_CARD));
        this.loadGameMenu.setLoadButtonListener(new LoadSelectedGameListener(this, this. gamePanel, this.loadGameMenu, GAME_CARD));
        this.loadGameMenu.setBrowseButtonListener(new BrowseButtonListener(this, this.loadGameMenu.getSelectedFile()));
    }

    /**
     * Set up the event listeners for the settings menu.
     */
    private void setupSettingsMenuListeners() {
        this.settingsMenu.setBackButtonListener(new GameWindowListeners(this, MENU_CARD));
    }

    /**
     * Set up the event listeners for the main menu.
     * It checks if a saved game exists and sets the continue button listener accordingly.
     * If no saved game exists, it sets a placeholder listener that informs the user.
     */
    private void setupMainMenuListeners() {
        this.mainMenu.setNewGameButtonListener(new GameWindowListeners(this, GAME_CARD));
        this.mainMenu.setLoadGameButtonListener(new GameWindowListeners(this, LOAD_GAME_CARD));
        this.mainMenu.setSettingsButtonListener(new PlaceholderListener(this,  
                                        "Settings are not available, we did not have time to make the menu, but it stays here because the main menu looks better with it LOL.", 
                                          "Settings not available"));
        this.mainMenu.setExitButtonListener(new ExitListener(this));

        try {
            // To remove warnings since we only use this to test if the continue.sav file exists. Not really useful but I like to have warning-free code when possible.
            @SuppressWarnings({ "resource", "unused" }) 
            FileReader continueExist = new FileReader("SameGame/SaveGame/continue.sav");
        } catch (FileNotFoundException e) {
            this.continueExist = false;
        }

        if (continueExist) {
            this.mainMenu.setContinueButtonListener(new GameWindowListeners(this, GAME_CARD, true));
        } else {
            this.mainMenu.setContinueButtonListener(new PlaceholderListener(this, "No game found, please play a new game and save it to be able to continue", "No game found"));
        }
    }

    /**
     * Set up the event listeners for the game panel.
     * It sets the menu button listener and the save button listener.
     */
    private void setupGamePanelListeners() {
        this.gamePanel.setMenuButtonListener(new GoBackToMainMenu(this, MENU_CARD));
        this.gamePanel.setSaveButtonListener(new SaveGameButtonListener(this, this.gamePanel));
    }

    /**
     * Set up the event listeners for the end screen.
     */
    private void setupEndScreenListeners() {
        this.endScreen.setPlayAgainListener(new GameWindowListeners(this, GAME_CARD));
        this.endScreen.setMainMenuListener(new GameWindowListeners(this, MENU_CARD));
    }

    /**
     * Shows the specified card in the card layout.
     * Initializes the game panel if the game card is shown.
     * 
     * @param card The name of the card to show.
     */
    public void showMenu(String card) {
        this.cardLayout.show(this.contentPanel, card);

        switch (card) {
            case GAME_CARD:
                if (this.isContinue) {
                    this.gamePanel.initializeGame("SameGame/SaveGame/continue.sav");
                } else {
                    this.gamePanel.initializeGame();
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * Shows the end screen with the final score.
     * 
     * @param finalScore The final score to display on the end screen.
     */
    public void showEndScreen(int finalScore) {
        this.endScreen.setFinalScore(finalScore);
        this.cardLayout.show(this.contentPanel, END_SCREEN_CARD);
    }
    
    /**
     * Refreshes the continue button listener state to enable game continuation.
     * Clears all existing listeners first to prevent duplicate actions.
     */
    public void refreshContinueButtonState() {
        this.mainMenu.clearContinueButtonListeners();
        this.mainMenu.setContinueButtonListener(new GameWindowListeners(this, GAME_CARD, true));
    }
}
