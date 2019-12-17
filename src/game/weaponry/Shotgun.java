package game.weaponry;

import engine.IConfig;
import engine.Song;
import engine.Sprite;

import java.awt.*;

public class Shotgun extends Weapon{

    public Shotgun(int maskX, int maskY, int maskWidth, int maskHeight){
        super(
                "Shotgun", 40, 50, 2, 30,
                new Sprite(
                        "assets/imgs/shotgun.png",
                        198,
                        200,
                        1,
                        8,
                        0
                ),
                null
        );

        this.X = maskX;
        this.Y = maskY;
        this.width = maskWidth;
        this.height = maskHeight;

        aim = new Aim(
                "type-1",
                IConfig.MONITOR_WIDTH/2,
                IConfig.MONITOR_HEIGHT/2,
                50,
                100,
                new Color(0xff0000),
                new BasicStroke(2)
        );

        song = new Song("assets/songs/shotgun.wav");

        damage = 20;
    }

    @Override
    public void render() {
        super.render();

        sprite.render(x, y, getAbsoluteXOffset(), getAbsoluteYOffset(), 1, direction);
    }
}
