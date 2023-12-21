package Engine;

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

	public <T extends scene> void setNextScene(T scene) {
		this.nextScene = scene;
	}

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
