package com.ee.firearms.pantallas;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
/*Rectangle*/
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;


public class PantallaPrueba implements Screen {

	private Rectangle bucket;

	/*Imagenes*/
	private Texture dropImage;
	private Texture bucketImage;
	/*Sonidos*/
	private Sound dropSound;
	private Music rainMusic;
	/*Camara y SpriteBatch*/
	private OrthographicCamera camera;
	private SpriteBatch batch;
	/*Raindrops*/
	private Array<Rectangle> raindrops;
	private long lastDropTime;

	@Override
	public void show(){
		// Camara
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		
		// SpriteBatch
		batch = new SpriteBatch();
		
		// Cargar las texturas
		dropImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		
		// Cargar los sonidos
		dropSound = Gdx.audio.newSound(Gdx.files.internal("./sonidos/dropSound.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("./musica/rainMusic.mp3"));
		
		// Reproducir la música
		rainMusic.setLooping(true);
		rainMusic.play();
		
		// Rectangle
		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
		
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render(float delta){
		ScreenUtils.clear(0,0,0.2f,1);
		
		// Buena practica
		camera.update();
		
		// Renderizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		
		for(Rectangle raindrop: raindrops){
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		
		batch.end();
		
		// Teclas
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}
		
		if(bucket.x < 0){
			bucket.x = 0;
		}
		if(bucket.x > 800 - 64){
			bucket.x = 800 - 64;
		}
		
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000){
			spawnRaindrop();
		}
		
		for(Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ){
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0){
				iter.remove();
			}
			if(raindrop.overlaps(bucket)){
				dropSound.play();
				iter.remove();
			}
		}
	}

	private void spawnRaindrop(){
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void dispose(){
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}
}