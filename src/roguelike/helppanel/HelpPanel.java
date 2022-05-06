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
	
	public static Color black = new Color(0, 0, 0);
	public static Color red = new Color (128, 0, 0);
	public static Color green = new Color (0, 128, 0);
	public static Color yellow = new Color (128, 128, 0);
	public static Color blue = new Color (0, 0, 128);
	public static Color magenta = new Color (128, 0, 128);
	public static Color cyan = new Color (0, 128, 128);
	public static Color white = new Color (192, 192, 192);
	public static Color brightBlack = new Color (128, 128, 128);
	public static Color brightRed = new Color (255, 0, 0);
	public static Color brightGreen = new Color (0, 255, 0);
	public static Color brightYellow = new Color (255, 255, 0);
	public static Color brightBlue = new Color (0, 0, 255);
	public static Color brightMagenta = new Color (255, 0 , 255);
	public static Color brightCyan = new Color (0, 255, 255);
	public static Color brightWhite = new Color (255, 255, 255);
	
	private Image offcreenBuffer;
	private Graphics offscreenGraphics;
	private int widthInCharacters;
	private int heightInCharacters;
	private int charWidth = 0;
	private int charHeight = 16;
	private String terminalFontFile = "cp437_9x16.png";
	private Color defaultBackgroundColor;
	private Color defaultForegroundColor;
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
			throw new IllegalArgumentException("width " + width + " must be gerater than 0.");
		}
		
		if (height < 1) {
			throw new IllegalArgumentException("height " + height + " must be greater than 0.");
		}
		
		widthInCharacters = width;
		heightInCharacters = height;
		
		defaultBackgroundColor = black;
		defaultForegroundColor = white;
		
		chars = new car[widthInCharacters][heightInCharacters];
		backgroundColors = new Color[widthInCharacters][heightInCharacters];
		foregroundColors = new Color[widthInCharacters][heightInCharacters];
		
		oldBackGroundColors = new Color[widthInCharacters][heightInCharacters];
		oldForegroundColors = new Color[widthInCharacters][heightInCharacters];
		
		if(font == null) {
			font = AsciiFont.CP437_9x16;
		}
		setAsciiFont(font);
	}
}
