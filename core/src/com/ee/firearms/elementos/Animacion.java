package com.ee.firearms.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animacion {

	private Animation<TextureRegion> animation;
	private float tiempo;
	private TextureRegion [] regionsMovimientos;
	private Texture textura;
	
	private TextureRegion frameActual;
	
	public Animacion(Personaje p, int cantFrames) {
		// Cargar la textura
		textura = p.getTextura();
		TextureRegion [][] tmp = TextureRegion.split(textura,textura.getWidth()/cantFrames,textura.getHeight());
		
		regionsMovimientos = new TextureRegion[cantFrames];
		for(int i = 0; i < cantFrames; i++){
			regionsMovimientos[i] = tmp[0][i];
		}
		
		// Crear la animacion
		animation = new Animation<TextureRegion>(1/10f,regionsMovimientos);
		tiempo = 0f;
	}
	
	public void render(SpriteBatch sb, int x, int y){
		tiempo += Gdx.graphics.getDeltaTime();
		frameActual = animation.getKeyFrame(tiempo,true);
		sb.draw(frameActual,x,y);
	}
}
