package adventure.dartgameworld;
import adventure.collectworld.CollectWorld;
import javalib.funworld.World;
import javalib.worldimages.*;

public class DartGameWorld extends World {
    
    Balloons balloons;
    Pointer pointer;
    CollectWorld otherWorld;
    int dartCount;
    final int WIDTH = 600;
    final int HEIGHT = 450;
    
    private DartGameWorld(Balloons b, Pointer p, CollectWorld w, int c) {
        this.balloons = b;
        this.pointer = p;
        this.otherWorld = w;
        this.dartCount = c;
    }
    
    public DartGameWorld(CollectWorld w) {
        this.otherWorld = w;
        this.balloons = new Balloons();
        this.pointer = new Pointer();
        this.dartCount = w.dartCount;
    }
    
    public WorldImage makeImage() {
        return new OverlayImages(this.balloons.makeImage(), 
                this.pointer.makeImage());
    }
    
    public World onTick() {
        return new DartGameWorld(this.balloons.tick(), this.pointer,
                this.otherWorld, this.dartCount);
    }
    
    public World onKeyEvent(String ke) {
        if (ke.equals("s")) {
            return this.shootAttempt();
        } else {
            return new DartGameWorld(this.balloons, this.pointer.onKey(ke),
                    this.otherWorld, this.dartCount);
        }
    }
    
    World shootAttempt() {
        return this; // IMPLEMENT
    }
}