package game.weaponry;

import engine.IConfig;
import engine.Song;
import engine.Sprite;

import java.awt.*;

public class SubMachineGun extends Weapon{

    public SubMachineGun(int maskX, int maskY, int maskWidth, int maskHeight){
        super(
                "Sub Machine Gun", 2, 50,1, true, 15,
                new Sprite(
                        "assets/imgs/submachinegun.png",
                        139,
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
                120,
                new Color(0xff0000),
                new BasicStroke(1)
        );

        song = new Song("assets/songs/submachinegun.wav");

        damage = 5;
    }

    @Override
    public void render() {
        super.render();

        sprite.render(x, y, getAbsoluteXOffset(), getAbsoluteYOffset(), 1, direction);
    }
}
