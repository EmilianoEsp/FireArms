package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ee.firearms.JuegoStateManager;
import com.ee.firearms.utiles.Constantes;

public abstract class PantallaAbstracta extends Stage implements Screen {
    
    protected JuegoStateManager jsm;
    protected boolean pausado;
    
    protected PantallaAbstracta(JuegoStateManager jsm) {
        super(new FitViewport(Constantes.V_WIDTH, Constantes.V_HEIGHT , new OrthographicCamera()), jsm.getBatch());
        this.jsm = jsm;
        Gdx.input.setInputProcessor(this);
    }
    
    @Override 
    public void show() {
    	
    }
 
    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        pausado = true;
    }

    @Override
    public void resume() {
        pausado = false;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
    
    @Override 
    public void hide() {
    	
    }

}