package com.ee.firearms;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.pantallas.Pantallas;

public interface JuegoStateManager {

	public void mostrarPantalla(Pantallas p);
	public void limpiarPantalla();
	
	public SpriteBatch getBatch();
	public AssetManager getAssets();
	
}