package adventure;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;

public class Darts {
    Posn[] dartsArray;
    int tickCount;
    
    Darts() {
        this.dartsArray = new Posn[5];
        
        // INITIALIZE THESE TO BE RANDOM
    }
    
    Darts tick() {
        return this;
        // this will have to keep track of either time or tick count
        // to decide when to add/remove darts from the array 
    }
    
    Darts addDartToEnd() {
        return this;
    }
    
    Darts removeDartFromFront() {
        return this;
    }
    
    
}