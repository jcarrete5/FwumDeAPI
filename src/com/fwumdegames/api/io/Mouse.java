package com.fwumdegames.api.io;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.JComponent;

/**
 * Holds the data for the mouse
 * @author Ryan Goldstein
 */
public class Mouse extends MouseAdapter implements MouseMotionListener
{
	private static final HashMap<Integer, Boolean> buttons;
	private static final Point position;
	private static final Mouse instance;
	private static JComponent comp;
	
	static
	{
		buttons = new HashMap<Integer, Boolean>();
		position = new Point();
		instance = new Mouse();
	}
	
	private Mouse() {}
	
	public static Mouse getInstance(JComponent c)
	{
		comp = c;
		return instance;
	}
	
	/**
	 * Gets the current mouse position. <br>
	 * The position is relative to the component the instance was added to.
	 * @return The (x, y) position of the mouse cursor
	 */
	public static Point getPosition()
	{
		System.out.println(comp);
		return comp.getMousePosition();
	}
	
	/**
	 * Check to see if a button is pressed
	 * @param button The key code from MouseEvent
	 * @return If the button is currently being held
	 */
	public static boolean pressed(int button)
	{
		return buttons.containsKey(button) && buttons.get(button);
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		position.x = e.getX();
		position.y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		buttons.put(e.getButton(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		buttons.put(e.getButton(), false);
	}
}
