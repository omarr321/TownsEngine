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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void play() {
		while(true) {
			if (this.currScene != null) {
				Scene temp = this.playScene(this.currScene);
				this.setScene(temp);
			} else {
				break;
			}
		}
	}

	/**
	 * The main loop of the engine. This prints the Scene and options and wait for the user to choose an Option and moves to the appropriate Scene
	 */
	public Scene playScene(Scene scene) {
			scene.compileScene(this);
			Toolbox.printChar(scene.toString(), 10);
			int optionNum = scene.getNumOfOptions();

			while(true) {
				System.out.print("\n>>>");
				String currInString = this.userIn.nextLine();
				if (currInString.toLowerCase().equals("quit")) {
					return null;
				}
				try {
					int currIn = Integer.parseInt(currInString);
					if (currIn < 1 || currIn > optionNum) {
						System.out.print("Error: Input is not a valid Option!");
					} else {
						if (this.currScene instanceof SavePoint) {
							if (this.currScene.getOption(currIn-1).getText().equals("Save the game")) {
								this.saveScene = scene.getOption(currIn-1).getScene();
							}
						}
						return scene.getOption(currIn-1).getScene();
					}
				} catch (NumberFormatException ex) {
					System.out.print("Error: Input is not a number!");
				}
			}
	}
}
