package Engine;
import java.util.List;
import Engine.option;

public class scene {

	private String text;
	private option[] options = new option[10];
	private int optionIndex = 0;
	
	public scene(String text) {
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void addOption(option option) {
		if (this.optionIndex == 10) {
			return;
		}
		options[this.optionIndex] = option;
		this.optionIndex = this.optionIndex++;
	}
	
	public boolean noOptions() {
		return this.optionIndex == 0;
	}
	
	public option getOption(int index) {
		return this.options[index];
	}
	
	@Override
	public String toString() {
		String temp = this.text;
		if (this.noOptions()) {
			temp = temp + "\nPress enter to continue...";
		} else {
			for (int i = 0; i < optionIndex; i++) {
				temp = temp + "\n" + i + " | " + this.options[i].toString();
			}
		}
		
		return temp;
	}
}
