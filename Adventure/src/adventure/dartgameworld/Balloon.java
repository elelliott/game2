package adventure.dartgameworld;
import javalib.worldimages.*;
import java.util.Random;

public class Balloon {
    Posn loc;
    Posn dir;
    final int WIDTH = 600;
    final int HEIGHT = 450;
    
    Random randomizer = new Random();
    
    private Balloon(Posn p, Posn d) {
        this.loc = p;
        this.dir = d;
    }
    
    Balloon() {
        int initX = randomizer.nextInt(WIDTH);
        this.loc = new Posn(initX, HEIGHT);
        
        int magnitude = randomizer.nextInt(10);
        
        int flip = randomizer.nextInt(2);
        int dx = 0;
        switch (flip) {
            case 0: dx = -magnitude;
                break;
            case 1: dx = magnitude;
                break;
        }
        // dy ranges between -15 and -5
        int dy = (randomizer.nextInt(11) + 5) * -1;
        
        this.dir = new Posn(dx, dy);
    }
    
    Balloon tick() {
        int newX = this.loc.x + this.dir.x;
        int newY = this.loc.y + this.dir.y;
        
        return new Balloon(new Posn(newX, newY), 
                new Posn((this.dir.x * -1), this.dir.y));
    }
    
    WorldImage makeImage() {
        return new FromFileImage(this.loc, "aballoon.png");
    }
}