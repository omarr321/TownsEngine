package Engine;
import Engine.scene;
import java.util.InputMismatchException;
import java.util.Scanner;

public class player {
	private String name;
	private scene currScene = null;
	private Scanner userIn;
	
	public player (String name) {
		this(name, null);
	}
	
	public player (String name, scene scene) {
		this.name = name;
		this.currScene = scene;
		userIn = new Scanner(System.in);
	}
	
	public void setScene(scene scene) {
		this.currScene = scene;
	}
	
	public void play() {
		if (this.currScene != null) {
			toolbox.printChar(this.currScene.toString(), 10);
			int optionNum = this.currScene.getNumOfOptions();
			
			while(true) {
				System.out.print("\n>>>");
				String currInString = this.userIn.nextLine();
				if (currInString.equals("quit") || currInString.equals("Quit")) {
					return;
				}
				try {
					int currIn = Integer.parseInt(currInString);
					if (currIn < 1 || currIn > optionNum) {
						System.out.print("Error: Input is a vaild option!");
					} else {
						this.setScene(this.currScene.getOption(currIn-1).getScene());
						break;
					}
				} catch (NumberFormatException ex) {
					System.out.print("Error: Input is not a number!");
				}
			}
			this.play();
		}
	}
}
