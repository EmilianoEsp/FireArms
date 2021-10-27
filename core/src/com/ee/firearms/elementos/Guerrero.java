package com.ee.firearms.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class Guerrero extends Personaje {
	
	private Texture texturasAnim[] = new Texture[2];
	private int vel = 10;
	
	public Guerrero(){
		super("Guerrero", new Texture(Recursos.GUERRERO) , 100, 100);
	}
	
	public void cargarTexturas() {
		texturasAnim[0] = new Texture(Recursos.GUERRERO_CAMINAR_DERECHA);
		texturasAnim[1] = new Texture(Recursos.GUERRERO_CAMINAR_IZQUIERDA);
		
	}
	
	public void moverDerecha() {
		float posX = this.getPosX() + vel;
		Animacion animDerecha = new Animacion(this, texturasAnim[0], 8);
		animDerecha.render(Render.sb, (int) posX, (int) this.getPosY());
		this.setPosX(posX);
	}

	public void moverIzquierda() {
		float posX = this.getPosX() - vel;
		Animacion animIzquierda = new Animacion(this, texturasAnim[1], 8);
		animIzquierda.render(Render.sb, (int) posX, (int) this.getPosY());
		this.setPosX(posX);
	}
}