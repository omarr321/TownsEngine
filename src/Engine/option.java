package Engine;
import Engine.scene;

public class option {
	private String optionText;
	private scene nextScene;
	
	public option(String text) {
		this(text, null);
	}
	public <T extends scene> option(String text, T scene) {
		this.optionText = text;
		this.nextScene = scene;
	}
	
	public void setText(String text) {
		this.optionText = text;
	}
	public String getText() {
		return this.optionText;
	}
	public <T extends scene> void setScene(T scene) {
		this.nextScene = scene;
	}
	
	public scene getScene() {
		return this.nextScene;
	}
	
	@Override
	public String toString() {
		return this.optionText;
	}
}
