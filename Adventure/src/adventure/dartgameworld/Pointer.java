package adventure.dartgameworld;
import javalib.worldimages.*;

public class Pointer {
    public Posn loc;
    final int WIDTH = 600;
    final int HEIGHT = 450;
    
    Pointer() {
        this.loc = new Posn(WIDTH/2, HEIGHT/2);
    }
    
    private Pointer(Posn p) {
        this.loc = p;
    }
    
    Pointer onKey(String ke) {
        if (ke.equals("up")) {
            return new Pointer(new Posn(this.loc.x, this.loc.y - 2));
        } else if (ke.equals("down")) {
            return new Pointer(new Posn(this.loc.x, this.loc.y + 2));
        } else if (ke.equals("left")) {
            return new Pointer(new Posn(this.loc.x - 2, this.loc.y));
        } else if (ke.equals("right")) {
            return new Pointer(new Posn(this.loc.x + 2, this.loc.y));
        } else {
            return this;
        }
    }
    
    WorldImage makeImage() {
        return new FromFileImage(this.loc, "pointer.png");
    }
}