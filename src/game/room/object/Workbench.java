package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Workbench extends Solid {

    private int type;

    public static int LEFT = 1;
    public static int RIGHT = 2;

    public Workbench(String name, int x, int y, int width, int height,int type){
        super(name, x, y, 0, 0, 137, 400, new Sprite("assets/imgs/lab-workbench.png",0,0, 0,0, 0), ObjectStatus.ONLY_ONE,0.5d,false);
        this.type = type;
        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init(){
        addHitBox(new HitBox(0, 0, width,  height));
    }

    @Override
    public void render(){
        int framex = width, framey = height,srcx=0,srcy=0;


        framex = 137;
        framey = 400;

        if(type == RIGHT){
            srcx = 137;
            framex = 274;
        }




        int frameWidth = (int)((framex-srcx)*sizeRelative);
        int frameHeight = (int)((framey-srcy)*sizeRelative);

        sprite.render(x, y, x+width,y+height,srcx,srcy,framex,framey, null);
    }
}
