package adventure.collectworld;

import adventure.dartgameworld.DartGameWorld;
import javalib.funworld.World;
import javalib.worldimages.*;

public class CollectWorld extends World {

    Player player;
    Darts darts;
    Enemies enemies;
    DartGameWorld otherWorld;
    public int dartCount;

    final int WIDTH = 600;
    final int HEIGHT = 450;

    private CollectWorld(Player p, Darts d, Enemies e, DartGameWorld w, int c) {
        this.player = p;
        this.darts = d;
        this.enemies = e;
        this.otherWorld = w;
        this.dartCount = c;
    }

    public CollectWorld() {
        this.player = new Player(new Posn(WIDTH / 2, HEIGHT / 2));
        this.darts = new Darts();
        this.enemies = new Enemies();
        this.otherWorld = new DartGameWorld(this);
        this.dartCount = 0;
    }

    public World onTick() {
        Player tp = this.player.tick();
        Darts td = this.darts.tick();
        Enemies te = this.enemies.tick();
        
        int px = tp.loc.x;
        int py = tp.loc.y;
        // this for-loop checks to see if the player picks up any darts
        for (int i = 0; i < td.dartsArray.length; i++) {
            Posn d = td.dartsArray[i];
            if (px - 25 <= d.x && px + 25 >= d.x 
                    && py - 32 <= d.y && py + 32 >= d.y) {
                return new CollectWorld(tp, td.collect(i), te, 
                        this.otherWorld, this.dartCount);
            }
        }
        
        // this for-loop checks to see if any enemy picks up any darts
        Darts tdCollect = td;
        for (int e = 0; e < te.enemyArray.length; e++) {
            int ex = te.enemyArray[e].loc.x;
            int ey = te.enemyArray[e].loc.y;
            
            for (int d = 0; d < td.dartsArray.length; d++) {
                Posn dt = td.dartsArray[d];
                if (ex - 50 <= dt.x && ex + 50 >= dt.x 
                        && ey - 74 <= dt.y && ey + 74 >= dt.y) {
                    tdCollect = tdCollect.collect(d);
                }
            }
        }
        
        return new CollectWorld(tp, tdCollect, te, this.otherWorld, this.dartCount);
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.player.makeImage(),
                ((WorldImage) new OverlayImages(this.enemies.makeImage(), 
                        this.darts.makeImage())));
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("p")) {
            return new DartGameWorld(this);
        } else {
            return new CollectWorld(this.player.onKey(ke), this.darts,
                    this.enemies, this.otherWorld, this.dartCount);
        }
    }

}
