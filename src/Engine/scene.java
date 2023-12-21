package Engine;
import java.util.List;
import Engine.option;

public class scene {

	private String text;
	private option[] options = new option[10];
	private int optionIndex = 0;
	
	public scene() {}
	
	public scene(String text) {
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public void addOption(option option) {
		if (this.optionIndex == 10) {
			return;
		}
		options[this.optionIndex] = option;
		this.optionIndex++;
	}
	
	public boolean noOptions() {
		return this.optionIndex == 0;
	}
	
	public option getOption(int index) {
		return this.options[index];
	}
	
	public int getNumOfOptions() {
		
		return this.optionIndex;
	}

	protected void deleteAllOptions() {
		this.options = new option[10];
		this.optionIndex = 0;
	}
	
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
	
	public void compileScene(player player) {}
}
