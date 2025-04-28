package SameGame.ActionListeners;

import javax.swing.*;
import javax.swing.event.*;

/**
 * Handles list selection events for the file list in the LoadGameMenu.
 * Updates the selected file text field when a file is selected from the list.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class FileListListener implements ListSelectionListener {
    private JList<String> fileList;
    private JTextField selectedFileField;

    /**
     * Constructor that initializes the JList and JTextField references.
     * 
     * @param fileList The file list component
     * @param selectedFileField The text field to update with the selected file
     */
    public FileListListener(JList<String> fileList, JTextField selectedFileField) {
        this.fileList = fileList;
        this.selectedFileField = selectedFileField;
    }

    /**
     * Updates the text field with the selected file path when a list selection changes.
     * 
     * @param e The list selection event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedValue = this.fileList.getSelectedValue();
            if (selectedValue != null) {
                this.selectedFileField.setText(selectedValue);
            } else {
                this.selectedFileField.setText("");
            }
        }
    }
}
