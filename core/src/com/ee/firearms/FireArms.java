package com.ee.firearms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.pantallas.PantallaAbstracta;
import com.ee.firearms.pantallas.Pantallas;
import com.ee.firearms.utiles.ReproductorMusica;

public class FireArms extends Game implements JuegoStateManager {
	
	private SpriteBatch batch;
    public JuegoAssetManager assets;
    public static ReproductorMusica repMusica;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
        this.assets = new JuegoAssetManager();
        repMusica = new ReproductorMusica();
        
//        ItemDataManager.getInstance().load(JuegoAssetManager.ITEM_JSON);
//        EquipoDataManager.getInstance().load(JuegoAssetManager.EQUIPO_JSON);
//        PersonajeDataManager.getInstance().load(JuegoAssetManager.PERSONAJE_JSON);

        new SkinManager();
        
        if(assets.isFinished()) {
        	mostrarPantalla(Pantallas.MENU_PRINCIPAL);
        }
	}
	
	@Override
    public void mostrarPantalla(Pantallas p) {
        Screen pantallaActual = getScreen();
 
        PantallaAbstracta crearPantalla = p.crearPantalla(this);
        setScreen(crearPantalla);
 
        if (pantallaActual != null) {
        	pantallaActual.dispose();
        }
    }

	@Override
    public void limpiarPantalla() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
	
	@Override
	public AssetManager getAssets() {
		return assets;
	}
	
	@Override
	public SpriteBatch getBatch() {
		return batch;
	}
	
	@Override
    public void render () {
        super.render();
        screen.render(Gdx.graphics.getDeltaTime());
		repMusica.update();
        assets.update();
    }
    
    @Override
    public void dispose () {
        assets.dispose();
        batch.dispose();
    }
    
}