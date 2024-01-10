package Engine.scenes;

import Engine.other.Option;
import Engine.other.Player;

/**
 * This class is a child of Scene. This is designed for the title screen. See the Scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class TitleScene<T extends Scene> extends Scene {
	private Scene nextScene;
	private String credit;
	private String title;

	private String oldText = null;

	public TitleScene() {
		this(null, null, null, null);
	}
	
	public TitleScene(String title) {
		this(title, null, null, null);
	}
	
	public TitleScene(String title, String text) {
		this(title, text, null, null);
	}
	
	public TitleScene(String title, String text, String credit) {
		this(title, text, credit, null);
	}
	
	public TitleScene(String title, String text, String credit, T startScene) {
		this.title = title;
		this.credit = credit;
		this.setText(text);
		this.nextScene = startScene;
	}

	/**
	 * Sets the title
	 * @param title - The title of the story
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCredit(String credit) {this.credit = credit; }
	/**
	 * This adds the Scene that will play after this Scene.
	 * @param startScene - The Scene that starts the story off
	 * @param player - The Player
	 */
	public void addStartScene(T startScene, Player player) {
		this.nextScene = startScene;
	}

	/**
	 * This compiles the Scene so that it displays and links properly. This happens when the Scene runs
	 * @param player - The Player
	 */
	@Override
	public void compileScene(Player player) {
		if (this.oldText == null) {
			this.oldText = this.getText();
		}
		this.deleteAllOptions();

		super.setText(this.title + "\n\n" + this.oldText);
		
		Option back = new Option("Back", this);
		
		Scene creditScene = new Scene("Story made by " + this.credit);
		creditScene.addOption(back);
		Scene helpScene = new Scene("TownsEngine is simple you use. Use the numbers on your keyboard to select options in the story.\nType \"quit\" anytime to quit the story");
		helpScene.addOption(back);
		
		Option play = new Option("Play", this.nextScene);
		Option credit = new Option("Credits", creditScene);
		Option help = new Option("Help", helpScene);
		
		this.addOption(play);
		this.addOption(credit);
		this.addOption(help);
	}
}