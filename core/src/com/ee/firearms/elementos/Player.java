package com.ee.firearms.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {

	private Vector2 velocity = new Vector2();
	private float speed = 60 * 2, gravity = 60 * 1.8f;
	private boolean canJump;
	private TiledMapTileLayer collisionLayer;
	
	private String blockedKey = "blocked";
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;
	}
	
	public void draw(SpriteBatch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
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
			collisionX = isCellBlocked(getX(), getY() + getHeight());

			// Medio a la izquierda
			if(!collisionX) {
				collisionX = isCellBlocked(getX(), getY() + getHeight() / 2);
			}
			
			// Abajo a la izquierda
			if(!collisionX) {
				collisionX = isCellBlocked(getX(), getY());
			}
		} else if (velocity.x > 0) {
			// Arriba a la derecha
			collisionX = isCellBlocked(getX() + getWidth(), getY() + getHeight());
			
			// Medio a la derecha
			if(!collisionX) {
				collisionX = isCellBlocked(getX() + getWidth(), getY() + getHeight() / 2);
			}
			
			// Abajo a la derecha
			if(!collisionX) {
				collisionX = isCellBlocked(getX() + getWidth(), getY());
			}
		}
		
		if(collisionX) {
			setX(oldX);
			velocity.x = 0;
		}

		setY(getY() + velocity.y * delta * 5f);

		if (velocity.y < 0) { // Cae
			// Abajo a la izquierda
			collisionY = isCellBlocked(getX(), getY());
			
			// Abajo al medio
			if(!collisionY) {
				collisionY = isCellBlocked(getX() + getWidth() / 2, getY());
			}
			
			// Abajo a la derecha
			if(!collisionY) {
				collisionY = isCellBlocked(getX() + getWidth(), getY());
			}
			
			canJump = collisionY;
			
		} else if (velocity.y > 0) { // Sube
			// Arriba a la izquierda
			collisionY = isCellBlocked(getX(), getY() + getHeight());
			
			// Arriba al medio
			if(!collisionY) {
				collisionY = isCellBlocked(getX() + getWidth() / 2, getY() + getHeight());
			}
			
			// Arriba a la derecha
			if(!collisionY) {
				collisionY = isCellBlocked(getX() + getWidth(), getY() + getHeight());
			}
		}
		
		if(collisionY) {
			setY(oldY);
			velocity.y = 0;
		}
		
	}
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
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

	// Entradas
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Keys.W:
			if(canJump) {
				velocity.y = speed;
				canJump = false;
			}
			System.out.println("Presionaste la W");
			break;
		case Keys.A:
			velocity.x = -speed;
			System.out.println("Presionaste la A");
			break;
		case Keys.D:
			velocity.x = speed;
			System.out.println("Presionaste la D");
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Keys.A:
		case Keys.D:
			velocity.x = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
	
}
