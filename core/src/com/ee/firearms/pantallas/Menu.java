package com.ee.firearms.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.ee.firearms.elementos.Imagen;
import com.ee.firearms.elementos.Texto;

public abstract class Menu implements Screen {

	private Music musica;
	private Imagen fondo;
	private Texto opciones[];
	private String textos[] = {};
	
	public Menu(Music musica, Imagen fondo, Texto opciones[], String textos[]) {
		this.musica = musica;
		this.fondo = fondo;
		this.opciones = opciones;
		this.textos = textos;
	}

	public Music getMusica() {
		return musica;
	}

	public Imagen getFondo() {
		return fondo;
	}

	public Texto[] getOpciones() {
		return opciones;
	}

	public String[] getTextos() {
		return textos;
	}
	
}
