package com.fwumdesoft.api.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.fwumdesoft.api.math.geom.TileMap;

/**
 * A more advanced version of the LibGDX Actor class <br/>
 * It uses single-method interfaces to allow for easy lambda integration. <br/>
 * @author Ryan Goldstein
 */
public class Entity extends Actor implements Disposable
{
	/**
	 * The default 'method' for drawing
	 */
	private static final ActionProperty<Batch> DEFAULT_DRAW = (entity, batch) ->
	{
		batch.draw(entity.texture, entity.getX(), entity.getY(), entity.getOriginX(), entity.getOriginY(), 
				entity.getWidth(), entity.getHeight(), entity.getScaleX(), entity.getScaleY(), entity.getRotation());
	};
	/**
	 * Action taken when the Entity is disposed. <br>
	 * Pass the Entity object itself as the parameter, and use the parameter as the reference. <br>
	 * Usually the last action the object will take, so cleanup actions are ideal here.
	 */
	public Action destroy;
	/**
	 * The action taken to update the Entity every frame. <br>
	 * Pass the Entity object itself as the parameter, and use the parameter as the reference. <br>
	 * The Float property is the delta time- the number of milliseconds since the last update.
	 * Update the Entity's state here. 
	 */
	public ActionProperty<Float> update;
	/**
	 * The way the Entity is drawn every frame. <br>
	 * Pass the Entity object itself as the parameter, and use the parameter as the reference. <br>
	 * The Batch property is the batch that will be used to draw the Entity.
	 */
	public ActionProperty<Batch> draw;
	/**
	 * The texture region that is drawn each frame.
	 */
	public TextureRegion texture;
	/**
	 * The velocity of the Entity <br>
	 * You must use it yourself, it will not be automatically applied
	 */
	@Deprecated
	public float xSpeed, ySpeed;
	/**
	 * The amount of health the Entity has <br>
	 * You must use it yourself, it will not be automatically applied
	 */
	public int health;
	/**
	 * The timeout of the Entity's actions <br>
	 * You must use it yourself, it will not be automatically applied
	 */
	public int timeout;
	
	private final Vector2 speed;
	private boolean lockForward;
	private final Polygon boundingBox;
	private final Vector2 tmpV;
	/**
	 * Create a new Entity with an empty update function
	 */
	public Entity()
	{
		super();
		draw = DEFAULT_DRAW;
		speed = new Vector2(0, 0);
		lockForward = false;
		boundingBox = new Polygon();
		boundingBox.setVertices(new float[]{0,0 , 0,0 , 0,0 , 0,0});
		tmpV = new Vector2(0, 0);
	}
	
	/**
	 * Create a new Entity
	 * @param update The update function for the Entity <br>
	 * The entity to update is the first parameter and the milliseconds since the last update is the second.
	 */
	public Entity(ActionProperty<Float> update)
	{
		this();
		this.update = update;
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		if(update != null)
			update.doAction(this, delta);
	}
	
	@Override
	public void draw(Batch batch, float alpha)
	{
		Color previous = batch.getColor();
		batch.setColor(previous.r, previous.g, previous.b, alpha);
		draw.doAction(this, batch);
		batch.setColor(previous);
	}
	
	/**
	 * Checks if a point is contained within the bounds of the Entity considering rotation
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(float x, float y)
	{
		return boundingBox.contains(x, y);
	}
	
	/**
	 * Checks if a point is contained within the bounds of the Entity
	 * @param vector The position to check
	 * @return If the point falls inside the Entity's bounding box
	 */
	public boolean contains(Vector2 vector)
	{
		return boundingBox.contains(vector.x, vector.y);
	}
	
	@Override
	public void setBounds(float x, float y, float width, float height)
	{
		boundingBox.setVertices(new float[] { 0,0 , width,0 , 0,height , width,height});
		setWidth(width);
		setHeight(height);
		setPosition(x, y);
	}
	
	@Override
	public void moveBy(float x, float y)
	{
		boundingBox.translate(x, y);
	}
	
	@Override
	public void setX(float x)
	{
		boundingBox.setPosition(x, boundingBox.getY());
	}
	
	@Override
	public void setY(float y)
	{
		boundingBox.setPosition(boundingBox.getX(), y);
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		setX(x);
		setY(y);
	}
	
	@Override
	public float getX()
	{
		return boundingBox.getX();
	}
	
	@Override
	public float getY()
	{
		return boundingBox.getY();
	}

	
	/**
	 * Checks to see if the Entity is overlapping another bounding box
	 * @return If the Entity is overlapping the other box
	 */
	public boolean overlaps(float x, float y, float width, float height)
	{
		Rectangle rect = Rectangle.tmp;
		rect.set(x, y, width, height);
		boolean contains = contains(x, y) || contains(x + width, y) || 
				contains(x, y + height) || contains(x + width, y + height);
		contains |= rect.contains(getX(), getY());
		tmpV.x = getX() + getWidth();
		tmpV.y = getY() + getHeight();
		tmpV.rotate(getRotation());
		contains |= rect.contains(getX() + tmpV.x, getY()) || rect.contains(getX(), getY() + tmpV.y);
		return  contains || rect.contains(getX() + tmpV.x, getY() + tmpV.y);
	}
	
