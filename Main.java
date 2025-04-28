import SameGame.*;
import javax.swing.*;

/**
 * Main class to start the SameGame application.
 * It sets the look and feel, creates the main game window, and sets the icon.
 */
public class Main {
    
    /**
     * Main method to start the SameGame application.
     * It sets the look and feel, creates the main game window, and sets the icon.
     * 
     * @param args Command line arguments (not used here).
     */
    public static void main(String[] args) {
        // Make sure we run the UI on the Event Dispatch Thread (From what I heard it's better to do it this way)
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the look and feel
                PlagueTaleLookAndFeel.installLookAndFeel();
                UIManager.setLookAndFeel(PlagueTaleLookAndFeel.class.getName());

                // Create the main game window
                SameGameWindow window = new SameGameWindow("SameGame: Innocence", 950, 700);

                ImageIcon icon = new ImageIcon("SameGame/Assets/Images/Icon.png");
                window.setIconImage(icon.getImage());

                window.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error initializing the game: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}