package com.frontanilla.ZBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BirdTexture {
    public Animation animation;
    public TextureRegion up;
    public TextureRegion mid;
    public TextureRegion down;

    public BirdTexture (Animation animation,
                        TextureRegion up, TextureRegion mid, TextureRegion down) {
        this.animation = animation;
        this.up = up;
        this.mid = mid;
        this.down = down;
    }
}
