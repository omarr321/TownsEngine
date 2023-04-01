package Engine;

public class titleScene extends scene{
	private scene nextScene;
	private String credit;
	
	public titleScene(String title, String text, String credit) {
		this(title, text, credit, null);
	}
	
	public <T extends scene> titleScene(String title, String text, String credit, T startScene) {
		super(title + "\n\n" + text);
		this.credit = credit;
		this.nextScene = startScene;
	}
	
	public <T extends scene> void addStartScene(T startScene) {
		this.nextScene = startScene;
	}
	
	public void compileScene() {
		option back = new option("Back", this);
		
		scene creditScene = new scene("Story made by " + this.credit);
		creditScene.addOption(back);
		scene helpScene = new scene("TownsEngine is simple you use. Use the numbers on your keyboard to select options in the story.");
		helpScene.addOption(back);
		
		option play = new option("Play", this.nextScene);
		option credit = new option("Credits", creditScene);
		option help = new option("Help", helpScene);
		
		this.addOption(play);
		this.addOption(credit);
		this.addOption(help);
	}
}