package Engine.scenes;

import Engine.other.Player;
import Engine.other.Option;

/**
 * This class is a child of Scene. This is designed for the big block of text with no options. See the Scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class SavePoint<T extends Scene> extends Scene {
	private Scene nextScene;
	private String oldText = null;

	public SavePoint() {
		this(null, null);
	}

	public SavePoint(String text) {
		this(text, null);
	}
	public SavePoint(String text, T scene) {
		super(text);
		this.nextScene = scene;
	}

	/**
	 * Set the next Scene after this Scene
	 * @param scene - The next Scene
	 * @param <T - Any class that extends scene
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
		// Removes all options and saves the original text so if you come back to this, it does not dup options or text
		if (this.oldText == null) {
			this.oldText = this.getText();
		}
		this.deleteAllOptions();

		Option saveO = new Option("Save the game");
		saveO.setScene(nextScene);
		Option noSaveO = new Option("Continue without saving");
		noSaveO.setScene(nextScene);
		this.addOption(saveO);
		this.addOption(noSaveO);
		this.setText(this.oldText + "\n Would you like to Save?");
	}
}
