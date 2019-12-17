package game.room;

import engine.ObjectStatus;
import engine.Scene;
import engine.Solid;
import engine.Sprite;
import game.GUI.loja.StoreInterface;

import java.awt.*;

public class Seller extends Room {

    private StoreInterface storeInterface;

    public Seller(String name, int xaxis, int yaxis, StoreInterface storeInterface){
        super(name,xaxis,yaxis);
        this.storeInterface = storeInterface;
    }

    @Override
    public void init() { //700 812

        int width = (int)(1172*0.4),height = (int)(829*0.4);
        Solid bk = new Solid("elevator-background", -150 , -250, 0, 0,width, height,new Sprite("assets/imgs/weapon-seller.png",width,height, 0,0, 0), ObjectStatus.ONLY_ONE,false);

        addBackground(bk);

        addSolid("north-limit",-170 , -250, width+20, 20, new Color(0,0,0,0), false);
        //addSolid("south-limit",-170 , height-250, width+20, 20, new Color(0,0,0,0), false);
        addSolid("west-limit",-170 , -250, 20, height, new Color(0,0,0,0), false);
        addSolid("east-limit",width-150 , -250, 20, height, new Color(0,0,0,0), false);
        addSolid("block1",-150 , -250, width, 233, new Color(0,255,0,150), false);
       //-- Não apagar, ainda ver. addSolid("block2",106 , -15, 207, 19, new Color(255,0,0,150), false);
        addSolid("block3",150 , 70, 100, 30, new Color(0,0,0,0), false);
        addSolid("block5",-150 , 18, 212, 63, new Color(0,0,0,0), false);


    }

    @Override
    public void render(){
        super.render();
        storeInterface.render();
    }

    @Override
    public void update(){
        super.update();
        storeInterface.update();
    }

    @Override
    protected void resetZombies() {}
}
