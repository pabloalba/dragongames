package dragongames.base.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;
import java.util.Map;

public class AssetMusic {
    public final Map<String, Music> music = new HashMap<String, Music>();

    public AssetMusic(AssetManager assetManager, Map<String, String> musicFiles) {
        for (Map.Entry<String, String> entry : musicFiles.entrySet()) {
            Music asset = assetManager.get(entry.getValue(), Music.class);
            music.put(entry.getKey(), asset);
        }
    }
}
