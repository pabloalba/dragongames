package dragongames.base.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class SkinsKeeper implements Disposable {
    public Skin skin;

    protected void init(String jsonSkin, String atlasSkin){
        skin = new Skin(
                Gdx.files.internal(jsonSkin),
                new TextureAtlas(atlasSkin));
    }


    @Override
    public void dispose() {
        skin.dispose();
    }

}
