package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

/**
 * Handles the action event for the browse button in the load game menu.
 * Opens a file chooser dialog to let the user select a game file.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class BrowseButtonListener implements ActionListener {
    private SameGameWindow sameGameWindow;
    private JTextField selectedFileField;

    /**
     * Constructor.
     * 
     * @param sameGameWindow The main game window.
     * @param selectedFileField The text field to display the selected file path on.
     */
    public BrowseButtonListener(SameGameWindow sameGameWindow, JTextField selectedFileField) {
        this.sameGameWindow = sameGameWindow;
        this.selectedFileField = selectedFileField;
    }

    
    /** 
     * Handles the action event for the browse button.
     * Opens a file chooser dialog to let the user select a game file.
     * 
     * @param e The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("SameGame/Grids/"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int result = fileChooser.showOpenDialog(sameGameWindow);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileField.setText(selectedFile.getAbsolutePath());
        }
    }
}
