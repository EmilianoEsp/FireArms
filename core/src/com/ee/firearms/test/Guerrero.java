package com.ee.firearms.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class Guerrero extends Personaje {
	
	public Texture texturasAnim[] = new Texture[2];
	
	private int vel = 10;
	
	public void setTexturasAnim(Texture[] texturasAnim) {
		this.texturasAnim = texturasAnim;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public Guerrero(){
		super("Guerrero", new Texture(Recursos.GUERRERO_DER) , 100, 100);
	}
	
	public void dibujarDer() {
		Render.sb.draw(new Texture(Recursos.GUERRERO_DER), this.getPosX(), this.getPosY());
	}
	
	public void dibujarIzq() {
		Render.sb.draw(new Texture(Recursos.GUERRERO_IZQ), this.getPosX(), this.getPosY());
	}
	
	public Texture[] getTexturasAnim() {
		return texturasAnim;
	}

	public int getVel() {
		return vel;
	}

	public void cargarTexturas() {
		
		
	}
	
	public void moverDerecha(Animacion animDerecha) {
		float posX = this.getPosX() + vel;
		animDerecha.render(Render.sb, (int) posX, (int) this.getPosY());
		this.setPosX(posX);
	}

	public void moverIzquierda(Animacion animIzquierda) {
		float posX = this.getPosX() - vel;
		animIzquierda.render(Render.sb, (int) posX, (int) this.getPosY());
		this.setPosX(posX);
	}
	
	public void saltar() {
		float posY = this.getPosY() + 100; //100 sig piso
		if(this.getPosY()>=200) {
			posY = 200;
		}
		this.setPosY(posY);
	}
}