package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Elevator extends Solid {

    public Elevator(String name, int x, int y, int width, int height){
        super(name, x, y, 0, 0, width, height, new Sprite("assets/imgs/elevator.png",700,812, 0,0, 0), ObjectStatus.ONLY_ONE,false);
        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init(){

        //addHitBox(new HitBox(0, 50, (int)(width*sizeRelative), (int)(height * sizeRelative)));

    }

    @Override
    public void render(){
        System.out.println("x:  " + x + ", y:  " + y + ", xoff:  " + getXOffset() + ", yoff:   " + getYOffset());
        sprite.render(x, y, width, height);
    }
}
