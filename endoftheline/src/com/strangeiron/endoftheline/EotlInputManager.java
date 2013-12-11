
package com.strangeiron.endoftheline;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class EotlInputManager implements InputProcessor {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public static final int JUMP = 4;
	public static final int PRIMARY_SHOT = 5;
        public static final int SECONDARY_SHOT = 6;

	public static final int ESCAPE = 6;

	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];
        public int x, y = 0;
        public boolean updated = false;

	public void set (int key, boolean down) {
		// @TODO: добавить загрузку из конфига!!!
		int button = -1;

		if (key == Keys.W) button = JUMP;
		if (key == Keys.A) button = LEFT;
		if (key == Keys.S) button = DOWN;
		if (key == Keys.D) button = RIGHT;
                
                if(key == Input.Buttons.LEFT) button = PRIMARY_SHOT;
                if(key == Input.Buttons.RIGHT) button = SECONDARY_SHOT;
                

		if (key == Keys.ESCAPE || key == Keys.MENU) button = ESCAPE;

		if (button >= 0) {
			buttons[button] = down;
		}
                
                updated = true;
	}

	public void tick () {
		for (int i = 0; i < buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}
                updated = false;
	}

	public void releaseAllKeys () {
		for (int i = 0; i < buttons.length; i++) {
			if (i == UP || i == DOWN) continue;
			buttons[i] = false;
		}
	}

	@Override
	public boolean keyDown (int keycode) {
            set(keycode, true);
            return false;
	}

	@Override
	public boolean keyUp (int keycode) {
            set(keycode, false);
            return false;
	}

	@Override
	public boolean keyTyped (char character) {
            // TODO Auto-generated method stub
            return false;
	}

	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
            set(button, true);
            this.x = x;
            this.y = y;
            
            return false;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
            set(button, false);
            this.x = x;
            this.y = y;
            
            return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}
}
