package Engine.scenes;
import Engine.other.Option;
import Engine.other.Player;
import Engine.other.Toolbox;

/**
 * This is the basic Scene class. This has text and options in no special formatting. The root for all Scene objects and has a lot of the basic methods.
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Scene {

	private String text;
	private Option[] options = new Option[10];
	private int optionIndex = 0;
	
	public Scene() {}
	
	public Scene(String text) {
		this.text = text;
	}

	/**
	 * Sets the text of the Scene
	 * @param text - The text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the Scene text
	 * @return - The Scene text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Adds an Option to the Scene and increments the index by 1
	 * @param option - The Option to add
	 */
	public void addOption(Option option) {
		if (this.optionIndex == 10) {
			return;
		}
		options[this.optionIndex] = option;
		this.optionIndex++;
	}

	/**
	 * Check if the Scene has any options.
	 * @return - True if it has no options, false if otherwise
	 */
	public boolean noOptions() {
		return this.optionIndex == 0;
	}

	/**
	 * Gets the Option at the index provided
	 * @param index - The index of the Option
	 * @return - The Option found
	 */
	public Option getOption(int index) {
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
	 * Wipes all options from the Scene and resets the index to zero
	 */
	protected void deleteAllOptions() {
		this.options = new Option[10];
		this.optionIndex = 0;
	}

	/**
	 * converts the Scene to a nicely formatted string
	 * @return - The Scene in string form
	 */
	@Override
	public String toString() {
		String temp = Toolbox.breakStringUp(this.text);
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
	 * This compiles the Scene so that it displays and links properly. This happens when the Scene runs.
	 * @param player
	 */
	public void compileScene(Player player) {}
}
