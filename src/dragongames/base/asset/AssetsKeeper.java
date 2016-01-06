package dragongames.base.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.Map;

public abstract class AssetsKeeper implements Disposable, AssetErrorListener {
    public static final String TAG = AssetsKeeper.class.getName();
    private AssetManager assetManager;
    public Map<String, Sound> sounds;
    public Map<String, Music> music;
    TextureAtlas atlas;

    protected void init(AssetManager assetManager, String textureFile, Map<String, String> soundFiles,
                        Map<String, String> musicFiles) {
        // load music
        loadMusics(assetManager, musicFiles);
        // load sounds
        loadSounds(assetManager, soundFiles);
        //load textures
        init(assetManager, textureFile);
        // get music
        music = new AssetMusic(assetManager, musicFiles).music;
        // get sounds
        sounds = new AssetSounds(assetManager, soundFiles).sound;


    }

    protected void init(AssetManager assetManager, String textureFile) {
        this.assetManager = assetManager;

        // set asset manager error handler
        assetManager.setErrorListener(this);

        // load texture atlas
        loadTextureAtlas(textureFile);

        // start loading assets and wait until finished
        assetManager.finishLoading();

        atlas = assetManager.get(textureFile);
        // enable texture filtering for pixel smoothing
        for (Texture t : atlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

    }

    private void loadTextureAtlas(String fileName) {
        this.assetManager.load(fileName, TextureAtlas.class);
    }

    private void loadSounds(AssetManager assetManager, Map<String, String> soundFiles) {
        for (String soundFile : soundFiles.values())
            assetManager.load(soundFile, Sound.class);
    }

    private void loadMusics(AssetManager assetManager, Map<String, String> musicFiles) {
        for (String musicFile : musicFiles.values())
            assetManager.load(musicFile, Music.class);
    }

    public TextureRegion getTextureRegion(String name) {
        return atlas.findRegion(name);
    }


    @Override
    public void dispose() {
        assetManager.dispose();
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '"
            + asset.fileName + "'", (Exception) throwable);
    }
}
