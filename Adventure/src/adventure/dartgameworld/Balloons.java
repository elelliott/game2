package adventure.dartgameworld;
import javalib.worldimages.*;

public class Balloons {
    
    public Balloon[] ballArray;

    Balloons() {
        this.ballArray = new Balloon[4];
        for (Balloon b : this.ballArray) {
            b = new Balloon();
        }
    }
    
    private Balloons(Balloon[] b) {
        this.ballArray = b;
    }
    
    Balloons tick() {
        Balloon[] temp = new Balloon[this.ballArray.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = this.ballArray[i].tick();
        }
        
        return new Balloons(temp);
    }
    
    WorldImage makeImageHelp(int n) {
        if (n == 0) {
            return this.ballArray[0].makeImage();
        } else {
            return new OverlayImages(this.ballArray[n].makeImage(), 
                    this.makeImageHelp(n-1));
        }
    }
    
    WorldImage makeImage() {
        return this.makeImageHelp(this.ballArray.length-1);
    }
    
    Balloons pop(int ind) {
        Balloon[] temp = new Balloon[this.ballArray.length];
        
        for (int i = 0; i < temp.length; i++) {
            if (i == ind) {
                temp[i] = new Balloon();
            } else {
                temp[i] = this.ballArray[i];
            }
        }
        
        return new Balloons(temp);
    }
}
