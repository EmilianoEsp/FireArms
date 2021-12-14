package com.ee.firearms.utiles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.ee.firearms.JuegoAssetManager;

public class ReproductorMusica {
	
	public Music musica[] = { 
							  Gdx.audio.newMusic(Gdx.files.internal(JuegoAssetManager.MUSICA_1)),
							  Gdx.audio.newMusic(Gdx.files.internal(JuegoAssetManager.MUSICA_2)),
							  Gdx.audio.newMusic(Gdx.files.internal(JuegoAssetManager.MUSICA_3)),
							  Gdx.audio.newMusic(Gdx.files.internal(JuegoAssetManager.MUSICA_4)),
							  Gdx.audio.newMusic(Gdx.files.internal(JuegoAssetManager.MUSICA_5)),
							};
	
	private Random random;
	private int musicaActual;
	
	public boolean pausada = false;
	
	public ReproductorMusica() {
		random = new Random();
		musicaActual = random.nextInt(musica.length);
		musica[getMusicaActual()].play();
		musica[getMusicaActual()].setVolume(Constantes.volumenMusica/100);
	}
	
	public void update() {
		if (pausada) {
			musica[getMusicaActual()].pause();
		} else musica[getMusicaActual()].play();
		if (!musica[getMusicaActual()].isPlaying() && !pausada) {
			siguiente();
		}
		musica[getMusicaActual()].setVolume(Constantes.volumenMusica/100);
	}

	public int getMusicaActual() {
		return musicaActual;
	}
	
	public void siguiente() {
		musica[getMusicaActual()].stop();
		if(musicaActual < musica.length - 1) {
			musicaActual++;
		} else {
			musicaActual = 0;
		}
		musica[getMusicaActual()].play();
	}
	
	public void anterior() {
		musica[getMusicaActual()].stop();
		if(musicaActual > 0) {
			musicaActual--;
			} else {
				musicaActual = musica.length - 1;
			}
		musica[getMusicaActual()].play();
	}
	
}