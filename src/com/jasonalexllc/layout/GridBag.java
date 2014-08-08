package com.jasonalexllc.layout;

import java.awt.*;
import javax.swing.*;

/**
 * Helper methods for the GridBagLayout manager. Cannot be instantiated
 * @author Jason Carrete
 * @since Aug 7, 2014
 */
public final class GridBag
{
	/**
	 * Can't be instantiated
	 */
	private GridBag() {}
	
	/**
	 * Adds the specified JComponent, c, to the JPanel, p, using the GridBagLayout manager with the specified constraints.
	 * @param p JPanel that has something to be added
	 * @param c JComponent to be added
	 * @param x Column for the JComponent
	 * @param y Row for the JComponent
	 * @param width Amount of x space the JComponent should take up
	 * @param height Amount of y space the JComponent should take up
	 * @param weightx Divides x area with the x-weights of other JComponent
	 * @param weighty Divides y area with the y-weights of other JComponent
	 * @param align The area the JComponent should be anchored to
	 * @param fill Determines if the JComponent should expand to fill an area
	 * @param insets How many pixels the JComponent should be shrunk in on each side
	 */
	public static void add(JPanel p, JComponent c, int x, int y, int width, int height, double weightx, double weighty, int align, int fill, Insets insets)
	{
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = align;
		gc.fill = fill;
		gc.gridheight = height;
		gc.gridwidth = width;
		gc.gridx = x;
		gc.gridy = y;
		gc.insets = insets;
		gc.weightx = weightx;
		gc.weighty = weighty;
		p.add(c, gc);
	}
	
	/**
	 * Adds the specified JComponent, c, to the JPanel, p, using the GridBagLayout manager with the specified constraints.
	 * @param p JPanel that has something to be added
	 * @param c JComponent to be added
	 * @param x Column for the JComponent
	 * @param y Row for the JComponent
	 * @param width Amount of x space the JComponent should take up
	 * @param height Amount of y space the JComponent should take up
	 * @param align The area the JComponent should be anchored to
	 */
	public static void add(JPanel p, JComponent c, int x, int y, int width, int height, int align)
	{
		add(p, c, x, y, width, height, 1.0, 1.0, align, GridBagConstraints.NONE, new Insets(5, 5, 5, 5));
	}
	
	/**
	 * Adds the specified JComponent, c, to the JPanel, p, using the GridBagLayout manager with the specified constraints.
	 * @param p JPanel that has something to be added
	 * @param c JComponent to be added
	 * @param x Column for the JComponent
	 * @param y Row for the JComponent
	 * @param width Amount of x space the JComponent should take up
	 * @param height Amount of y space the JComponent should take up
	 * @param align The area the JComponent should be anchored to
	 * @param fill Determines if the JComponent should expand to fill an area
	 */
	public static void add(JPanel p, JComponent c, int x, int y, int width, int height, int align, int fill)
	{
		add(p, c, x, y, width, height, 1.0, 1.0, align, fill, new Insets(5, 5, 5, 5));
	}}
