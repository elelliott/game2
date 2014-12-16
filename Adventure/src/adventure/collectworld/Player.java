package adventure.collectworld;
import javalib.worldimages.*;

public class Player {
    // (x, y)
    Posn loc;
    // (dx, dy)
    Posn dir;
    final int WIDTH = 600;
    final int HEIGHT = 450;
    
    Player(Posn p) {
        this.loc = p;
        this.dir = new Posn(0, 0);
    }
    
    protected Player(Posn p, Posn d) {
        this.loc = p;
        this.dir = d;
    }
    
    Player tick() {
        int newX = this.loc.x + this.dir.x;
        int newY = this.loc.y + this.dir.y;
        
        if (newX >= WIDTH) {
            return new Player(new Posn(WIDTH, newY), this.dir);
        } else if (newX <= 0) {
            return new Player(new Posn(0, newY), this.dir);
        } else if (newY >= HEIGHT) {
            return new Player(new Posn(newX, HEIGHT), this.dir);
        } else if (newY <= 0) {
            return new Player(new Posn(newX, 0), this.dir);
        } else {
            return new Player(new Posn(newX, newY), this.dir);
        }
    }
    
    Player onKey(String ke) {
        if (ke.equals("up")) {
            return new Player(this.loc, new Posn(this.dir.x, -1));
        } else if (ke.equals("down")) {
            return new Player(this.loc, new Posn(this.dir.x, 1));
        } else if (ke.equals("left")) {
            return new Player(this.loc, new Posn(-1, this.dir.y));
        } else if (ke.equals("right")) {
            return new Player(this.loc, new Posn(1, this.dir.y));
        } else {
            return this;
        }
    }
    
    WorldImage makeImage() {
        return new FromFileImage(this.loc, "alady.png");
    }
}