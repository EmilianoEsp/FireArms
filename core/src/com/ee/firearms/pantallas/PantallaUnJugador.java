package com.ee.firearms.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ee.firearms.FireArms;
import com.ee.firearms.JuegoAssetManager;
import com.ee.firearms.JuegoStateManager;
import com.ee.firearms.box2d.B2WorldCreator;
import com.ee.firearms.box2d.WorldContactListener;
import com.ee.firearms.elementos.Jugador;
import com.ee.firearms.mapas.PrimerMapa;
import com.ee.firearms.utiles.Constantes;

public class PantallaUnJugador extends PantallaAbstracta {

	private TextureAtlas atlas;
    private PrimerMapa mapa;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
//    private Hud hud;

    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Jugador jugador;

    private Music music;
    
    public Game game;
    public FireArms core;
	
	protected PantallaUnJugador(JuegoStateManager jsm) {
		super(jsm);
		
		atlas = new TextureAtlas(JuegoAssetManager.ATLAS_1);

        
        //create cam used to follow mario through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(Constantes.V_WIDTH / Constantes.PPM, Constantes.V_HEIGHT / Constantes.PPM, gamecam);

//        hud = new Hud(p.getVida());

        mapa = new PrimerMapa();

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, Constantes.GRAVEDAD), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this, mapa);
	
        jugador = new Jugador(this);

        world.setContactListener(new WorldContactListener());

        music = JuegoAssetManager.manager.get(JuegoAssetManager.MUSICA_JUEGO);
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();
	}

	@Override
	public void render(float delta) {
		update(delta);

		jsm.limpiarPantalla();

        mapa.render();

//        b2dr.render(world, gamecam.combined); // TODO Quitar

        jsm.getBatch().setProjectionMatrix(gamecam.combined);
        jsm.getBatch().begin();
        jugador.draw(jsm.getBatch());
        jsm.getBatch().end();

        //Set our batch to now draw what the Hud camera sees.
//        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
//        hud.stage.draw();
	}
	
    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        jugador.update(dt);

//        hud.update(p.getVida());

        gamecam.position.x = jugador.b2body.getPosition().x;
        gamecam.update();
        mapa.update(gamecam);
    }
    
    public void handleInput(float dt){
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                jugador.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && jugador.b2body.getLinearVelocity().x <= 2)
                jugador.b2body.applyLinearImpulse(new Vector2(0.1f, 0), jugador.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && jugador.b2body.getLinearVelocity().x >= -2)
                jugador.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), jugador.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
//                jugador.fire();
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            	jugador.attack();
            }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    public World getWorld(){
        return world;
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

    @Override
    public void dispose() {
        mapa.getMap().dispose();
        mapa.getRenderer().dispose();
        world.dispose();
        b2dr.dispose();
//        hud.dispose();
    }
    
    public TextureAtlas getAtlas(){
        return atlas;
    }

}
