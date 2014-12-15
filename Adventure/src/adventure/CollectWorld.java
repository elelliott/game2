package adventure;

import javalib.funworld.World;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class CollectWorld extends World {

    Player player;
    Darts darts;
    Enemies enemies;
    World otherWorld;

    final int WIDTH = 500;
    final int HEIGHT = 400;

    private CollectWorld(Player p, Darts d, Enemies e, World otherWorld) {
        this.player = p;
        this.darts = d;
        this.enemies = e;
        this.otherWorld = otherWorld;
    }

    CollectWorld() {
        this.player = new Player(new Posn(WIDTH / 2, HEIGHT / 2));
        this.darts = new Darts();
        this.enemies = new Enemies();
        this.otherWorld = new DartGameWorld();
    }

    public World onTick() {
        return new CollectWorld(this.player.tick(), this.darts.tick(),
                this.enemies.tick(), this.otherWorld);
    }

    public WorldImage makeImage() {
        return new FromFileImage(new Posn(WIDTH/2, HEIGHT/2), "dart.png");
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("p")) {
            return new DartGameWorld();
        } else {
            return new CollectWorld(this.player.onKey(ke), this.darts,
                    this.enemies, this.otherWorld);
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
