package com.fwumdegames.api.graphics;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import com.fwumdegames.api.math.Fraction;

/**
 * FwumDeGames version of the Graphics2D object.<br>
 * @author Jason Carrete
 * @since Oct 15, 2014
 */
public class FGraphics2D extends Graphics2D
{
	private Graphics2D g2;
	
	public FGraphics2D(Graphics2D g2)
	{
		this.g2 = g2;
	}
	
	public void drawLine(Fraction m, int yIntercept)
	{
		int width = g2.getClipBounds().width, height = g2.getClipBounds().height;
		
		Point p1 = new Point(0, yIntercept), p2 = new Point(0, yIntercept);
		while(p2.x >= 0 && p2.x <= width && p2.y >= 0 && p2.y <= height)
		{
			p2.y += m.getNumerator();
			p2.x += m.getDenominator();
		}
		
		g2.drawLine(p1.x, p1.y, p2.x, p2.y);
	}
	
	public void drawLine(double m, int yIntercept)
	{
		
	}
	
	public void drawLine(double m, Point p1)
	{
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#addRenderingHints(java.util.Map)
	 */
	@Override
	public void addRenderingHints(Map<?, ?> arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#clip(java.awt.Shape)
	 */
	@Override
	public void clip(Shape arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#draw(java.awt.Shape)
	 */
	@Override
	public void draw(Shape arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawGlyphVector(java.awt.font.GlyphVector, float, float)
	 */
	@Override
	public void drawGlyphVector(GlyphVector arg0, float arg1, float arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawImage(java.awt.Image, java.awt.geom.AffineTransform, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, AffineTransform arg1,
			ImageObserver arg2)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawImage(java.awt.image.BufferedImage, java.awt.image.BufferedImageOp, int, int)
	 */
	@Override
	public void drawImage(BufferedImage arg0, BufferedImageOp arg1, int arg2,
			int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawRenderableImage(java.awt.image.renderable.RenderableImage, java.awt.geom.AffineTransform)
	 */
	@Override
	public void drawRenderableImage(RenderableImage arg0, AffineTransform arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawRenderedImage(java.awt.image.RenderedImage, java.awt.geom.AffineTransform)
	 */
	@Override
	public void drawRenderedImage(RenderedImage arg0, AffineTransform arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawString(java.lang.String, int, int)
	 */
	@Override
	public void drawString(String arg0, int arg1, int arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawString(java.lang.String, float, float)
	 */
	@Override
	public void drawString(String arg0, float arg1, float arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawString(java.text.AttributedCharacterIterator, int, int)
	 */
	@Override
	public void drawString(AttributedCharacterIterator arg0, int arg1, int arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#drawString(java.text.AttributedCharacterIterator, float, float)
	 */
	@Override
	public void drawString(AttributedCharacterIterator arg0, float arg1,
			float arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#fill(java.awt.Shape)
	 */
	@Override
	public void fill(Shape arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getBackground()
	 */
	@Override
	public Color getBackground()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getComposite()
	 */
	@Override
	public Composite getComposite()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getDeviceConfiguration()
	 */
	@Override
	public GraphicsConfiguration getDeviceConfiguration()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getFontRenderContext()
	 */
	@Override
	public FontRenderContext getFontRenderContext()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getPaint()
	 */
	@Override
	public Paint getPaint()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getRenderingHint(java.awt.RenderingHints.Key)
	 */
	@Override
	public Object getRenderingHint(Key arg0)
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getRenderingHints()
	 */
	@Override
	public RenderingHints getRenderingHints()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getStroke()
	 */
	@Override
	public Stroke getStroke()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#getTransform()
	 */
	@Override
	public AffineTransform getTransform()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#hit(java.awt.Rectangle, java.awt.Shape, boolean)
	 */
	@Override
	public boolean hit(Rectangle arg0, Shape arg1, boolean arg2)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#rotate(double)
	 */
	@Override
	public void rotate(double arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#rotate(double, double, double)
	 */
	@Override
	public void rotate(double arg0, double arg1, double arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#scale(double, double)
	 */
	@Override
	public void scale(double arg0, double arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setBackground(java.awt.Color)
	 */
	@Override
	public void setBackground(Color arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setComposite(java.awt.Composite)
	 */
	@Override
	public void setComposite(Composite arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setPaint(java.awt.Paint)
	 */
	@Override
	public void setPaint(Paint arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setRenderingHint(java.awt.RenderingHints.Key, java.lang.Object)
	 */
	@Override
	public void setRenderingHint(Key arg0, Object arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setRenderingHints(java.util.Map)
	 */
	@Override
	public void setRenderingHints(Map<?, ?> arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setStroke(java.awt.Stroke)
	 */
	@Override
	public void setStroke(Stroke arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#setTransform(java.awt.geom.AffineTransform)
	 */
	@Override
	public void setTransform(AffineTransform arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#shear(double, double)
	 */
	@Override
	public void shear(double arg0, double arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#transform(java.awt.geom.AffineTransform)
	 */
	@Override
	public void transform(AffineTransform arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#translate(int, int)
	 */
	@Override
	public void translate(int arg0, int arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics2D#translate(double, double)
	 */
	@Override
	public void translate(double arg0, double arg1)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#clearRect(int, int, int, int)
	 */
	@Override
	public void clearRect(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#clipRect(int, int, int, int)
	 */
	@Override
	public void clipRect(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#copyArea(int, int, int, int, int, int)
	 */
	@Override
	public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#create()
	 */
	@Override
	public Graphics create()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#dispose()
	 */
	@Override
	public void dispose()
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawArc(int, int, int, int, int, int)
	 */
	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3,
			ImageObserver arg4)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, ImageObserver arg5)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, Color arg5, ImageObserver arg6)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, int, int, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, int arg7, int arg8, ImageObserver arg9)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, int arg7, int arg8, Color arg9,
			ImageObserver arg10)
	{
		// AUTO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawLine(int, int, int, int)
	 */
	@Override
	public void drawLine(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawOval(int, int, int, int)
	 */
	@Override
	public void drawOval(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawPolygon(int[], int[], int)
	 */
	@Override
	public void drawPolygon(int[] arg0, int[] arg1, int arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawPolyline(int[], int[], int)
	 */
	@Override
	public void drawPolyline(int[] arg0, int[] arg1, int arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawRoundRect(int, int, int, int, int, int)
	 */
	@Override
	public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillArc(int, int, int, int, int, int)
	 */
	@Override
	public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillOval(int, int, int, int)
	 */
	@Override
	public void fillOval(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillPolygon(int[], int[], int)
	 */
	@Override
	public void fillPolygon(int[] arg0, int[] arg1, int arg2)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillRect(int, int, int, int)
	 */
	@Override
	public void fillRect(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillRoundRect(int, int, int, int, int, int)
	 */
	@Override
	public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getClip()
	 */
	@Override
	public Shape getClip()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getClipBounds()
	 */
	@Override
	public Rectangle getClipBounds()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getColor()
	 */
	@Override
	public Color getColor()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getFont()
	 */
	@Override
	public Font getFont()
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getFontMetrics(java.awt.Font)
	 */
	@Override
	public FontMetrics getFontMetrics(Font arg0)
	{
		// AUTO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setClip(java.awt.Shape)
	 */
	@Override
	public void setClip(Shape arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setClip(int, int, int, int)
	 */
	@Override
	public void setClip(int arg0, int arg1, int arg2, int arg3)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setFont(java.awt.Font)
	 */
	@Override
	public void setFont(Font arg0)
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setPaintMode()
	 */
	@Override
	public void setPaintMode()
	{
		// AUTO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setXORMode(java.awt.Color)
	 */
	@Override
	public void setXORMode(Color arg0)
	{
		// AUTO Auto-generated method stub
		
	}
}
