package com.ee.firearms.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ee.firearms.utiles.Render;

public class Imagen {
	
	private Texture t;
	private Sprite s;
	
	public Imagen(String ruta) {
		t = new Texture(ruta);
		s = new Sprite(t);
	}
	
	public void dibujar() {
		s.draw(Render.sb);
	}
	
	public void setTransparencia(float a) {
		s.setAlpha(a);
	}
	
	public void setSize(float ancho, float alto) {
		s.setSize(ancho, alto);
	}
	
	public void setPosition(float x, float y) {
		s.setPosition(x, y);
	}
	
	public float getWidth() {
		return s.getWidth();
	}
	
	public float getHeight() {
		return s.getHeight();
	}
}
