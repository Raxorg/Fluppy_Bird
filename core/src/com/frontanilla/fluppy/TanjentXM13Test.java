package com.frontanilla.fluppy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.frontanilla.tanjentxm.Player;
import com.frontanilla.tanjentxm.XMModule;

public class TanjentXM13Test extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
    ShapeRenderer shapeRenderer;

	Player p;

	int[] modules = new int[4];
    int currentModuleIndex = 0;

    int[] keyCodes = new int[] {
            Input.Keys.NUM_1,
            Input.Keys.NUM_2,
            Input.Keys.NUM_3,
            Input.Keys.NUM_4,
            Input.Keys.J,
            Input.Keys.M,
            Input.Keys.N,
            Input.Keys.F,
            Input.Keys.S,
            Input.Keys.R,
            Input.Keys.T
    };

    Color[] colors = new Color[] {
            Color.BLACK,
            Color.CYAN,
            Color.DARK_GRAY,
            Color.MAGENTA,
            Color.NAVY,
            Color.PINK,
            Color.TEAL,
            Color.YELLOW,
            Color.DARK_GRAY,
            Color.GREEN,
            Color.RED
    };
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("data/badlogic.jpg"));

		this.p = new Player(44100, Player.INTERPOLATION_MODE_LINEAR);
		this.modules[0] = p.loadXM(Gdx.files.internal("chip18.xm").readBytes(), 0);
		this.modules[1] = p.loadXM(Gdx.files.internal("chip19.xm").readBytes(), 0);
		this.modules[2] = p.loadXM(Gdx.files.internal("chip20.xm").readBytes(), 0);
		this.modules[3] = p.loadXM(Gdx.files.internal("chip17.xm").readBytes(), 0);
        this.currentModuleIndex = 0;
		p.play(modules[this.currentModuleIndex], true, true, 0.25f, 0.25f);

        Gdx.input.setInputProcessor(this);

        this.shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth() / (float)this.keyCodes.length;
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < this.keyCodes.length; i++) {
            this.shapeRenderer.setColor(colors[i].r, colors[i].g, colors[i].b, 0.5f);
            this.shapeRenderer.rect(i * width, 0, width, height);
        }
        this.shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		p.dispose();
	}

	@Override
	public void pause() {
		// Uncomment this to pause the player when window out of focus
        // p.pause();
	}

	@Override
	public void resume() {
		p.resume();
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == keyCodes[0]) {
            this.currentModuleIndex = 0;
            this.p.play(modules[this.currentModuleIndex], true, true, 0.25f, 0.25f);
            return true;
        }
        if (keycode == keyCodes[1]) {
            this.currentModuleIndex = 1;
            this.p.play(modules[this.currentModuleIndex], true, true, 0.25f, 0.25f);
            return true;
        }
        if (keycode == keyCodes[2]) {
            this.currentModuleIndex = 2;
            this.p.play(modules[this.currentModuleIndex], true, true, 0.25f, 0.25f);
            return true;
        }
        if (keycode == keyCodes[3]) {
            this.currentModuleIndex = 3;
            this.p.play(modules[this.currentModuleIndex], true, true, 0.25f, 0.25f);
            return true;
        }

        if (keycode == keyCodes[4]) {
            // jump to random order and row
            this.p.jump(modules[this.currentModuleIndex], (int) (Math.random() * 16), 16 * (int) (Math.random() * 4), Player.JUMP_STYLE_KEY_OFF_NOTES);
            return true;
        }

        if (keycode == keyCodes[5]) {
            // play a min7 chord
            int  root = (int)(0x30 + Math.random() * 24);
            p.queueNoteData(this.modules[this.currentModuleIndex], 0, root + 0x00, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 1, root + 0x03, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 2, root + 0x07, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 3, root + 0x0A, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 4, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 5, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 6, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 7, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            return true;
        }

        if (keycode == keyCodes[6]) {
            // play a maj7 chord
            int  root = (int)(0x30 + Math.random() * 24);
            p.queueNoteData(this.modules[this.currentModuleIndex], 0, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 1, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 2, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 3, XMModule.NOTE_KEYOFF, -1, -1, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 4, root + 0x00, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 5, root + 0x04, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 6, root + 0x07, 1, 0x30, -1, -1);
            p.queueNoteData(this.modules[this.currentModuleIndex], 7, root + 0x0B, 1, 0x30, -1, -1);
            return true;
        }

        if (keycode == keyCodes[7]) {
            // speed up
            this.p.queueNoteData(this.modules[this.currentModuleIndex], -1, -1, -1, -1, 0x0F, 0x03);
            return true;
        }
        if (keycode == keyCodes[8]) {
            // speed down
            this.p.queueNoteData(this.modules[this.currentModuleIndex], -1, -1, -1, -1, 0x0F, 0x06);
            return true;
        }

        if (keycode == keyCodes[9]) {
            // random chaos
            for (int i = 0; i < 16; i++) {
                this.p.queueNoteData(this.modules[this.currentModuleIndex], i, (int) Math.random() * 96, 1, (int) Math.random() * 255, (int) Math.random() * 255, (int) Math.random() * 255);
            }
            return true;
        }
        if (keycode == keyCodes[10]) {
            // silence/tranquility
            for (int i = 0; i < 16; i++) {
                this.p.queueNoteData(this.modules[this.currentModuleIndex], i, XMModule.NOTE_KEYOFF, 0, -1, 0xC, 0x00);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return this.keyUp(this.keyCodes[(int)(screenX / (float)Gdx.graphics.getWidth() * this.keyCodes.length)]);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
