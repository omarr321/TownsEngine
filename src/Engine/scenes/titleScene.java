package Engine.scenes;

import Engine.other.option;
import Engine.other.player;

/**
 * This class is a child of scene. This is designed for the title screen. See the scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class titleScene extends scene {
	private scene nextScene;
	private String credit;
	private String title;

	private String oldText = null;

	public titleScene() {
		this(null, null, null, null);
	}
	
	public titleScene(String title) {
		this(title, null, null, null);
	}
	
	public titleScene(String title, String text) {
		this(title, text, null, null);
	}
	
	public titleScene(String title, String text, String credit) {
		this(title, text, credit, null);
	}
	
	public <T extends scene> titleScene(String title, String text, String credit, T startScene) {
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

	/**
	 * This adds the scene that will play after this scene.
	 * @param startScene - The scene that starts the story off
	 * @param player - The player
	 * @param <T> - Any class that extends scene
	 */
	public <T extends scene> void addStartScene(T startScene, player player) {
		this.nextScene = startScene;
	}

	/**
	 * This compiles the scene so that it displays and links properly. This happens when the scene runs
	 * @param player - The player
	 */
	@Override
	public void compileScene(player player) {
		if (this.oldText == null) {
			this.oldText = this.getText();
		}
		this.deleteAllOptions();

		super.setText(this.title + "\n\n" + this.oldText);
		
		option back = new option("Back", this);
		
		scene creditScene = new scene("Story made by " + this.credit);
		creditScene.addOption(back);
		scene helpScene = new scene("TownsEngine is simple you use. Use the numbers on your keyboard to select options in the story.\nType \"quit\" anytime to quit the story");
		helpScene.addOption(back);
		
		option play = new option("Play", this.nextScene);
		option credit = new option("Credits", creditScene);
		option help = new option("Help", helpScene);
		
		this.addOption(play);
		this.addOption(credit);
		this.addOption(help);
	}
}