	/**
	 * Checks to see if the Entity is overlapping another bounding box
	 * @return If the Entity is overlapping the other box
	 */
	public boolean overlaps(Entity other)
	{
		return overlaps(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	
	/**
	 * Checks to see if the Entity is overlapping another bounding box
	 * @return If the Entity is overlapping the other box
	 */
	public boolean overlaps(Rectangle box)
	{
		return overlaps(box.x, box.y, box.width, box.height);
	}
	
	/**
	 * Attempt to move if the map allows it
	 * @param map The TileMap to check against
	 * @param translateX The amount to attempt to move
	 * @return If the move was successful
	 */
	public boolean contactX(TileMap map, float translateX)
	{
		boolean moved;
		if(moved = map.free(getX() + translateX, getY(), getWidth(), getHeight()))
			moveBy(translateX, 0);
		return moved;
	}
	
	/**
	 * Attempt to move if the map allows it
	 * @param map The TileMap to check against
	 * @param translateY The amount to attempt to move
	 * @return If the move was successful
	 */
	public boolean contactY(TileMap map, float translateY)
	{
		boolean moved;
		if(moved = map.free(getX(), getY() + translateY, getWidth(), getHeight()))
			moveBy(0, translateY);
		return moved;
	}
	
	/**
	 * Rotates the Entity so that it faces the point <xSpeed, ySpeed> would translate it to
	 * @param lock If the Entity should lock its rotation to its momentum
	 */
	public void faceForward(boolean lock)
	{
		lockForward = lock;
	}
	
	@Override
	public void rotateBy(float degrees)
	{
		super.rotateBy(degrees);
		boundingBox.rotate(degrees);
		if(lockForward)
			speed.rotate(degrees);
	}
	
	public Rectangle boundingBox()
	{
		return boundingBox.getBoundingRectangle();
	}
	
	@Override
	public void setRotation(float rotation)
	{
		super.setRotation(rotation);
		boundingBox.rotate(rotation);
		if(lockForward)
			speed.rotate(rotation);
	}
	
	@Override
	public float getRotation()
	{
		return 360 - (boundingBox.getRotation() % 360);
	}
	
	public void setSpeed(float x, float y)
	{
		speed.x = x;
		speed.y = y;
		updateFacing();
	}
	
	public void setXSpeed(float x)
	{
		speed.x = x;
		updateFacing();
	}
	
	public void setYSpeed(float y)
	{
		speed.y = y;
		updateFacing();
	}
	
	public float getXSpeed()
	{
		return speed.x;
	}
	
	public float getYSpeed()
	{
		return speed.y;
	}
	
	public Vector2 getSpeed()
	{
		return speed;
	}
	
	private void updateFacing()
	{
		if(lockForward)
			setRotation((float)Math.toDegrees(Math.atan2(speed.y, speed.x)));
	}
	
	/**
	 * Increase or decrease the speed of the entity
	 * @param speedAmt The amount to add to the speed
	 */
	public void accelerate(float speedAmt)
	{
		if(lockForward)
		{
			speed.x += Math.cos(Math.toRadians(getRotation())) * speedAmt;
			speed.y += Math.sin(Math.toRadians(getRotation())) * speedAmt;
		}
		else
		{
			float rotation = (float) Math.atan2(speed.y, speed.x);
			speed.x += Math.cos(rotation) * speedAmt;
			speed.y += Math.sin(rotation) * speedAmt;
		}
	}
	
	/**
	 * Scale the speed of the entity
	 * @param scale The number to multiply the speed by
	 */
	public void accelerateScale(float scale)
	{
		speed.scl(scale);
	}
	
	/**
	 * Increase the speed of the Entity
	 * @param x The amount to increase
	 * @param y The amount to increase
	 */
	public void accelerate(float x, float y)
	{
		speed.add(x, y);
	}
	
	public void moveBySpeed()
	{
		moveBy(speed.x, speed.y);
	}
	
	@Override
	public void dispose()
	{
		if(destroy != null)
			destroy.doAction(this);
		remove();
	}
	
	/**
	 * Intended for use with Lambdas (Java 8 or higher)
	 * Take an action using an Entity 
	 * @author Ryan Goldstein
	 */
	@FunctionalInterface
	public static interface Action
	{
		/**
		 * Intended for use with Lambdas (Java 8 or higher)
		 * Take an action using an Entity 
		 * @param e The Entity to update
		 */
		abstract public void doAction(Entity e);
	}
	
	/**
	 * Intended for use with Lambdas (Java 8 or higher)
	 * Take an action with a secondary property
	 * @param T The type of the other property
	 * @author Ryan Goldstein
	 */
	@FunctionalInterface
	public static interface ActionProperty<T>
	{
		/**
		 * Intended for use with Lambdas (Java 8 or higher)
		 * Take an action with a secondary property
		 * @param e The Entity to update
		 * @param other The property to use
		 */
		abstract public void doAction(Entity e, T other);
	}
	
	/**
	 * Spawn an Entity with similar properties
	 * It is good form to define the update and draw functions in the spawn method, as well as the name
	 * @author Ryan Goldstein
	 */
	@FunctionalInterface
	@Deprecated
	public static interface Spawner
	{
		/**
		 * Create a new instance of the Entity
		 * @param x The x position to spawn at
		 * @param y The y position to spawn at
		 * @return The spawned Entity
		 */
		abstract public Entity spawn(float x, float y);
	}
}