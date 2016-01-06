package dragongames.base.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;


public abstract class AbstractGameStageScreen implements Screen {
    protected Stage stage;
    protected Skin skin;
    protected Game game;
    protected boolean paused;
    protected float width;
    protected float height;

    public AbstractGameStageScreen (Game game,float width, float height) {
        this.game = game;
        this.width = width;
        this.height = height;
    }


    @Override
    public void render (float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }


    @Override
    public void show () {
        stage = new Stage(new FitViewport(width,
                height));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        if (stage!=null) {
            stage.dispose();
        }
    }

    @Override
    public void dispose () {
        if (stage!=null) {
            stage.dispose();
        }
        if (skin != null) {
            skin.dispose();
        }
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


}
