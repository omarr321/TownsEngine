package Engine.scenes;

import Engine.other.Option;
import Engine.other.Player;

/**
 * This class is a child of Scene. This is designed for the big block of text with no options. See the Scene class for what this class is for
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Deadend<T extends Scene> extends Scene {

    public Deadend() {
        this(null);
    }

    public Deadend(String text) {
        super(text);
    }

    /**
     * This compiles the Scene so that it displays and links properly. This happens when the Scene runs
     * @param player - The Player
     */
    @Override
    public void compileScene(Player player) {
        this.deleteAllOptions();

        Option deadend1 = new Option("Go back to the last Scene");
        deadend1.setScene(player.getLastScene());
        this.addOption(deadend1);

        if (player.getSaveScene() != null) {
            Option deadend2 = new Option("Go back to the last save");
            deadend2.setScene(player.getSaveScene());
            this.addOption(deadend2);
        }

        Option deadend3 = new Option("Go back to the start");
        deadend3.setScene(player.getStartScene());
        this.addOption(deadend3);
    }
}
