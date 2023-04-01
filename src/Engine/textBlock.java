package Engine;

public class textBlock extends scene{
	private scene nextScene;
	
	public textBlock() {}
	
	public textBlock(String text) {
		super(text);
	}
	
	public <T extends scene> textBlock(String text, T scene) {
		super(text);
		this.nextScene = scene;
	}
	
	public <T extends scene> void setNextScene(T scene) {
		this.nextScene = scene;
	}
	
	@Override
	public void compileScene() {
		
		option nextScene = new option("Press 1 to continue", this.nextScene);
		
		this.addOption(nextScene);
	}
}
