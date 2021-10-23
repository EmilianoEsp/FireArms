package com.ee.firearms.elementos;

public abstract class Personaje {

	private int nivel = 1, experiencia = 0;
	private int posX, posY;
	private String nombre;
	private Imagen img;
	//private Textura textura;
	
	public Personaje(String nombre, Imagen img, int posX, int posY) {
		this.nombre = nombre;
		this.img = img;
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
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

	public Imagen getImg() {
		return img;
	}
}
