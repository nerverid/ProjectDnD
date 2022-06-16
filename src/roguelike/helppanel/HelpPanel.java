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
	
	private Image offscreenBuffer;
	private Graphics offscreenGraphics;
	private int widthInCharacters;
	private int heightInCharacters;
	private int charWidth = 0;
	private int charHeight = 16;
	private String terminalFontFile = "cp437_9x16.png";
	private Color defaultBackgroundColor;
	private Color defaultForegroundColor;
	private int cursorX;
	private int cursorY;
	private BufferedImage glyphSprite;
	private BufferedImage[] glyphs;
	private char[][] chars;
	private Color[][] backgroundColors;
	private Color[][] foregroundColors;
	private char[][] oldChars;
	private Color[][] oldBackgroundColors;
	private Color[][] oldForegroundColors;
	private HelpFont helpFont;
	
	public int getCharHeight() {
		return charHeight;
	}
	
	public int getCharWidth() {
		return charWidth;
	}
	
	
	public int getHeightInCharacters() {
		return heightInCharacters;
	}
	
	public int getWidthInCharacters() {
		return widthInCharacters;
	}
	
	public int getCursorX() {
		return cursorX;
	}
	
	public void setCursorX(int cursorX) {
		if(cursorX < 0 || cursorX >= widthInCharacters) 
			throw new IllegalArgumentException("cursorX " + cursorX + " must be within range [0, " +
		widthInCharacters + ").");
			this.cursorX = cursorX;
	}
	
	public int getCursorY() {
		return cursorY;
	}
	/**
	 * Class constructor
	 * Default size is 80x24
	 */
	
	public void setCursorY(int cursorY) {
		if(cursorY < 0 || cursorY >= heightInCharacters)
			throw new IllegalArgumentException("cursorY " + cursorY + " must be within range [0," + heightInCharacters + ").");
	
		this.cursorY = cursorY;
	}
	
	public void setCursorPosition(int x, int y) {
		setCursorX(x);
		setCursorY(y);
	}
	
	public Color getDefaultBackgroundColor() {
		return defaultBackgroundColor;
	}
	
	public void setDefaultBackgroundColor(Color defaultBlackgroundColor) {
		if (defaultBackgroundColor == null)
			throw new NullPointerException("defaultBackgroundColor must not be null.");
		
		this.defaultBackgroundColor = defaultBlackgroundColor;
	}
	
	public Color getDefaultForegroundColor() {
		return defaultForegroundColor;
	}
	
	public void setDefaultForegroundColor(Color defaultForegroundColor) {
		if (defaultForegroundColor == null)
			throw new NullPointerException("defaultForegroundColor must not be null.");
		
		this.defaultForegroundColor = defaultForegroundColor;
	}
	
	public HelpFont getHelpFont() {
		return helpFont;
	}
	
	public void setHelpFont(HelpFont font) {
		if(this.helpFont == font) {
			return;
		}
		this.helpFont = font;
		
		this.charHeight = font.getHeight();
		this.charWidth = font.getWidth();
		this.terminalFontFile = font.getFontFilename();
		
		Dimension panelSize = new Dimension(charWidth * widthInCharacters, charHeight * heightInCharacters);
		setPreferredSize(panelSize);
		
		glyphs = new BufferedImage[256];
		
		offscreenBuffer = new BufferedImage(panelSize.width, panelSize.height, BufferedImage.TYPE_INT_RGB);
		offscreenGraphics = offscreenBuffer.getGraphics();
		
		loadGliphs();
		
		oldChars = new char[widthInCharacters][heightInCharacters];
	}
	
	
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
	
	public void update(Graphice g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		if (g == null)
			throw new NullPointerException();
		
		for (int x = 0; x < widthInCharacters; x++) {
			for (int y = 0; y < heightInCharacters; y++) {
				if (oldBackgroundColors[x][y] == backgroundColors[x][y]
						&& oldForegroundColors[x][y] == foregroundColors[x][y]
						&& oldChars[x][y] == chars[x][y])
							continue;
				
				Color bg = backgroundColors[x][y];
				Color fg = foregroundColors[x][y];
				
				LookupOp op = setColors(bg, fg);
				BufferedImage img = op.filter(glyphs[char[x][y]], null);
				offscreenGraphics.drawImage(img, x * charWidth, y * charHeight, null);
				
				oldBackgroundColors[x][y] = backgroundColors[x][y];
				oldForegroundColors[x][y] = foregroundColors[x][y];
				oldChars[x][y] = chars[x][y];
			}
		}
		
		g.drawImage(offscreenBuffer, 0,0, this);
	}
	
	private void loadGlyphs() {
		try {
			glyphSprite = ImageIO.read(HelpPanel.class.getClassLoader().getResource(terminalFontFile));
		} catch (IOException e) {
			System.err.println("loadGlyphs(): " + e.getMessage());
		}
		
		for (int i = 0; i < 256; i++) {
			int sx = (i % 16) * charWidth;
			int sy = (i / 16) * charHeight;
			
			glyphs[i] = new BufferedImage(charWidth, charHeight, BufferedImage.TYPE_INT_ARGB);
			glyphs[i].getGraphics().drawImage(glyphSprite, 0, 0, charWidth, charHeight, sx, sy, sx + charWidth, sy + charHeight, null)
		}
	}
	
	private LookupOp setColors(Color bgColor, Color fgColor) {
		short[] a = new short[256];
		short[] r = new short[256];
		short[] g = new short[256];
		short[] b = new short[256];
		
		byte bga = (byte) (bgColor.getAlpha());
		byte bgr = (byte)(bgColor.getRed());
		byte bgg = (byte)(bgColor.getGreen());
		byte bgb = (byte)(bgColor.getBlue());
		
		byte fga = (byte) (fgColor.getAlpha());
		byte fgr = (byte) (fgColor.getRed());
		byte fgg = (byte) (fgColor.getGreen());
		byte fgb = (byte) (fgColor.getBlue());
		
		for (int i = 0; i<256; i++) {
			if(i == 0) {
				a[i] = bga;
				r[i] = bgr;
				g[i] = bgg;
				b[i] = bgb;
			} else {
				a[i] = fga;
				r[i] = fgr;
				g[i] = fgg;
				b[i] = fgb;
			}
		}
		
		short [][] table = {r, g, b, a};
		return new LookupOp(new ShortLookupTable(0, table), null);
	}
	
	public HelpPanel clear() {
		return clear (' ', 0 , 0, widthInCharacters, heightInCharacters, defaultForegroundColor, defaultBackgroundColor);
	}
	
	public HelpPanel clear(char character) {
		if(character < 0 || character >= glyphs.length)
			throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "].");
		return clear(character, 0,0, widthInCharacters, heightInCharacters, defaultForegroundColor, defaultBackgroundColor);
	}
	
	public HelpPanel clear(char character, Color foreground, Color background) {
		if(character < 0 || character >= glyphs.length)
			throw new IllegalArgumentException("character" + character + " must be within range [0, " + glyphs.length + "].");
		
		return clear (character, 0 , 0 ,widthInCharacters, heightInCharacters, foreground, background);
	}
}
