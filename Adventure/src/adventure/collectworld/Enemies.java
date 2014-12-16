package adventure.collectworld;

import javalib.worldimages.*;

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

    WorldImage makeImageHelp(int n) {
        if (n <= 0) {
            return this.enemyArray[0].makeImage();
        } else {
            return new OverlayImages(this.enemyArray[n].makeImage(),
                    this.makeImageHelp(n - 1));
        }
    }

    WorldImage makeImage() {
        return this.makeImageHelp(this.enemyArray.length-1);
    }
}
