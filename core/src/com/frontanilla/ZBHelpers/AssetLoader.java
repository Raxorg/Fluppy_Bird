package com.frontanilla.ZBHelpers;

import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.tanjentxm.Player;
import com.frontanilla.ZBHelpers.BirdTexture;

public class AssetLoader {
    public static Vector<BirdTexture> birdTextures;
    public static Texture texture, logoTexture;
    public static TextureRegion logo, zbLogo, bg, grass, bird, birdDown,
            birdUp, skullUp, skullDown, bar, playButtonUp, playButtonDown,
            ready, gameOver, highScore, scoreboard, star, noStar, retry;
    public static Animation birdAnimation;
    public static Sound dead, flap, coin, fall;
    public static BitmapFont font, shadow, whiteFont;
    // MUSIC
    public static int[] modules = new int[2];
    public static int currentModuleIndex = 0;
    public static Player player;
    public static boolean isLooping = false;
    // PREFS
    private static Preferences prefs;

    public static void load() {

        birdTextures = new Vector<BirdTexture>();
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        ready = new TextureRegion(texture, 59, 83, 34, 7);
        ready.flip(false, true);

        retry = new TextureRegion(texture, 59, 110, 33, 7);
        retry.flip(false, true);

        gameOver = new TextureRegion(texture, 59, 92, 46, 7);
        gameOver.flip(false, true);

        scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
        scoreboard.flip(false, true);

        star = new TextureRegion(texture, 152, 70, 10, 10);
        noStar = new TextureRegion(texture, 165, 70, 10, 10);

        star.flip(false, true);
        noStar.flip(false, true);

        highScore = new TextureRegion(texture, 59, 101, 48, 7);
        highScore.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zbLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);

        whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        whiteFont.getData().setScale(.1f, -.1f);

        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZombieBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        // XM MUSIC
        player = new Player(44100, Player.INTERPOLATION_MODE_LINEAR);
        modules[0] = player.loadXM(Gdx.files.internal("data/menu.xm").readBytes(), 0);
        modules[1] = player.loadXM(Gdx.files.internal("data/gameplay.xm").readBytes(), 0);
        currentModuleIndex = 0;

        // custom bird skins
        addBirdTexture("data/texture.png",
                       new int[]{ 136,0, 17,12 },
                       new int[]{ 153,0, 17,12 },
                       new int[]{ 170,0, 17,12});
        setBirdTexture(0);

        addBirdTexture("data/pajaro-stronguista.png",
                       new int[]{ 0,0, 16,11 },
                       new int[]{ 17,0, 16,11 },
                       new int[]{ 34,0, 16,11 });
        //  setBirdTexture(1); // uncomment to play with the stronguist bird XDxDdxXD
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();

        // Dispose sounds
        dead.dispose();
        flap.dispose();
        coin.dispose();

        font.dispose();
        shadow.dispose();
        player.dispose();
    }

    public static void addBirdTexture (String pathToTexture,
                                       int[] upFrame, int[] midFrame,
                                       int[] downFrame) {
        Animation animation;
        TextureRegion[] newTexture = new TextureRegion[3];
        Texture birdTexture = new Texture(Gdx.files.internal(pathToTexture));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); // ¯\_(ツ)_/¯

        newTexture[0] = new TextureRegion(birdTexture, upFrame[0], upFrame[1],
                                              upFrame[2], upFrame[3]);

        newTexture[1] = new TextureRegion(birdTexture, midFrame[0], midFrame[1],
                                              midFrame[2], midFrame[3]);

        newTexture[2] = new TextureRegion(birdTexture, downFrame[0], downFrame[1],
                                              downFrame[2], downFrame[3]);

        for (int i=0; i<3; i++) { newTexture[i].flip(false, true); }

        animation = new Animation(0.06f, newTexture);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        birdTextures.addElement(new BirdTexture(animation, newTexture[0], newTexture[1],
                                                newTexture[2]));
    }

    public static void setBirdTexture (int index) {
        AssetLoader.bird = birdTextures.get(index).mid;
        AssetLoader.birdAnimation = birdTextures.get(index).animation;
    }
}
