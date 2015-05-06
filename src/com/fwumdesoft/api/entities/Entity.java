package com.fwumdesoft.api.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	public int jump;
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
	
	/**
	 * Create a new Entity with an empty update function
	 */
	public Entity()
	{
		super();
		draw = DEFAULT_DRAW;
		speed = new Vector2(0, 0);
		lockForward = false;
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
	 * Checks if a point is contained within the bounds of the Entity
	 * @param x The x position
	 * @param y The y position
	 * @return If the point falls inside the Entity's bounding box
	 */
	public boolean contains(float x, float y)
	{
		return x >= getX() && y >= getY() && x < getX() + getWidth() && y < getY() + getHeight();
	}
	
	/**
	 * Checks if a point is contained within the bounds of the Entity
	 * @param vector The position to check
	 * @return If the point falls inside the Entity's bounding box
	 */
	public boolean contains(Vector2 vector)
	{
		return contains(vector.x, vector.y);
	}
	
	/**
	 * Checks to see if the Entity is overlapping another bounding box
	 * @return If the Entity is overlapping the other box
	 */
	public boolean overlaps(float x, float y, float width, float height)
	{
		return getX() < x + width && getX() + getWidth() > x && 
				getY() < y + height && getY() + getHeight() > y;
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
	public void setRotation(float rotation)
	{
		super.setRotation(rotation);
		if(lockForward)
			speed.rotate(rotation);
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
	 * Scale the speed of the entity
	 * @param scale The number to multiply the speed by
	 */
	public void accelerate(float scale)
	{
		speed.scl(scale);
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