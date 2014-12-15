package adventure;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;

public class Enemies {
    Enemy[] enemyArray;

    Enemies() {
        this.enemyArray = new Enemy[4];
        
        for (Enemy e : this.enemyArray) {
            e = new Enemy();
        }
    }
    
    private Enemies(Enemy[] e) {
        this.enemyArray = e;
    }
    
    Enemies tick() {
        Enemy[] temp = new Enemy[this.enemyArray.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = this.enemyArray[i].tick();
        }
        
        return new Enemies(temp);
    }
}