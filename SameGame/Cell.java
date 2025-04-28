package SameGame;

import SameGame.ActionListeners.*;
import javax.swing.*;
import java.awt.*;

/**
 * Represents a single cell in the game grid.
 * It displays an image with different background colors and borders.
 * 
 * @author Adriwin
 * @version 1.3.1
 */
public class Cell extends JComponent {
	// Constants
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final Color HIGHLIGHT_COLORS[] = {
		PlagueTaleLookAndFeel.WARM_AMBER,
		PlagueTaleLookAndFeel.LIGHT_BROWN_HOVER,
		PlagueTaleLookAndFeel.CRIMSON_RED
	};

	// Variables
	private Image image;
	private boolean isHighlighted = false;
	private boolean isEmpty = false;
	private int value;
	
	/**
	 * Constructor for a cell with an image.
	 * 
	 * @param path Image path
	 * @param row Row position
	 * @param column Column position
	 * @param value Cell value
	 * @param parentGrid Reference to the parent grid
	 */
	public Cell(String path, int row, int column, int value, Grid parentGrid) {
		super();
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
		this.image = Toolkit.getDefaultToolkit().getImage(path);
		this.isEmpty = false;
		this.value = value;
		this.addMouseListener(new CellMouseAdapter(parentGrid, row, column, value, isEmpty));
	}
	
	/**
	 * Constructor for an empty cell without an image.
	 * Overloaded constructor to create an empty cell.
	 * 
	 * @param row Row position
	 * @param column Column position
	 * @param parentGrid Reference to the parent grid
	 */
	public Cell(int row, int column, Grid parentGrid) {
		super();
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
		this.isEmpty = true;
	}

	/**
	 * Sets the highlighted state of the cell.
	 * 
	 * @param highlighted Whether the cell should be highlighted
	 */
	public void setHighlighted(boolean highlighted) {
		this.isHighlighted = highlighted;
		repaint();
	}
	
	/**
	 * Sets the cell as empty.
	 */
	public void setEmpty() {
		this.isEmpty = true;
		this.value = -1;
		this.image = null;
		repaint();
	}
	
	
	/** 
	 * Overrides the getPreferredSize method to ensure the cell is square.
	 * The preferred size is set to the maximum of the width and height of the cell.
	 * 
	 * @return The preferred size of the cell as a Dimension object
	 */
	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		int size = Math.max(d.width, d.height);
		return new Dimension(size, size);
	}
	
	/**
	 * Displays the image in the cell.
	 * The image is drawn in the center of the cell, maintaining its aspect ratio.
	 * The cell is highlighted if the isHighlighted flag is set to true.
	 */
	@Override
	protected void paintComponent(Graphics p) {
	  super.paintComponent(p);
	  Graphics p2 = p.create();
	  if (this.isOpaque()) {
		p2.setColor(this.getBackground());
		p2.fillRect(0, 0, this.getWidth(), this.getHeight());
	  }
	  
	  // Only draw the image if the cell is not empty
	  if (!isEmpty) {
		// Calculate the size keep it squared
		int size = Math.min(getWidth(), getHeight());
		
		// Calculate position to center the image
		int x = (getWidth() - size) / 2;
		int y = (getHeight() - size) / 2;
		  
		// Draw highlight overlay if highlighted
		if (isHighlighted) {
			p2.setColor(HIGHLIGHT_COLORS[this.value]);
			p2.fillRect(0, 0, getWidth(), getHeight());
		}

		// Draw the image as a square and center it in the cell
		p2.drawImage(this.image, x, y, size, size, this);
	}

	p2.dispose();
	}
}