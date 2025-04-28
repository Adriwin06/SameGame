package SameGame.ActionListeners;

import SameGame.*;
import java.awt.event.*;

/**
 * Handles mouse events for the cells in the grid.
 * It highlights connected cells when the mouse enters a cell and clears highlights when it exits.
 * It also breaks connected cells when the cell is clicked.
 * 
 * @author Adriwin
 * @version 1.0
 */
public class CellMouseAdapter extends MouseAdapter {
    private Grid parentGrid;
    private int row;
    private int column;
    private int value;
    private boolean isEmpty = false;

    /**
     * Constructor that initializes the grid, cell position, value, and empty state.
     * 
     * @param parentGrid The parent grid reference.
     * @param row The row position of the cell.
     * @param column The column position of the cell.
     * @param value The value of the cell.
     * @param isEmpty Indicates if the cell is empty.
     */
    public CellMouseAdapter(Grid parentGrid, int row, int column, int value, boolean isEmpty) {
        this.isEmpty = isEmpty;
        this.parentGrid = parentGrid;
        this.row = row;
        this.column = column;
        this.value = value;
    }

    
    /** 
     * Handles mouse entered event.
     * Highlights connected cells if the cell is not empty and has a valid value.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isEmpty && value >= 0) {
            parentGrid.highlightConnectedCells(row, column);
        }
    }
    
    /**
     * Handles mouse exited event.
     * Clears highlights if the cell is not empty and has a valid value.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (!isEmpty && value >= 0) {
            parentGrid.clearHighlights();
        }
    }
    
    /**
     * Handles mouse clicked event.
     * Breaks connected cells if the cell is not empty and has a valid value.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isEmpty && value >= 0) {
            parentGrid.breakConnectedCells(row, column);
        }
    }
}