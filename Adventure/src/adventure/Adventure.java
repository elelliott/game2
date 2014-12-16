package adventure;
import adventure.collectworld.CollectWorld;
import javalib.funworld.World;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Adventure {
    
    static final int WIDTH = 600;
    static final int HEIGHT = 450;

    public static void main(String[] args) {
        World collectWorld = new CollectWorld();
        collectWorld.bigBang(WIDTH, HEIGHT, 0.5);
        
    }
    
}

// MODE 1: player moves around, picks up darts that spawn on ground every
//         so many ticks. "enemies" (other people) move around too, in "random"
//         pattern, they pick up darts if they pass them. goal: collect as many
//         darts as possible (in a time limit?).
//         ------> enemy attack/player defense if time (kill enemy, they drop
//                 a dart?
//              CLASSES NEEDED: CollectWorld extends World
//                              Player
//                              Enemies
//                              Darts
// MODE 2: entered a) voluntarily by player? b) when time runs up in mode 1?
//         this is a dart throwing/balloon popping game, pretty simple
//         player has the number of darts they collected in mode 1. score is
//         calculated by ratio of balloons popped to number of darts
//         when out of darts it returns to mode 1 to try again.
//         ------> if time, ask if they want to try again, exit window if no