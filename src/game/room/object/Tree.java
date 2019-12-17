package game.room.object;

import engine.HitBox;
import engine.ObjectStatus;
import engine.Solid;
import engine.Sprite;

public class Tree extends Solid {

    private int type;
    public static int HORIZONTAL1 = 0;
    public static int HORIZONTAL2 = 1;
    public static int HORIZONTAL3 = 2;

    public static int VERTICAL1 = 3;
    public static int VERTICAL2 = 4;
    public static int VERTICAL3 = 5;

    public Tree(String name, int x,int y,int xaxis, int yaxis, int type){
        //(int) (350*0.8), (int) (210*0.8)
        super(name, x, y, xaxis, yaxis, 902,529, new Sprite("assets/imgs/generic/tree.png", 902,704, 0,0, 1), ObjectStatus.ONLY_ONE,0.4d, true);
        this.type = type;
//        this.sizeRelative = 0.4f;
//        if(type >= VERTICAL1 && type <= VERTICAL3){
//            width = 376;
//            height = 898;
//            this.sizeRelative = 0.5f;
//
//        }


        if(type >= VERTICAL1 && type <= VERTICAL3){
            width = (int) (373*0.5d);
            height = (int) (898*0.5d);
        }


        init();
    }


    /**
     *  IMPLEMENTEI A HITBOX
     */
    private void init() {
        if(type >= HORIZONTAL1 && type <= HORIZONTAL3){
            addHitBox(new HitBox(0,  height-100, width, height-100));
        }else{
            addHitBox(new HitBox(40, height-300 , width-80, height-50));
        }

    }


    @Override
    public void render(){
        int framex = width, framey = height,srcx=0,srcy=0;

        if(type >= HORIZONTAL1 && type <= HORIZONTAL3){
            if(type == 1){
                srcx = 902;
            }else if(type == 2){
                srcx = 902*2;
            }
            framex = 902+srcx;//width+srcx;
            framey = 704+srcy;//height+srcy;
        }else{
            srcx = 2713;
            if(type == 4){
                srcx = 3110;//3110;
            }else if(type == 5){
                srcx = 3517;//3516;
            }
            framex = 373+srcx;//width+srcx;
            framey = 898+srcy;//height+srcy;
        }

        //int frameWidth = (int)((framex-srcx)*sizeRelative);
        //int frameHeight = (int)((framey-srcy)*sizeRelative);

        sprite.render(x, y, x+width, y+height,srcx,srcy,framex,framey, null);
    }

    /*
        if(type >= HORIZONTAL1 && type <= HORIZONTAL3){
            if(type == 1){
                srcx = framex;
                framex *= 2;
            }else if(type == 2){
                srcx = framex*2;
                framex *= 3;
            }
        }else{
            srcx = 2722;
            if(type == 5){
                srcx = 3110;
            }else if(type == 6){
                srcx = 3516;
            }
            framex = width+srcx;
            framey = height+srcy;
        }*/
    /*
        x = 0;
        y = 0;
        xoffset = 678
        yoffset = 529

        width =  678;
        height = 529;


        EXEMPLO

          (x, y)
        +---+------+----------+
        |   |######|          |
        |   |######|          |
        |   |######|          |
        +---+------+ (xoffset, yoffset)
        |                     |
        |                     |
        +----------+----------+

        Imagem 200x200

        x = 10
        y = 0
        xoffset = 100
        yoffset = 100

        logo, width = 90, height = 100

     */
}
