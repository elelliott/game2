package adventure;

import javalib.worldimages.Posn;
import java.util.Random;

public class Enemy {
    // (x, y)
    Posn loc;
    // (dx, dy)
    Posn dir;
    final int WIDTH = 500;
    final int HEIGHT = 400;
    Random randomizer = new Random();

    Enemy() {
        int x = randomizer.nextInt(WIDTH);
        int y = randomizer.nextInt(HEIGHT);
        
        // determines magnitude of movement; min 3, max 15
        int magnitude = randomizer.nextInt(13) + 3;
        // determines initial direction
        boolean flip = randomizer.nextBoolean();
        boolean flip2 = randomizer.nextBoolean();
        
        int dx, dy;
        if (flip) {
            dx = magnitude;
        } else {
            dx = magnitude * -1;
        }
        
        if (flip2) {
            dy = magnitude;
        } else {
            dy = magnitude * -1;
        }

        this.loc = new Posn(x, y);
        this.dir = new Posn(dx, dy);
    }

    protected Enemy(Posn loc, Posn dir) {
        this.loc = loc;
        this.dir = dir;
    }

    Enemy tick() {
        int newX = this.loc.x + this.dir.x;
        int newY = this.loc.y + this.dir.y;
        
        if (newX >= WIDTH) {
            newX = WIDTH;
        } else if (newX <= 0) {
            newX = 0;
        } else if (newY >= HEIGHT) {
            newY = HEIGHT;
        } else if (newY <= 0) {
            newY = 0;
        }

        int randOf4 = randomizer.nextInt(4);

        switch (randOf4) {
            case 0: // dx switches
                return new Enemy(new Posn(newX, newY),
                        new Posn(this.dir.x * -1, this.dir.y));
            case 1: // dy switches
                return new Enemy(new Posn(newX, newY),
                        new Posn(this.dir.x, this.dir.y * -1));
            case 2: // both switch
                return new Enemy(new Posn(newX, newY),
                        new Posn(this.dir.x * -1, this.dir.y * -1));
            default: // keep same direction
                return new Enemy(new Posn(newX, newY), this.dir);
        }

    }
}
