package com.ee.firearms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinManager {
	
	public static Skin skin;

	public SkinManager() {
		skin = new Skin();
		FileHandle fileHandle = Gdx.files.internal("skin/star-soldier-ui.json");
		FileHandle atlasFile = fileHandle.sibling("star-soldier-ui.atlas");
		if (atlasFile.exists()) {
			skin.addRegions(new TextureAtlas(atlasFile));
		}
		skin.load(fileHandle);
	}

	public static void dispose() {
		skin.dispose();
	}
}
