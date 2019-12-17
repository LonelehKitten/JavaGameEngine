package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Char extends Solid {

    private int type;
    public static int EAST = 1;
    public static int WEST = 2;
    public static int SOUTH = 3;
    public static int NORTH = 4;



    public Char(String name, int x,int y,int xaxis, int yaxis, int type){
        super(name, x, y, xaxis, yaxis, 80,140, new Sprite("assets/imgs/chair.png", 80,140, 0,0, 1), ObjectStatus.ONLY_ONE,true);
        this.type = type;
        this.sizeRelative = 0.8f;
        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init(){
        addHitBox(new HitBox(0, 0, (int)(width*sizeRelative), (int)(height*sizeRelative)));
    }


    @Override
    public void render(){
        int framex = width, framey = height,srcx=0,srcy=0;

        switch(type){
            case 1:srcx=0;break;
            case 2:srcx=80;break;
            case 3:srcx=160;break;
            case 4:srcx=240;break;
        }
        framex = width+srcx;
        framey = height+srcy;



        int frameWidth = (int)((framex-srcx)*sizeRelative);
        int frameHeight = (int)((framey-srcy)*sizeRelative);

        sprite.render(x, y, x+frameWidth,y+frameHeight,srcx,srcy,framex,framey, null);
    }

}
