package com.ee.firearms.pantallas;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ee.firearms.utiles.Config;

public abstract class Menu {
	protected Stage stage;
	
	public Menu() {
		stage = new Stage(new FitViewport(Config.ANCHO, Config.ALTO));
	}
	
}
