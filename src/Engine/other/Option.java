package Engine.other;
import Engine.scenes.Scene;

/**
 * An Option that can be in a Scene.
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Option<T extends Scene> {
	private String optionText;
	private Scene nextScene;

	public Option() {
		this(null, null);
	}
	public Option(String text) {
		this(text, null);
	}
	public Option(String text, T scene) {
		this.optionText = text;
		this.nextScene = scene;
	}

	/**
	 * Set the Option text
	 * @param text - The text for the Option
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
	 * Sets the Scene that this Option will go to
	 * @param scene - The Scene that this Option will go to
	 * @param <T> - Any class that extends Scene
	 */
	public void setScene(T scene) {
		this.nextScene = scene;
	}

	/**
	 * Gets the Scene this Option leads to
	 * @return - The Scene that this options goes to
	 */
	public Scene getScene() {
		return this.nextScene;
	}

	/**
	 * Converts this Option to text
	 * @return - This Option converted to text
	 */
	@Override
	public String toString() {
		String temp = this.optionText;
		if (this.nextScene == null) {
			temp = temp + "*";
		}
		return temp;
	}
}
