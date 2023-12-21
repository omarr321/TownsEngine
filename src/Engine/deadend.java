package Engine;

public class deadend extends scene{

    public deadend() {
        this(null);
    }

    public deadend(String text) {
        super(text);
    }

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
