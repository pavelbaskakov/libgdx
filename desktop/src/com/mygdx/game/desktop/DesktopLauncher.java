package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Chapter4.TurtleGame;
import com.mygdx.game.Chapter6.src.fiftytwopickup.FiftyTwoPickupGame;
import com.mygdx.game.Chapter6.src.planedodger.PlaneDodgerGame;
import com.mygdx.game.Chapter6.src.rectangledestroyer.RectangleDestroyerGame;
import com.mygdx.game.Chapter6.src.spacerocks.SpaceRocksGame;
import com.mygdx.game.balloonBuster.BalloonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new FiftyTwoPickupGame(), config);
	}
}
