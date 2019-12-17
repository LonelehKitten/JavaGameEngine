package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Incubators extends Solid {

    private int type;
    public static int LEFT = 1;
    public static int CENTER = 2;
    public static int RIGHT = 3;




    public Incubators(String name, int x,int y,int xaxis, int yaxis, int type){
        super(name, x, y, xaxis, yaxis, 337,699, new Sprite("assets/imgs/incubators.png", 80,140, 0,0, 1), ObjectStatus.ONLY_ONE,true);
        this.type = type;
        this.sizeRelative = 0.4f;
        if(type == CENTER){
            width = 773;
            height = 753;
            this.sizeRelative = 0.9f;
        }
        if(type == RIGHT){
            width = 339;
            height = 818;
        }
        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init(){
        addHitBox(new HitBox(0, -50, (int)(width*sizeRelative), (int)(height*sizeRelative)+50));
    }


    @Override
    public void render(){
        int framex = width, framey = height,srcx=0,srcy=60;

        switch(type){
            case 1:srcx=0;break;
            case 2:srcx=359;srcy=50;break;
            case 3:srcx=1160;srcy=0;break;
        }
        framex = width+srcx;
        framey = height+srcy;

        int frameWidth = (int)((framex-srcx)*sizeRelative);
        int frameHeight = (int)((framey-srcy)*sizeRelative);

        sprite.render(x, y, x+frameWidth,y+frameHeight,srcx,srcy,framex,framey, null);
    }

}
