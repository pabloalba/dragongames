package dragongames.base.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dragongames.base.controller.Controller;
import dragongames.base.gameobject.AbstractGameObject;

public class Renderer implements Disposable {

    protected OrthographicCamera camera;
    protected SpriteBatch batch;

    protected Controller controller;

    protected Viewport viewport;

    private float width;
    private float height;



    public Renderer(Controller controller, float width, float height) {
        this.controller = controller;
        this.width = width;
        this.height = height;
        init();
    }

    protected void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(width, height, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        batch = new SpriteBatch();
    }

    public void render() {

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (AbstractGameObject gameObject: controller.gameObjects){
            gameObject.render(batch);
        }

        batch.end();
    }


    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }


    public Viewport getViewport() {
        return viewport;
    }
}
