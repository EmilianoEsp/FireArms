package com.ee.firearms;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class JuegoAssetManager extends AssetManager {

//	// Database
//	public static final String PERSONAJE_JSON = "Database/personajes.json";
//    public static final String ITEM_JSON = "Database/items.json";
//    public static final String EQUIPO_JSON = "Database/equipo.json";
//    
//    // Fuentes
// 	public static final String FUENTE_QUANTUM = "fuentes/quantum-profit.ttf";
// 	public static final String FUENTE_SECRET = "fuentes/secret-power.otf";
// 	public static final String FUENTE_POSSUM = "fuentes/possum.otf";
//    
    // Imagenes
 	public static final String LOGO = "imagenes/logo.png";
 	public static final String FONDO_MENU = "imagenes/fondoMenu.png";
 	public static final String TITULO_MENU = "imagenes/tituloMenu.png";
 	public static final String TITULO_OPCIONES = "imagenes/tituloOpciones.png";
 	
 	// Musica
 	public static final String MUSICA_1 = "musica/ambient-music.ogg";
 	public static final String MUSICA_2 = "musica/C418_Minecraft.ogg";
 	public static final String MUSICA_3 = "musica/Chopin_Scherzo_No3.ogg";
 	public static final String MUSICA_4 = "musica/Schubert_Impromptu_No3.ogg";
 	public static final String MUSICA_5 = "musica/Schumann_Traumerei.ogg";
 	public static final String MUSICA_JUEGO = "musica/game-music.ogg";
	
 	// Mapas
 	public static final String MAPA_1 = "mapas/primerMapa.tmx";
 	
 	// Atlas
 	public static final String ATLAS_1 = "atlas/AtlasFF.atlas";
 	
 	public static final AssetManager manager = new AssetManager();
	
	public JuegoAssetManager() {
		cargarMusica();
		cargarSonidos();
		cargarImagenes();
		cargarAtlas();
		
		manager.finishLoading();

//        manager.get(Recursos.MUSICAMENU, Music.class).setLooping(true);
//        manager.get(ConstantesJuego.MUSICA_JUEGO, Music.class).setLooping(true);
	}

	private void cargarSonidos() {
//		manager.load("sonidos/coin.wav", Sound.class);
//		manager.load("sonidos/bump.wav", Sound.class);
//		manager.load("sonidos/break.wav", Sound.class);
	}

	private void cargarMusica() {
		manager.load(MUSICA_1, Music.class);
		manager.load(MUSICA_2, Music.class);
		manager.load(MUSICA_3, Music.class);
		manager.load(MUSICA_4, Music.class);
		manager.load(MUSICA_5, Music.class);
		manager.load(MUSICA_JUEGO, Music.class);
	}
	
	private void cargarImagenes() {
		manager.load(LOGO, Texture.class);
		manager.load(FONDO_MENU, Texture.class);
		manager.load(TITULO_MENU, Texture.class);
		manager.load(TITULO_OPCIONES, Texture.class);
	}
	
	private void cargarAtlas() {
		manager.load(ATLAS_1, TextureAtlas.class);
	}
	
	public AssetManager getManager() {
		return manager;
	}
    
}