package Engine.other;
import Engine.scenes.scene;

/**
 * An option that can be in a scene.
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class option {
	private String optionText;
	private scene nextScene;
	
	public option(String text) {
		this(text, null);
	}
	public <T extends scene> option(String text, T scene) {
		this.optionText = text;
		this.nextScene = scene;
	}

	/**
	 * Set the option text
	 * @param text - The text for the option
	 */
	public void setText(String text) {
		this.optionText = text;
	}

	/**
	 * Gets the options text
	 * @return - The options text
	 */
	public String getText() {
		return this.optionText;
	}

	/**
	 * Sets the scene that this option will go to
	 * @param scene - The scene that this option will go to
	 * @param <T> - Any class that extends scene
	 */
	public <T extends scene> void setScene(T scene) {
		this.nextScene = scene;
	}

	/**
	 * Gets the scene this option leads to
	 * @return - The scene that this options goes to
	 */
	public scene getScene() {
		return this.nextScene;
	}

	/**
	 * Converts this option to text
	 * @return - This option converted to text
	 */
	@Override
	public String toString() {
		return this.optionText;
	}
}
