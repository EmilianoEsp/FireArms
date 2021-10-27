package com.ee.firearms.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ee.firearms.utiles.Recursos;

public class Guerrero extends Personaje {
	
		private int x,y;
		private Animation<TextureRegion> animation;
		private float tiempo;
		private TextureRegion [] regionsMovimientos;
		private Texture textura;
		
		private TextureRegion frameActual;
		
		public Guerrero(int x, int y){
			super("Guerrero", new Imagen("personajes/Pj_Guerrero.png"), 100, 100);
			
			this.x = x;
			this.y = y;
			
			// Cargar la imagen
			textura = new Texture(Recursos.GUERRERO_ATAQUE);
			TextureRegion [][] tmp = TextureRegion.split(textura,textura.getWidth()/4,textura.getHeight());
			
			regionsMovimientos = new TextureRegion[4];
			for(int i = 0; i < 4; i++){
				regionsMovimientos[i] = tmp[0][i];
			}
			
			// Crear la animacion
			animation = new Animation<TextureRegion>(1/10f,regionsMovimientos);
			tiempo = 0f;
		}
		
		public Texture getTextura() {
			return textura;
		}

		public void render(final SpriteBatch batch){
			tiempo += Gdx.graphics.getDeltaTime();
			frameActual = animation.getKeyFrame(tiempo,true);
			batch.draw(frameActual,x,y);
		}
}