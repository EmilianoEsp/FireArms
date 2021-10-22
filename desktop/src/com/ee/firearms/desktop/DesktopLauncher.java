package com.ee.firearms.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ee.firearms.FireArms;
import com.ee.firearms.utiles.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "FireArms";
		config.width = Config.ANCHO;
		config.height = Config.ALTO;
		config.resizable = false;
		config.addIcon("icon_32x32.png", FileType.Internal);
		new LwjglApplication(new FireArms(), config);
	}
}
