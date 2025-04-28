package SameGame;

import javax.swing.*;

/**
 * Class that extends JFrame and provides a basic window setup.
 * It sets the title, preferred size, and default close operation for the window.
 * It also centers the window on the screen.
 * 
 * This class can be used as a base class for creating different types of windows in a Java Swing application.
 * 
 * @version 1.1
 * @author Adriwin
 */
public class GenericWindows extends JFrame {
    public GenericWindows(String title, int width, int height) {
        super(title);
        setPreferredSize(new java.awt.Dimension(width, height));
        setSize(getPreferredSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
