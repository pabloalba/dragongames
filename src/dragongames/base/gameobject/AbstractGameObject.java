package dragongames.base.gameobject;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
    protected TextureRegion reg;

    private static int lastId = 0;

    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    public Vector2 velocity = new Vector2();
    public Vector2 terminalVelocity = new Vector2();
    public Vector2 friction = new Vector2();
    public Vector2 acceleration = new Vector2();
    public Rectangle bounds;
    public boolean flip = false;
    public boolean visible = true;
    private int id;
    public boolean debug;


    public AbstractGameObject() {
        id = ++lastId;
        position = new Vector2();
        dimension = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;

        velocity = new Vector2();
        friction = new Vector2();
        acceleration = new Vector2();
        bounds = new Rectangle();

    }

    public int getId() {
        return id;
    }


    protected void updateMotionX(float deltaTime) {
        if (velocity.x != 0) {
            // Apply friction
            if (velocity.x > 0) {
                velocity.x =
                        Math.max(velocity.x - friction.x * deltaTime, 0);
            } else {
                velocity.x =
                        Math.min(velocity.x + friction.x * deltaTime, 0);
            }
        }
        // Apply acceleration
        velocity.x += acceleration.x * deltaTime;
        // Make sure the object's velocity does not exceed the
        // positive or negative terminal velocity
        velocity.x = MathUtils.clamp(velocity.x,
                -terminalVelocity.x, terminalVelocity.x);
    }


    protected void updateMotionY(float deltaTime) {
        if (velocity.y != 0) {
            // Apply friction
            if (velocity.y > 0) {
                velocity.y =
                        Math.max(velocity.y - friction.y * deltaTime, 0);
            } else {
                velocity.y =
                        Math.min(velocity.y + friction.y * deltaTime, 0);
            }
        }

        // Apply acceleration
        velocity.y += acceleration.y * deltaTime;

        // Make sure the object's velocity does not exceed the
        // positive or negative terminal velocity
        velocity.y = MathUtils.clamp(velocity.y,
                -terminalVelocity.y, terminalVelocity.y);
    }


    public void update(float deltaTime) {
        updateMotionX(deltaTime);
        updateMotionY(deltaTime);
        // Move to new position
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;

    }

    public void render(SpriteBatch batch) {
        if (visible) {
            bounds.setCenter(position.x + dimension.x / 2, position.y + dimension.y / 2);

            if (debug) {
                debugBounds(batch);
            }


            batch.draw(reg.getTexture(),
                    position.x, position.y,
                    origin.x, origin.y,
                    dimension.x, dimension.y,
                    scale.x, scale.y,
                    rotation,
                    reg.getRegionX(), reg.getRegionY(),
                    reg.getRegionWidth(), reg.getRegionHeight(),
                    flip, false);
        }

    }


    private void debugBounds(SpriteBatch batch) {
        Pixmap pixmap = new Pixmap(Math.round(bounds.width), Math.round(bounds.height), Pixmap.Format.RGBA8888);
        // Fill square with red color at 50% opacity
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        Sprite spr = new Sprite(texture);
        spr.setPosition(bounds.x, bounds.y);
        spr.draw(batch);
    }

    public void init(TextureRegion textureRegion) {
        reg = textureRegion;
        dimension.set(reg.getRegionWidth(), reg.getRegionHeight());

        // Center image on game object
        origin.set(dimension.x / 2, dimension.y / 2);

        bounds.set(0, 0, dimension.x, dimension.y);
    }

    public boolean equals(AbstractGameObject gameObject) {
        return (gameObject != null) && (gameObject.id == this.id);
    }

}
