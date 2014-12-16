package adventure.collectworld;

import adventure.dartgameworld.DartGameWorld;
import javalib.funworld.World;
import javalib.worldcanvas.*;
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
        return new CollectWorld(this.player.tick(), this.darts.tick(),
                this.enemies.tick(), this.otherWorld, this.dartCount);
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

//    public WorldEnd worldEnds() {
//        WorldImage finalImage;
//        
//        if (gameOver) {
//            return new WorldEnd(true, finalImage);
//        } else {
//            return new WorldEnd(false, finalImage);
//        }
//    }
}
