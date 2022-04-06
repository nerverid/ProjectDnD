package roguelike.helppanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HelpPanel extends JPanel{
	
	private static Color black = new Color(0, 0, 0);
	/**
	 * Class constructor
	 * Default size is 80x24
	 */
	public HelpPanel() {
		this(80, 24);
	}
	
	/**
	 * Class constructor secifying the width and height in characters.
	 * @param width
	 * @param height
	 */
	public HelpPanel(int width, int height) {
		this(width, height, null);
	}
	
	public HelpPanel(int width, int height, HelpFront front) {
		super();
		
		if (width <1) {
			throw new IllegalArgumentException("width " + widht + " must be gerater than 0.");
		}
		
		if (height < 1) {
			throw new IllegalArgumentException("height " + height + " must be greater than 0.");
		}
		
		widthInCharacters = width;
		heightInCharacters = height;
	}
}
