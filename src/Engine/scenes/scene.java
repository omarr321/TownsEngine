package Engine.scenes;
import Engine.other.option;
import Engine.other.player;
import Engine.other.toolbox;

/**
 * This is the basic scene class. This has text and options in no special formatting. The root for all scene objects and has a lot of the basic methods.
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class scene {

	private String text;
	private option[] options = new option[10];
	private int optionIndex = 0;
	
	public scene() {}
	
	public scene(String text) {
		this.text = text;
	}

	/**
	 * Sets the text of the scene
	 * @param text - The text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the scene text
	 * @return - The scene text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Adds an option to the scene and increments the index by 1
	 * @param option - The option to add
	 */
	public void addOption(option option) {
		if (this.optionIndex == 10) {
			return;
		}
		options[this.optionIndex] = option;
		this.optionIndex++;
	}

	/**
	 * Check if the scene has any options.
	 * @return - True if it has no options, false if otherwise
	 */
	public boolean noOptions() {
		return this.optionIndex == 0;
	}

	/**
	 * Gets the option at the index provided
	 * @param index - The index of the option
	 * @return - The Option found
	 */
	public option getOption(int index) {
		return this.options[index];
	}

	/**
	 * Gets the total number of  options
	 * @return - The total count of the options
	 */
	public int getNumOfOptions() {
		
		return this.optionIndex;
	}

	/**
	 * Wipes all options from the scene and resets the index to zero
	 */
	protected void deleteAllOptions() {
		this.options = new option[10];
		this.optionIndex = 0;
	}

	/**
	 * converts the scene to a nicely formatted string
	 * @return - The scene in string form
	 */
	@Override
	public String toString() {
		String temp = toolbox.breakStringUp(this.text);
		if (this.noOptions()) {
			temp = temp + "\nNo Options!";
		} else {
			for (int i = 0; i < optionIndex; i++) {
				temp = temp + "\n" + (i+1) + " | " + this.options[i].toString();
			}
		}
		
		return temp;
	}

	/**
	 * This compiles the scene so that it displays and links properly. This happens when the scene runs.
	 * @param player
	 */
	public void compileScene(player player) {}
}
