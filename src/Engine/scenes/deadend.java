package Engine.scenes;

import Engine.other.option;
import Engine.other.player;

/**
 * This class is a child of scene. This is designed for the big block of text with no options. See the scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class deadend extends scene{

    public deadend() {
        this(null);
    }

    public deadend(String text) {
        super(text);
    }

    /**
     * This compiles the scene so that it displays and links properly. This happens when the scene runs
     * @param player - The player
     */
    @Override
    public void compileScene(player player) {
        this.deleteAllOptions();

        option deadend1 = new option("Go back to last scene");
        deadend1.setScene(player.getLastScene());
        this.addOption(deadend1);

        if (player.getSaveScene() != null) {
            option deadend2 = new option("Go back to last save");
            deadend2.setScene(player.getSaveScene());
            this.addOption(deadend2);
        }

        option deadend3 = new option("Go back to the start");
        deadend3.setScene(player.getStartScene());
        this.addOption(deadend3);
    }
}
