package com.frontanilla.fluppy;

import com.badlogic.gdx.Game;
import com.frontanilla.Screens.SplashScreen;
import com.frontanilla.ZBHelpers.AssetLoader;

public class FluppyGame extends Game {
    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
