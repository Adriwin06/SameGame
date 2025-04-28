package SameGame;

import javax.swing.*;
import java.awt.*;

/**
 * Custom JComponent that displays a logo image in the main menu.
 * This class is responsible for loading the image and painting it
 * in the center of the component while maintaining its aspect ratio.
 * 
 * @author Adriwin
 * @version 1.0
 * 
 * @see JComponent
 */
public class MainMenuLogo extends JComponent {
	private Image image;

	/**
	 * Constructor that initializes the logo image with the specified path.
	 * 
	 * @param path The path to the logo image file.
	 */
	public MainMenuLogo(String path) {
		super();
		this.image = Toolkit.getDefaultToolkit().getImage(path);
	}

	/**
	 * Displays the logo image in the center of the component.
	 * The image is scaled to fit within the component while maintaining its aspect ratio.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = g.create();
		if (this.isOpaque()) {
		  g2.setColor(this.getBackground());
		  g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
		// Setting the variables
		int compWidth = getWidth();
		int compHeight = getHeight();
		int imgWidth = image.getWidth(this);
		int imgHeight = image.getHeight(this);

		// Keeping the aspect ratio of the image
		double scale = Math.min((double) compWidth / imgWidth, (double) compHeight / imgHeight);
		int drawWidth = (int) (imgWidth * scale);
		int drawHeight = (int) (imgHeight * scale);

		int x = (compWidth - drawWidth) / 2;
		int y = (compHeight - drawHeight) / 2;

		g2.drawImage(image, x, y, drawWidth, drawHeight, this);
		g2.dispose();
	}
}