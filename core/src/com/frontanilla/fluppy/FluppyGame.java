package com.frontanilla.fluppy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.frontanilla.Screens.SplashScreen;
import com.frontanilla.ZBHelpers.AssetLoader;
import com.frontanilla.tanjentxm.Player;

public class FluppyGame extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
        AssetLoader.player.play(
                AssetLoader.modules[AssetLoader.currentModuleIndex],
                true,
                true,
                0.25f,
                0.25f
        );
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

    @Override
    public void pause() {
        super.pause();
        AssetLoader.player.pause();
    }

    @Override
    public void resume() {
        super.resume();
        AssetLoader.player.resume();
    }
}
