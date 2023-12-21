package Engine.scenes;

import Engine.other.option;
import Engine.other.player;

/**
 * This class is a child of scene. This is designed for the big block of text with no options. See the scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class savePoint extends scene{
	private scene nextScene;
	private String oldText = null;

	public savePoint() {
		this(null, null);
	}

	public savePoint(String text) {
		this(text, null);
	}
	public <T extends scene> savePoint(String text, T scene) {
		super(text);
		this.nextScene = scene;
	}

	/**
	 * Set the next scene after this scene
	 * @param scene - The next scene
	 * @param <T - Any class that extends scene
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
		// Removes all options and saves the original text so if you come back to this, it does not dup options or text
		if (this.oldText == null) {
			this.oldText = this.getText();
		}
		this.deleteAllOptions();

		option saveO = new option("Save the game");
		saveO.setScene(nextScene);
		option noSaveO = new option("Continue without saving");
		noSaveO.setScene(nextScene);
		this.addOption(saveO);
		this.addOption(noSaveO);
		this.setText(this.oldText + "\n Would you like to Save?");
	}
}
