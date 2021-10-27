package com.ee.firearms.elementos;

import com.badlogic.gdx.graphics.Texture;

public abstract class Personaje {

	private int nivel = 1, experiencia = 0;
	private float posX, posY;
	private String nombre;
	private Texture textura;
	
	public Personaje(String nombre, Texture textura, float posX, float posY) {
		this.nombre = nombre;
		this.textura = textura;
		this.posX = posX;
		this.posY = posY;
	}
	
//	public void moverIzquierda() {
//		System.out.println("Se movio a la izquierda");
//		
//		float posX = this.getPosX() - vel;
//		this.setPosX(posX);
//	}
//	
//	public void moverDerecha() {
//		System.out.println("Se movio a la derecha");
//		
//		float posX = this.getPosX() + vel;
//		this.setPosX(posX);
//	}
//	
//	public void saltar() {
//		System.out.println("Ha realizado un salto");
//		
//		float posY = this.getPosY() + vel;
//		
//		if(posY > this.getTextura().getHeight() + 5) {
//			posY = this.getPosY() - vel;
//		}
//		
//		if(posY == this.getTextura().getHeight()) {
//			posY = 0;
//		}
//		this.setPosY(posY);
//	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public int getNivel() {
		return nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public String getNombre() {
		return nombre;
	}

	public Texture getTextura() {
		return textura;
	}
}
