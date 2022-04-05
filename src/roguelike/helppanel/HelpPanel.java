package roguelike.helppanel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HelpPanel extends JPanel{
	
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
	}
}
