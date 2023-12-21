package Engine;

public class savePoint extends scene{
	private scene nextScene;

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

	public void compileScene() {
		option saveO = new option("Save the game");
		saveO.setScene(nextScene);
		option noSaveO = new option("Continue without saving");
		saveO.setScene(nextScene);
		this.addOption(saveO);
		this.addOption(noSaveO);
	}
}
