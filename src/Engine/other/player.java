package Engine.other;
import Engine.scenes.savePoint;
import Engine.scenes.scene;

import java.util.Scanner;

/**
 * The player object. This has the logic for the scenes and story. The player also stores important story data that cant be stored in the scene.
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class player {
	private String name;
	private scene lastScene = null;
	private scene currScene;
	private scene saveScene = null;
	private scene startScene = null;
	private Scanner userIn;
	
	public player (String name) {
		this(name, null);
	}
	
	public player (String name, scene scene) {
		this.name = name;
		this.currScene = scene;
		this.startScene = scene;
		userIn = new Scanner(System.in);
	}

	/**
	 * Sets what scene we are on
	 * @param scene - The scene we are on
	 */
	public void setScene(scene scene) {
		this.lastScene = this.currScene;
		this.currScene = scene;
	}

	/**
	 * Gets the saved scene
	 * @return - The saved scene
	 */
	public scene getSaveScene() {
		return this.saveScene;
	}

	/**
	 * Gets the last scene
	 * @return - The last scene
	 */
	public scene getLastScene() {
		return this.lastScene;
	}

	/**
	 * Gets the start scene
	 * @return - The start scene
	 */
	public scene getStartScene() {
		return this.startScene;
	}

	/**
	 * The main loop of the engine. This prints the scene and options and wait for the user to choose an option and moves to the appropriate scene
	 */
	public void play() {
		if (this.currScene != null) {
			this.currScene.compileScene(this);
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
						if (this.lastScene instanceof savePoint) {
							if (this.lastScene.getOption(currIn-1).getText().equals("Save the game")) {
								this.saveScene = this.currScene;
							}
						}
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
