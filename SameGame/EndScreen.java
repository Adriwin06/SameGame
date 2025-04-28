package SameGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the end screen of the game shown when there are no more valid moves.
 * It displays the final score and provides options to play again or return to the main menu.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class EndScreen extends JPanel {
    private JLabel titleLabel;
    private JLabel scoreLabel;
    private JButton playAgainButton;
    private JButton mainMenuButton;
    
    /**
     * Constructor for the EndScreen panel.
     * Initializes the components and sets up the layout.
     */
    public EndScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        titleLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        titleLabel.setFont(PlagueTaleLookAndFeel.MEDIEVAL_FONT.deriveFont(Font.BOLD, 48f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        scoreLabel = new JLabel("Your Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(PlagueTaleLookAndFeel.MEDIEVAL_FONT.deriveFont(Font.PLAIN, 24f));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setMaximumSize(new Dimension(200, 40));
        
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.setMaximumSize(new Dimension(200, 40));
        
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(scoreLabel);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(playAgainButton);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(mainMenuButton);
        this.add(Box.createVerticalGlue());
    }
    
    /**
     * Sets the final score to be displayed on the end screen.
     * 
     * @param score The final score to display.
     */
    public void setFinalScore(int score) {
        this.scoreLabel.setText("Your Score: " + score);
    }
    
    /**
     * Sets the action listener for the "Play Again" button.
     * 
     * @param listener The ActionListener to be set for the button.
     */
    public void setPlayAgainListener(ActionListener listener) {
        this.playAgainButton.addActionListener(listener);
    }
    
    /**
     * Sets the action listener for the "Main Menu" button.
     * 
     * @param listener The ActionListener to be set for the button.
     */
    public void setMainMenuListener(ActionListener listener) {
        this.mainMenuButton.addActionListener(listener);
    }
}
