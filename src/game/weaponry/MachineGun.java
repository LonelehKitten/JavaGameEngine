package game.weaponry;

import engine.IConfig;
import engine.Song;
import engine.Sprite;

import java.awt.*;

public class MachineGun extends Weapon {

    public MachineGun(int maskX, int maskY, int maskWidth, int maskHeight){
        super(
                "Machine Gun", 5, 50,2, true, 20,
                new Sprite(
                        "assets/imgs/machinegun.png",
                        207,
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
                300,
                new Color(0xff0000),
                new BasicStroke(2)
        );

        song = new Song("assets/songs/machinegun.wav");
        damage = 15;
    }

    @Override
    public void render() {
        super.render();

        sprite.render(x, y, getAbsoluteXOffset(), getAbsoluteYOffset(), 1, direction);
    }
}
