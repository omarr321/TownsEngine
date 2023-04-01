package Engine;

public class titleScene extends scene{
	private scene nextScene;
	private String credit;
	private String title;
	private String text;
	
	public titleScene() {}
	
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
		this.text = text;
		this.credit = credit;
		this.nextScene = startScene;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	public <T extends scene> void addStartScene(T startScene) {
		this.nextScene = startScene;
	}
	
	@Override
	public void compileScene() {
		super.setText(this.title + "\n\n" + this.text);
		
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