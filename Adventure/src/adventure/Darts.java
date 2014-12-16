package adventure;

import javalib.worldimages.*;
import java.util.Random;

public class Darts {

    Posn[] dartsArray;
    int tickCount;
    final int WIDTH = 500;
    final int HEIGHT = 400;

    Random randomizer = new Random();

    Darts() {
        this.dartsArray = new Posn[3];
        
        for (Posn d : this.dartsArray) {
            d = new Posn(randomizer.nextInt(WIDTH), randomizer.nextInt(HEIGHT));
        }
    }
    
    private Darts(Posn[] d) {
        this.dartsArray = d;
    }

    Darts tick() {
        tickCount++;
        int position = tickCount % 3;
        Posn[] newD = new Posn[this.dartsArray.length];
        
        for (int i = 0; i < newD.length; i++) {
            newD[i] = this.dartsArray[i];
        }
        
        if (tickCount % 17 == 0) {
            newD[position] = new Posn(randomizer.nextInt(WIDTH), 
                    randomizer.nextInt(HEIGHT));
        }
        
        return new Darts(newD);
    }
    
    WorldImage makeImage() {
        Posn dart1 = this.dartsArray[0];
        Posn dart2 = this.dartsArray[1];
        Posn dart3 = this.dartsArray[2];
        String img = "dart.png";
        return new OverlayImages(((WorldImage) new FromFileImage(dart1, img)),
                new OverlayImages(((WorldImage) new FromFileImage(dart2, img)),
                ((WorldImage) new FromFileImage(dart3, img))));
    }

}
