package Engine.other;
import Engine.scenes.Scene;
import Engine.scenes.SavePoint;

import java.util.Scanner;

/**
 * The Player object. This has the logic for the scenes and story. The Player also stores important story data that cant be stored in the Scene.
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Player {
	private String name;
	private Scene lastScene = null;
	private Scene currScene;
	private Scene saveScene = null;
	private Scene startScene = null;
	private Scanner userIn;
	
	public Player(String name) {
		this(name, null);
	}
	
	public Player(String name, Scene scene) {
		this.name = name;
		this.currScene = scene;
		this.startScene = scene;
		userIn = new Scanner(System.in);
	}

	/**
	 * Sets what Scene we are on
	 * @param scene - The Scene we are on
	 */
	public void setScene(Scene scene) {
		this.lastScene = this.currScene;
		this.currScene = scene;
	}

	/**
	 * Gets the saved Scene
	 * @return - The saved Scene
	 */
	public Scene getSaveScene() {
		return this.saveScene;
	}

	/**
	 * Gets the last Scene
	 * @return - The last Scene
	 */
	public Scene getLastScene() {
		return this.lastScene;
	}

	/**
	 * Gets the start Scene
	 * @return - The start Scene
	 */
	public Scene getStartScene() {
		return this.startScene;
	}

	public<T extends Scene> void setStartScene(T scene) {
		this.currScene = scene;
		this.startScene = scene;
	}

	/**
	 * The main loop of the engine. This prints the Scene and options and wait for the user to choose an Option and moves to the appropriate Scene
	 */
	public void play() {
		if (this.currScene != null) {
			this.currScene.compileScene(this);
			Toolbox.printChar(this.currScene.toString(), 10);
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
						System.out.print("Error: Input is not a valid Option!");
					} else {
						this.setScene(this.currScene.getOption(currIn-1).getScene());
						if (this.lastScene instanceof SavePoint) {
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
