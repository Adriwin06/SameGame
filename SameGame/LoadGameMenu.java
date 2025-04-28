package SameGame;

import SameGame.ActionListeners.FileListListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This class represents the Load Game menu of the SameGame application.
 * It allows the user to select a saved game file to load.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class LoadGameMenu extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<String> fileList;
    private JTextField selectedFileField;
    private JButton browseButton, loadButton, backButton;
    private JLabel titleLabel;

    /**
     * Constructor for the LoadGameMenu class.
     * It initializes the components and sets up the layout of the menu.
     */
    public LoadGameMenu() {
        // Outer BorderLayout (same as this.setLayout(new BorderLayout(10, 10) but better)
        super(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // NORTH: Title/Name of the menu
        this.titleLabel = new JLabel("Load Game", SwingConstants.CENTER);
        this.titleLabel.setFont(PlagueTaleLookAndFeel.MEDIEVAL_FONT.deriveFont(Font.BOLD, 28f));
        this.add(this.titleLabel, BorderLayout.NORTH);

        // CENTER: File list in scrollpane
        this.listModel = new DefaultListModel<>();
        this.scanSaveDirectory();
        this.fileList = new JList<>(this.listModel);
        this.fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // SOUTH: container for file‑picker row + buttons
        JPanel south = new JPanel(new BorderLayout(5, 5));

        // File‑picker row at top of SOUTH
        JPanel selectedRow = new JPanel(new BorderLayout(5, 5));
        selectedRow.add(new JLabel("Selected File:"), BorderLayout.WEST);

        this.selectedFileField = new JTextField();
        this.selectedFileField.setEditable(false);
        selectedRow.add(this.selectedFileField, BorderLayout.CENTER);

        // Add the listener
        this.fileList.addListSelectionListener(new FileListListener(this.fileList, this.selectedFileField));
        this.add(new JScrollPane(this.fileList), BorderLayout.CENTER);

        this.browseButton = new JButton("Browse…");
        selectedRow.add(this.browseButton, BorderLayout.EAST);

        south.add(selectedRow, BorderLayout.NORTH);

        // Buttons row at bottom of SOUTH
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.loadButton = new JButton("Load Selected Game");
        this.backButton = new JButton("Back to Main Menu");
        btnRow.add(this.loadButton);
        btnRow.add(this.backButton);

        south.add(btnRow, BorderLayout.SOUTH);

        this.add(south, BorderLayout.SOUTH);
    }

    /**
     * Scans the save directory for existing SaveGame files and adds them to the list model.
     * The directory is assumed to be "SameGame/Grids/".
     */
    private void scanSaveDirectory() {
        File dir = new File("SameGame/Grids/");
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                this.listModel.addElement(f.getPath());
            }
        }
    }

    
    /**
     * Sets the selected file path in the text field.
     * 
     * @param path The file path to set.
     */
    public String getSelectedFilePath() {
        return this.selectedFileField.getText();
    }

    public JTextField getSelectedFile() {
        return this.selectedFileField;
    }

    public void setBrowseButtonListener(ActionListener listener) {
        this.browseButton.addActionListener(listener);
    }

    public void setLoadButtonListener(ActionListener listener) {
        this.loadButton.addActionListener(listener);
    }

    public void setBackButtonListener(ActionListener listener) {
        this.backButton.addActionListener(listener);
    }
}