package dragongames.base.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import dragongames.base.asset.AudioManager;
import dragongames.base.gameobject.AbstractGameObject;

public abstract class Controller {

    public Array<AbstractGameObject> gameObjects;
    protected Game game;

    private float width;
    private float height;

    public Controller(Game game, float width, float height) {
        this.game = game;
        this.width = width;
        this.height = height;
        gameObjects = new Array<AbstractGameObject>();
        init();
    }

    protected void addGameObject(AbstractGameObject gameObject) {
        gameObjects.add(gameObject);
    }

    protected float posMiddleX(AbstractGameObject object) {
        return (width - object.dimension.x) / 2;
    }

    protected float posMiddleY(AbstractGameObject object) {
        return (height - object.dimension.y) / 2;
    }

    protected float posThirdY(AbstractGameObject object) {
        return (height - object.dimension.y) / 3;
    }


    protected abstract void init();

    public abstract void touch(float x, float y);

    public abstract void update(float deltaTime);


    protected Array<AbstractGameObject> touchedGameObjects(float x, float y) {
        Array<AbstractGameObject> list = new Array<AbstractGameObject>();
        for (AbstractGameObject gameObject : gameObjects) {
            Rectangle rect = new Rectangle(gameObject.position.x, gameObject.position.y, gameObject.dimension.x, gameObject.dimension.y);
            if (rect.contains(x, y)) {
                list.add(gameObject);
            }
        }
        return list;
    }


    protected boolean gameObjectInList(AbstractGameObject gameObject, Array<AbstractGameObject> gameObjectsList) {
        for (AbstractGameObject go : gameObjectsList) {
            if (go.equals(gameObject)) {
                return true;
            }
        }
        return false;
    }


    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public void playMusic(String fileFullName, float volume) {
        playMusic(Gdx.audio.newMusic(Gdx.files.internal(fileFullName)), volume);
    }

    public void playMusic(Music music, float volume) {
        AudioManager.instance.play(music, volume);
    }

    public void stopMusic() {
        AudioManager.instance.stopMusic();
    }

    public void playSound(String fileFullName, float volume) {
        playSound(Gdx.audio.newSound(Gdx.files.internal(fileFullName)), volume);
    }

    public void playSound(Sound sound, float volume) {
        AudioManager.instance.play(sound, volume);
    }


    public void touchDown(int x, int y, int pointer, int button) {
    }

    public void touchUp(int x, int y, int pointer, int button) {
    }

    public void touchDragged(int x, int y, int pointer) {
    }
}
