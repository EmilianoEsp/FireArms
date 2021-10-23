package com.ee.firearms.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

	private Vector2 velocity = new Vector2();;
	private float speed = 60 * 2, gravity = 60 * 1.8f;
	private TiledMapTileLayer collisionLayer;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;
	}
	
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta) {
		velocity.y -= gravity * delta;
		if(velocity.y > speed) {
			velocity.y = speed;
		}else if(velocity.y < speed) {
			velocity.y = -speed;
		}
		
		float oldX = getX(), oldY = getY();
		float tileWidth = collisionLayer.getTileWidth();
		float tileHeight = collisionLayer.getTileHeight();
		boolean collisionX = false, collisionY = false;
		
		setX(getX() + velocity.x * delta);
		
		if (velocity.x < 0) {
			// Arriba a la izquierda
			collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) (getY() + getHeight() / tileHeight))
					.getTile().getProperties().containsKey("blocked");

			// Medio a la izquierda
			if(!collisionX) {
				collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
			
			// Abajo a la izquierda
			if(!collisionX) {
				collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
		} else if (velocity.x > 0) {
			// Arriba a la derecha
			collisionX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
					.getTile().getProperties().containsKey("blocked");
			
			// Medio a la derecha
			if(!collisionX) {
				collisionX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
			
			// Abajo a la derecha
			if(!collisionX) {
				collisionX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
		}
		
		if(collisionX) {
			setX(oldX);
			velocity.x = 0;
		}

		setY(getY() + velocity.y * delta);

		if (velocity.y < 0) {
			// Abajo a la izquierda
			collisionY = collisionLayer.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight))
					.getTile().getProperties().containsKey("blocked");
			
			// Abajo al medio
			if(!collisionY) {
				collisionY = collisionLayer.getCell((int) ((getX() + getWidth() / 2) / tileWidth), (int) (getY() / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
			
			// Abajo a la derecha
			if(!collisionY) {
				collisionY = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
			
		} else if (velocity.y > 0) {
			// Arriba a la izquierda
			collisionY = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
					.getTile().getProperties().containsKey("blocked");
			
			// Arriba al medio
			if(!collisionY) {
				collisionY = collisionLayer.getCell((int) ((getX() + getWidth() / 2) / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
			
			// Arriba a la derecha
			if(!collisionY) {
				collisionY = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			}
		}
		
		if(collisionY) {
			setY(oldY);
			velocity.y = 0;
		}
		
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public float getSpeed() {
		return speed;
	}
	
	public float getGravity() {
		return gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}
	
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	
}
