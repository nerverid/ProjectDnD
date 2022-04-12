package roguelike.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import roguelike.helppanel.HelpPanel;
import roguelike.graphics.*;

public class UiDesk extends JFrame implements KeyListener, MouseListener{

	private HelpPanel terminal;
	private AsciiCamera camera;
	private Queue<InputEvent> inputQueue;
	
	private int screenWidth;
	private int screenHeight;
	
	public UiDesk(int screenWidth, int screenHeight, Rectangle mapDimensions) {
		super("Roguelike");
		
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		inputQueue = new LinkedList<>();
		
		Rectangle gameViewArea = new Rectangle(screenWidth, screenHeight-5);
		terminal = new HelpPanel(screenWidth, screenHeight);
		camera = new AsciiCamera(mapDimensions, gameViewArea);
		
		super.add(terminal);
		super.addKeyListener(this);
		super.addMouseListener(this);
		super.setSize(screenWidth*9, screenHeight*16);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.repaint();
	}
	
	public HelpPanel getTerminal() {
		return terminal;
	}
	
	public InputEvent getNextInput() {
		return inputQueue.poll();
	}
	
	public void pointCameraAt(World world, int xfocus, int yfocus) {
		camera.lookAt(terminal, world, xfocus, yfocus);
	}
	
	public void refresh() {
		terminal.repaint();
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputQueue.add(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawDynamicLegend(Rectangle gameViewArea, World world, Map<String, Map<String, String>> tileData, Map<String, Map<String, String>> creatureData) {
		int x = 5;
		int y = gameViewArea.height;
		char glyph;
		
		for (String tileType : world.getTileTypesInArea(gameViewArea)) {
			glyph = tileData.get(tileType).get("glyph").charAt(0);
			terminal.write(glyph + "  " + titleType, x, y);
			y += 1;
			
			if(y == gameViewArea.height+2) {
				x += 15;
				y = gameViewArea.height;
			}
		}
		
	}

}
