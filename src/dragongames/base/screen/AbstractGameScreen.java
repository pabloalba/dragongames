package dragongames.base.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import dragongames.base.asset.AssetsKeeper;
import dragongames.base.controller.Controller;
import dragongames.base.listener.InputListener;
import dragongames.base.renderer.Renderer;

public abstract class AbstractGameScreen implements Screen {
    protected Game game;
    protected AssetsKeeper assets;
    protected Controller controller;
    protected Renderer renderer;
    private boolean paused;

    public AbstractGameScreen (Game game, AssetsKeeper assets) {

        this.game = game;
        this.assets = assets;
    }

    public AbstractGameScreen (Game game) {
        this.game = game;
    }

    public void render (float deltaTime) {

        // Do not update controller when paused.
        if (!paused) {
            //Handle user input
            InputListener.instance.handleInput();

            // Update game world by the time that has passed
            // since last rendered frame.
            if (controller != null) {
                controller.update(deltaTime);
            }
        }

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }


    @Override
    public void hide () {
        renderer.dispose();
    }
    @Override
    public void pause () {
        paused = true;
    }
    @Override
    public void resume () {
        // Only called on Android!
        paused = false;
    }


    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    public void show(Controller controller, Renderer renderer) {
        InputListener.instance.setController(controller);
        InputListener.instance.setRenderer(renderer);
        Gdx.input.setInputProcessor(InputListener.instance);
    }

    public void dispose () {
        if (assets != null) {
            assets.dispose();
        }
    }

    public abstract void show();
}
