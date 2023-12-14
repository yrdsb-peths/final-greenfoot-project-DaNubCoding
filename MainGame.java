import greenfoot.*;
import java.util.*;

public class MainGame extends Scene {
    public Player player;
    public Camera camera;
    
    public MainGame() {
        super();
        
        Log.start();
        
        Log.debug("Creating sprack group from \"block.png\"...");
        SprackGroup blocks = new SprackGroup("block.png", 16);
        Log.debug("Creating sprack group from \"player.png\"...");
        SprackGroup players = new SprackGroup("player.png", 32);
        
        Log.debug("Spawning spracks...");
        for (int x = 0; x < 12; x++) {
            for (int z = 0; z < 12; z++) {
                new Block(this, blocks, x, 0, z);
            }
        }
        new Block(this, blocks, 3, -1, 4);
        new Block(this, blocks, 4, -1, 4);
        new Block(this, blocks, 5, -1, 4);
        new Block(this, blocks, 4, -1, 3);
        new Block(this, blocks, 4, -1, 5);
        new Block(this, blocks, 4, -2, 4);
        
        this.player = new Player(this, players);
        this.camera = new Camera(this);
        
        Log.debug("Complete");
    }
    
    private class zSortComparator implements Comparator<Sprack> {
        public int compare(Sprack a, Sprack b) {
            if (a.pos.y.get() == b.pos.y.get()) {
                if (a.getY() < b.getY()) {
                    return -1;
                } else if (a.getY() > b.getY()) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (a.pos.y.get() < b.pos.y.get()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    
    public void act() {
        Collections.sort(Sprack.spracks, new zSortComparator());
        for (int i = 0; i < Sprack.spracks.size(); i++) {
            Sprack.spracks.get(i).remove();
            Sprack.spracks.get(i).add();
        }
    }
}
