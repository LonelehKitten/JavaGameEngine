package game.weaponry;

import engine.Camera;
import engine.IConfig;
import engine.Song;
import engine.Sprite;

import java.awt.*;

public class Pistol extends Weapon{

    public Pistol(int maskX, int maskY, int maskWidth, int maskHeight){
        super(
                "Pistol", 20, 50, 2,
                new Sprite(
                        "assets/imgs/pistol.png",
                        127,
                        200,
                        1,
                        8,
                        0
                ),
                null
        );

        this.x = maskX;
        this.y = maskY;
        this.width = maskWidth;
        this.height = maskHeight;

        aim = new Aim(
                "type-1",
                IConfig.MONITOR_WIDTH/2,
                IConfig.MONITOR_HEIGHT/2,
                50,
                80,
                new Color(0x6666ff),
                new BasicStroke(1)
        );

        song = new Song("assets/songs/pistol.wav");

        damage = 10;
    }

    @Override
    public void render() {
        super.render();

        sprite.render(x, y, getAbsoluteXOffset(), getAbsoluteYOffset(), 1, direction);
    }
}
