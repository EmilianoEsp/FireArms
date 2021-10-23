package com.ee.firearms.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ee.firearms.utiles.Recursos;

public class Guerrero extends Personaje {
		public int x,y;
		private Animation<TextureRegion> animation;
		private float tiempo;
		private TextureRegion [] regionsMovimientos;
		private Texture imagen;
		
		private TextureRegion frameActual;
		
		public Guerrero(int x, int y){
			super("Guerrero", new Imagen("personajes/Pj_Guerrero.png"), 100, 100);
			
			this.x = x;
			this.y = y;
			
			// Cargar la imagen
			imagen = new Texture(Recursos.GUERRERO_ATAQUE);
			TextureRegion [][] tmp = TextureRegion.split(imagen,imagen.getWidth()/4,imagen.getHeight()); // dividir por la cantidad de texturas, en este ejemplo son 8
			
			regionsMovimientos = new TextureRegion[4]; // el numero dependera de la cantidad anteriormente mencionada
			for(int i = 0; i < 4; i++){
				regionsMovimientos[i] = tmp[0][i];
			}
			
			// Crear la animacion
			animation = new Animation<TextureRegion>(1/10f,regionsMovimientos); // Cambiar el tiempo entre frames
			tiempo = 0f;
		}
		
		public void render(final SpriteBatch batch){
			tiempo += Gdx.graphics.getDeltaTime(); // indica el tiempo que paso desde el ultimo render
			frameActual = animation.getKeyFrame(tiempo,true);
			batch.draw(frameActual,x,y);
		}
}