package Engine;

import java.sql.Savepoint;

public class test {

	public static void main(String[] args) {
		titleScene startScene = new titleScene("Test Story", "A story to test this engine", "Omar R.");
		
		scene scene1 = new scene("This is the start of a test story!");
		option scene1_1 = new option("Let's Go!!!!!");
		option scene1_2 = new option("No, this is the worst!");
		scene1.addOption(scene1_1);
		scene1.addOption(scene1_2);
		
		scene scene2_1 = new scene("Fuck ya! Let's party!");
		scene1_1.setScene(scene2_1);
		option scene2_1_1 = new option("OK!");
		scene2_1.addOption(scene2_1_1);

		scene scene2_2 = new scene("FUCK OFF YOU CUNT!");
		scene1_2.setScene(scene2_2);
		option scene2_2O1 = new option("Do nothing");
		scene2_2.addOption(scene2_2O1);
		savePoint scene2_save = new savePoint("You take the insult like a champ!");
		scene2_2O1.setScene(scene2_save);

		scene scene_end_thing_1 = new scene("Time to go dumbass...");
		scene2_save.setNextScene(scene_end_thing_1);

		scene scene3_1 = new scene("According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway because bees don't care what humans think is impossible.");
		scene2_1_1.setScene(scene3_1);
		
		textBlock textBlock1 = new textBlock("This is a text block. There is only one option.", scene1);
		
		option scene3_2 = new option("This next scene is a textblock", textBlock1);
		scene3_1.addOption(scene3_2);
		
		player user = new player("Billy", startScene);
		
		startScene.addStartScene(scene1);
		user.play();
	}
}
