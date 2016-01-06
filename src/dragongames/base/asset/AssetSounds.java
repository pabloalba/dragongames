package dragongames.base.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AssetSounds {
    public final Map<String, Sound> sound = new HashMap<String, Sound>();

    public AssetSounds(AssetManager assetManager, Map<String, String> soundFiles) {
        for (Map.Entry<String, String> entry : soundFiles.entrySet()) {
            Sound asset = assetManager.get(entry.getValue(), Sound.class);
            sound.put(entry.getKey(), asset);
        }
    }
}
