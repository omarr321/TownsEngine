package Engine.scenes;

import Engine.other.Player;
import Engine.other.Option;

/**
 * This class is a child of Scene. This is designed for the big block of text with no options. See the Scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class TextBlock extends Scene {
	private Scene nextScene;
	
	public TextBlock() {}
	
	public TextBlock(String text) {
		super(text);
	}
	
	public <T extends Scene> TextBlock(String text, T scene) {
		super(text);
		this.setNextScene(scene);
	}

	/**
	 * Set the next Scene of this Scene
	 * @param scene - The next Scene
	 * @param <T> - Any class that extends Scene
	 */
	public <T extends Scene> void setNextScene(T scene) {
		this.nextScene = scene;
	}

	/**
	 * This compiles the Scene so that it displays and links properly. This happens when the Scene runs
	 * @param player - The Player
	 */
	@Override
	public void compileScene(Player player) {
		this.deleteAllOptions();
		Option nextScene = new Option("Press 1 to continue", this.nextScene);
		
		this.addOption(nextScene);
	}
}
