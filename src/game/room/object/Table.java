package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Table extends Solid {

    private int type;
    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;

    public Table(String name, int x,int y,int xaxis, int yaxis, int type){
        super(name, x, y, xaxis, yaxis, 0,0, new Sprite("assets/imgs/generic/table.png", 0,0, 0,0, 1), ObjectStatus.ONLY_ONE,true);
        this.type = type;
        if(type == HORIZONTAL){
            width = 210;
            height = 150;
        }else if(type == VERTICAL){
            width = 128;
            height = 210;
        }
        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init(){

        addHitBox(new HitBox(0, 0, width, height));

    }


    @Override
    public void render(){
        int framex = 0, framey = 0,spritW=0,spritH=0,outroX=0,outroY=0;
        if(type == HORIZONTAL){
            spritW = 210;
            spritH = 150;
            outroX = 210;
            outroY = 150;
        }else if(type == VERTICAL){
            framex = 216;

            spritW = outroX = 128;
            spritH = outroY = 210;
        }

        sprite.render(x, y, x+spritW,y+spritH,outroX,outroY,framex,framey);
    }
}
