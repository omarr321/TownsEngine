package Engine;

public class savePoint extends scene{
	private player player;
	
	public savePoint(player player) {
		this(player, null);
	}
	
	public savePoint(player player, String text) {
		super(text);
		this.player = player;
	}
	
	@Override
	public void compileScene() {
		
	}
}
