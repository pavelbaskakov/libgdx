package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.CheeseGame;
import com.mygdx.game.CheesePlease2;
import com.mygdx.game.CheesePlease3;
import com.mygdx.game.CheesePlease4;
import com.mygdx.game.CheesePlease5;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CheeseGame(), config);
	}
}
