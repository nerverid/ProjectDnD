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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
