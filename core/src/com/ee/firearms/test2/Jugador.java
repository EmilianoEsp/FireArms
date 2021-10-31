package com.ee.firearms.test2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ee.firearms.utiles.Render;

public class Jugador {
	
	private Vector2 position;
	private Texture textura;
	private int speed = 10;
	public Rectangle rect;
	
	public Jugador(Vector2 position, Texture textura, Rectangle rect) {
		this.position = position;
		this.textura = textura;
		this.rect = rect;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Texture getTextura() {
		return textura;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void dibujar() {
		Render.b.draw(this.getTextura(), this.position.x, this.position.y);
	}

	public void move(Vector2 nuevaPos) {
		float posX = this.position.x + nuevaPos.x;
		this.setPositionX(posX);
	}

	public void setPositionX(float posX) {
		this.position.x = posX;
	}
}
