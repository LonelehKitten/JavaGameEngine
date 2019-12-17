package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Fountain extends Solid {

    public Fountain(String name, int x,int y,int xaxis, int yaxis, int type){
        super(name, x, y, xaxis, yaxis,566,473, new Sprite("assets/imgs/fountain.png"),ObjectStatus.ONLY_ONE, 0.7d, true);
        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init(){

        addHitBox(new HitBox(0, 70, width, height-70));

    }

}
