package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Store extends Solid {

    private int linha;
    private int coluna;
    public static int LINHA1 = 1;
    public static int LINHA2 = 2;
    public static int LINHA3 = 3;
    public static int LINHA4 = 4;

    public static int COLUNA1 = 1;
    public static int COLUNA2 = 2;
    public static int COLUNA3 = 3;
    public static int COLUNA4 = 4;
    public static int COLUNA5 = 5;


    public Store(String name, int x,int y,int xaxis, int yaxis, int linha,int coluna){
        super(name, x, y, xaxis, yaxis, 902,704, new Sprite("assets/imgs/generic/store_fronts.png", 902,704, 0,0, 1), ObjectStatus.ONLY_ONE,true);
        this.linha = linha;
        this.coluna = coluna;
        this.sizeRelative = 1.5f;

        if(linha == LINHA1){
            height = 160;
            switch(coluna){
                case 1:width = 250;break;
                case 2:width = 214;break;
                case 3:width = 207;height=161;break;
                case 4:width = 211;height=161;break;
                case 5:width = 231;height=231;break;
            }
        }else
        if(linha == LINHA2){
            switch (coluna){
                case 1:width = 268;height=195;break;
                case 2:width = 545;height=220;break;
                case 3:width = 273;height=199;break;
            }
        }else
        if(linha == LINHA3){
            switch (coluna){
                case 1:width = 242;height=177;break;
                case 2:width = 175;height=160;break;
                case 3:width = 243;height=160;break;
                case 4:width = 170;height=173;break;
                case 5:width = 251;height=164;break;
            }
        }else
        if(linha == LINHA4){
            switch (coluna){
                case 1:width = 233;height=158;break;
                case 2:width = 231;height=160;break;
                case 3:width = 291;height=160;break;
                case 4:width = 390;height=185;break;
            }
        }

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

        if(linha == LINHA1) {
            srcy = 12;
            switch(coluna){
                case 1:srcx=14;break;
                case 2:srcx=273;break;
                case 3:srcx=518;srcy=9;break;
                case 4:srcx=756;srcy=10;break;
                case 5:srcx=998;srcy=0;break;
            }
            framex = width+srcx;
            framey = height+srcy;
        }else
        if(linha == LINHA2){
            switch(coluna) {
                case 1:srcx=14;srcy=244;break;
                case 2:srcx=343;srcy=220;break;
                case 3:srcx=948;srcy=240;break;
            }
            framex = width+srcx;
            framey = height+srcy;
        }else
        if(linha == LINHA3){
            srcy=486;
            switch(coluna) {
                case 1:srcx=14;break;
                case 2:srcx=285;srcy=503;break;
                case 3:srcx=490;break;
                case 4:srcx=769;break;
                case 5:srcx=970;break;
            }
            framex = width+srcx;
            framey = height+srcy;
        }else
        if(linha == LINHA4){
            switch(coluna) {
                case 1:srcx=14;srcy=738;break;
                case 2:srcx=267;srcy=736;break;
                case 3:srcx=520;srcy=737;break;
                case 4:srcx=831;srcy=713;break;
            }
            framex = width+srcx;
            framey = height+srcy;
        }



        int frameWidth = (int)((framex-srcx)*sizeRelative);
        int frameHeight = (int)((framey-srcy)*sizeRelative);

        sprite.render(x, y, x+frameWidth,y+frameHeight,srcx,srcy,framex,framey, null);
    }

}
