package com.ee.firearms.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.ee.firearms.pantallas.PantallaMenu;
import com.ee.firearms.pantallas.PantallaOpciones;
import com.ee.firearms.utiles.Config;

public class Entradas implements InputProcessor {

	private boolean abajo = false, arriba = false;
	private boolean enter = false;
	private int mouseX = 0, mouseY = 0;
	private boolean click = false;
	
	PantallaMenu appMenu;
	
	public Entradas(PantallaMenu appMenu) {
		this.appMenu = appMenu;
	}
	
	PantallaOpciones appOpc;
	
	public Entradas(PantallaOpciones appOpc) {
		this.appOpc = appOpc;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		if (appMenu != null) appMenu.tiempo = 0.08f;
		
		if(keycode == Keys.DOWN) {
			abajo = true;
		} else if(keycode == Keys.UP) {
			arriba = true;
		}
		
		if(keycode == Keys.ENTER) {
			enter = true;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.DOWN) {
			abajo = false;
		}
		
		if(keycode == Keys.UP) {
			arriba = false;
		}
		
		if(keycode == Keys.ENTER) {
			enter = false;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		click = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Config.ALTO - screenY;
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isArriba() {
		return arriba;
	}
	
	public boolean isEnter() {
		return enter;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public boolean isClick() {
		return click;
	}
}