package dragongames.base.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

import dragongames.base.controller.Controller;
import dragongames.base.renderer.Renderer;

public class InputListener extends InputAdapter {
    private Controller controller;
    private Renderer renderer;


    Vector3 touchPos = new Vector3();
    Long lastClickTime = 0L;

    public static InputListener instance = new InputListener();

    // singleton
    private InputListener() {
    }


    private void _touchButton() {
        Long time = System.currentTimeMillis();
        if (time - lastClickTime > 200) {
            lastClickTime = time;

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = renderer.getViewport().unproject(touchPos);
            controller.touch(touchPos.x, touchPos.y);

        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            _touchButton();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer < 5) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = renderer.getViewport().unproject(touchPos);
            controller.touchDown(Math.round(touchPos.x), Math.round(touchPos.y), pointer, button);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer < 5) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = renderer.getViewport().unproject(touchPos);
            controller.touchUp(Math.round(touchPos.x), Math.round(touchPos.y), pointer, button);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer < 5) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = renderer.getViewport().unproject(touchPos);
            controller.touchDragged(Math.round(touchPos.x), Math.round(touchPos.y), pointer);
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {

        return controller.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {

        return controller.keyTyped(character);
    }
}
