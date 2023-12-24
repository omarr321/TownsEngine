package Engine;

import Complier.Errors.TestError;
import Engine.other.Option;
import Engine.other.Player;
import Engine.scenes.*;

public class test {

	public static void main(String[] args) throws TestError {
		TitleScene startScene = new TitleScene("Test Story", "A story to test this engine", "Omar R.");
		
		Scene scene1 = new Scene("This is the start of a test story!");
		Option scene1_1 = new Option("Let's Go!!!!!");
		Option scene1_2 = new Option("No, this is the worst!");
		scene1.addOption(scene1_1);
		scene1.addOption(scene1_2);
		
		Scene scene2_1 = new Scene("Fuck ya! Let's party!");
		scene1_1.setScene(scene2_1);
		Option scene2_1_1 = new Option("OK!");
		scene2_1.addOption(scene2_1_1);

		Scene scene2_2 = new Scene("FUCK OFF YOU CUNT!");
		scene1_2.setScene(scene2_2);
		Option scene2_2O1 = new Option("Do nothing");
		scene2_2.addOption(scene2_2O1);
		SavePoint scene2_save = new SavePoint("You take the insult like a champ!");
		scene2_2O1.setScene(scene2_save);

		Scene scene_end_thing_1 = new Deadend("Time to go dumbass...");
		scene2_save.setNextScene(scene_end_thing_1);

		Scene scene3_1 = new Scene("According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway because bees don't care what humans think is impossible.");
		scene2_1_1.setScene(scene3_1);
		
		TextBlock textBlock1 = new TextBlock("This is a text block. There is only one Option.", scene1);
		
		Option scene3_2 = new Option("This next Scene is a textblock", textBlock1);
		scene3_1.addOption(scene3_2);
		
		Player user = new Player("Billy", startScene);
		
		startScene.addStartScene(scene1, user);
		user.play();
	}
}
