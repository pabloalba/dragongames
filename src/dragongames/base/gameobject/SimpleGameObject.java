package dragongames.base.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleGameObject extends AbstractGameObject{
    public SimpleGameObject(TextureRegion textureRegion) {
        super.init(textureRegion);
    }

    public SimpleGameObject(String fileFullName) {
        super.init(new TextureRegion(new Texture(Gdx.files.internal(fileFullName))));
    }
}
