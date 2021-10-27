package com.ee.firearms.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.ee.firearms.utiles.Recursos;

public class Guerrero extends Personaje {
	
	public Guerrero(){
		super("Guerrero", new Texture(Recursos.GUERRERO_IDLE), 100, 100);
	}
		
//		public Texture getTextura() {
//			return textura;
//		}

}