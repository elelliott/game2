package adventure.dartgameworld;

import adventure.collectworld.CollectWorld;
import javalib.funworld.World;
import javalib.worldimages.*;
import javalib.colors.Black;

public class DartGameWorld extends World {

    Balloons balloons;
    Pointer pointer;
    CollectWorld otherWorld;
    int dartCount;
    int initDarts;
    int numPopped;
    final int WIDTH = 600;
    final int HEIGHT = 450;

    private DartGameWorld(Balloons b, Pointer p, CollectWorld w, int c, int d, int n) {
        this.balloons = b;
        this.pointer = p;
        this.otherWorld = w;
        this.dartCount = c;
        this.initDarts = d;
        this.numPopped = n;
    }

    public DartGameWorld(CollectWorld w) {
        this.otherWorld = w;
        this.balloons = new Balloons();
        this.pointer = new Pointer();
        this.dartCount = w.dartCount;
        this.initDarts = w.dartCount;
        this.numPopped = 0;
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.balloons.makeImage(),
                ((WorldImage) new OverlayImages(this.pointer.makeImage(),
                        new TextImage(new Posn(WIDTH / 2 - 100, HEIGHT - 25),
                                "Score: " + getScore(), new Black()))));
    }

    public World onTick() {
        return new DartGameWorld(this.balloons.tick(), this.pointer,
                this.otherWorld, this.dartCount, this.initDarts, this.numPopped);
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("s")) {
            return this.shootAttempt();
        } else {
            return new DartGameWorld(this.balloons, this.pointer.onKey(ke),
                    this.otherWorld, this.dartCount, this.initDarts, this.numPopped);
        }
    }

    World shootAttempt() {
        int ind = whichPopped();
        if (this.dartCount - 1 == 0) { // out of darts
            return new CollectWorld();
        } else if (ind != -1) { // hit
            return new DartGameWorld(this.balloons.pop(ind), this.pointer,
                    this.otherWorld, this.dartCount - 1, this.initDarts, this.numPopped + 1);
        } else { // miss
            return new DartGameWorld(this.balloons, this.pointer,
                    this.otherWorld, this.dartCount - 1, this.initDarts, this.numPopped);
        }
    }

    int whichPopped() { // returns index of popped balloon, -1 if none
        int px = this.pointer.loc.x;
        int py = this.pointer.loc.y;

        for (int i = 0; i < this.balloons.ballArray.length; i++) {
            Balloon b = this.balloons.ballArray[i];
            // if within 48px x-wise to the balloon pinhole, and w/in 
            // 65px y-wise to the pinhole: this means the pinhole of the 
            // pointer must be on the balloon
            if (b.loc.x - 48 <= px && b.loc.x + 48 >= px
                    && b.loc.y - 65 <= py && b.loc.y + 65 >= py) {
                return i;
            }
        }
        return -1;
    }

    double getScore() {
        return Math.round((this.numPopped / 1.0 / this.initDarts) * 100);
    }
}
