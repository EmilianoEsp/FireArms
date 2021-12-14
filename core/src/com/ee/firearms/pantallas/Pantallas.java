package com.ee.firearms.pantallas;

import com.ee.firearms.FireArms;

public enum Pantallas {

	MENU_PRINCIPAL {
        public PantallaAbstracta crearPantalla(FireArms juegoStateManager, Object... params) {
            return new PantallaMenu(juegoStateManager);
        }
    },
	UN_JUGADOR {
    	public PantallaAbstracta crearPantalla(FireArms juegoStateManager, Object... params) {
    		return new PantallaUnJugador(juegoStateManager);
    	}
    },
	OPCIONES {
    	public PantallaAbstracta crearPantalla(FireArms juegoStateManager, Object... params) {
    		return new PantallaOpciones(juegoStateManager);
    	}
    },
	MULTIJUGADOR {
    	public PantallaAbstracta crearPantalla(FireArms juegoStateManager, Object... params) {
    		return new PantallaMultijugador(juegoStateManager);
    	}
    };
 
    public abstract PantallaAbstracta crearPantalla(FireArms juegoStateManager, Object... params);
	
}
