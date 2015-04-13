package com.fwumdesoft.api.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.fwumdesoft.api.math.geom.TileMap;

public class Entity extends Actor implements Disposable
{
	private static final ActionProperty<Batch> DEFAULT_DRAW = (entity, batch) ->
	{
		batch.draw(entity.texture, entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
	};
	public Action destroy;
	public ActionProperty<Float> update;
	public ActionProperty<Batch> draw;
	public TextureRegion texture;
	public float xSpeed, ySpeed;
	public int jump, health, timeout;
	
	public Entity()
	{
		super();
		draw = DEFAULT_DRAW;
	}
	
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
	
	public boolean contains(float x, float y)
	{
		return x >= getX() && y >= getY() && x < getX() + getWidth() && y < getY() + getHeight();
	}
	
	public boolean contains(Vector2 vector)
	{
		return contains(vector.x, vector.y);
	}
	
	public boolean overlaps(float x, float y, float width, float height)
	{
		return getX() < x + width && getX() + getWidth() > x && 
				getY() < y + height && getY() + getHeight() > y;
	}
	
	public boolean overlaps(Entity other)
	{
		return overlaps(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	
	public boolean overlaps(Rectangle box)
	{
		return overlaps(box.x, box.y, box.width, box.height);
	}
	
	public boolean contactX(TileMap map, float translateX)
	{
		boolean moved;
		if(moved = map.free(getX() + translateX, getY(), getWidth(), getHeight()))
			moveBy(translateX, 0);
		return moved;
	}
	
	public boolean contactY(TileMap map, float translateY)
	{
		boolean moved;
		if(moved = map.free(getX(), getY() + translateY, getWidth(), getHeight()))
			moveBy(0, translateY);
		return moved;
	}
	
	@Override
	public void dispose()
	{
		if(destroy != null)
			destroy.doAction(this);
		remove();
	}
	
	public static interface Action
	{
		abstract public void doAction(Entity e);
	}
	
	public static interface ActionProperty<T>
	{
		abstract public void doAction(Entity e, T other);
	}
	
	public static interface Spawner
	{
		abstract public Entity spawn(float x, float y);
	}
}