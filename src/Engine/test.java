package Engine;
import Engine.scene;
import Engine.option;
import Engine.player;

public class test {

	public static void main(String[] args) {
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
		
		scene scene3_1 = new scene("According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway because bees don't care what humans think is impossible.");
		scene2_1_1.setScene(scene3_1);
		
		player user = new player("Billy", scene1);
		user.play();
	}

}
