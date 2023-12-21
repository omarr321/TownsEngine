package Engine.scenes;

import Engine.other.option;
import Engine.other.player;

/**
 * This class is a child of scene. This is designed for the big block of text with no options. See the scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class textBlock extends scene {
	private scene nextScene;
	
	public textBlock() {}
	
	public textBlock(String text) {
		super(text);
	}
	
	public <T extends scene> textBlock(String text, T scene) {
		super(text);
		this.setNextScene(scene);
	}

	/**
	 * Set the next scene of this scene
	 * @param scene - The next scene
	 * @param <T> - Any class that extends scene
	 */
	public <T extends scene> void setNextScene(T scene) {
		this.nextScene = scene;
	}

	/**
	 * This compiles the scene so that it displays and links properly. This happens when the scene runs
	 * @param player - The player
	 */
	@Override
	public void compileScene(player player) {
		this.deleteAllOptions();
		option nextScene = new option("Press 1 to continue", this.nextScene);
		
		this.addOption(nextScene);
	}
}
