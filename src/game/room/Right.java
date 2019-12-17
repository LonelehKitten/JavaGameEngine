package game.room;

import engine.ObjectStatus;
import engine.Scene;
import engine.Solid;
import engine.Sprite;
import game.entity.MOB.HungryZombie;
import game.entity.MOB.NormalZombie;
import game.entity.MOB.SlowZombie;
import game.entity.MOB.Zombie;
import game.room.object.*;

import java.awt.*;

public class Right extends Room{

    public Right(String name, int xaxis, int yaxis){
        super(name,xaxis,yaxis);
    }

    public int range(int i,int n,int m){
        if(i == n) return 0;
        if(i == m) return 2;
        return 1;
    }

    public int range2(int i,int n,int m){
        if(i == n) return 5;
        if(i == m) return 3;
        return 4;
    }

    @Override
    public void init() {

        addBackground(new Solid("background-3", -1493, -780, 0, -300, 3400, 1400, new engine.Sprite("assets/imgs/shopping_tile.png"), engine.ObjectStatus.TILED, false));

        addSolid("north-limit", -1493, -780, 3400, 20, new Color(0, 0, 0, 0), false);
        addSolid("south-limit", -1493, 600, 3400, 20, new Color(0, 0, 0, 0), false);

        addSolid("west-limit", -1493, -780, 20, 1400, new Color(0, 0, 0, 0), false);
        addSolid("east-limit", 1907, -780, 20, 1400, new Color(0, 0, 50, 0), false);

        //addSolid("cubinho", -50, -50, 50, 50, new Color(0x0000ff), false);
        int posX = 0, posY = 0, step = 0, i, type = 0, total = 7;

        step = 350;
        posY = 338;
        posX = -100;
        for (i = -4; i < 3; i++) {
            type = range(i, -4, 3 - 1);
            addSolid(new Tree("canteiro-bottom-left" + i, posX + (step * i), posY, 0, 0, type));
        }

        posY = -355;
        for (i = -4; i < 0; i++) {
            type = range(i, -4, 0 - 1);
            addSolid(new Tree("canteiro-top-left" + i, posX + (step * i), posY, 0, 0, type));
        }

        addSolid(new Tree("canteiro-top-leftVertical" , -252, -625, 0, 0, 5));


        posX = -410;
        posY = -1183;
        step = 313;
        for(i=1;i<5;i++) {
            type = range2(i,1,4);
            addSolid(new Tree("canteiro-right-all-"+i, 934, posY + (step * i), 0, 0, type));
        }
        posY = -980;

        /*
            1090
             250
              840
         */
        addSolid(new Store("loja-left-1", posX - 1090, posY, 0, 0, Store.LINHA1, Store.COLUNA1));
        addSolid(new Store("loja-left-2", posX - 758, posY-88, 0, 0, Store.LINHA2, Store.COLUNA2));
        addSolid(new Store("loja-left-3", posX  + 53, posY + 2, 0, 0, Store.LINHA1, Store.COLUNA2));
        addSolid(new Store("loja-left-4", posX  + 372, posY-26, 0, 0, Store.LINHA3, Store.COLUNA1));
        addSolid(new Store("loja-left-5", posX  + 1085, posY-15, 0, 0, Store.LINHA3, Store.COLUNA4));
        addSolid(new Store("loja-left-6", posX  + 1330, posY-30, 0, 0, Store.LINHA4, Store.COLUNA4));

        addSolid("cafeteira",posX  + 730, posY,361,240,new Sprite("assets/imgs/Coffee-Shop-Storefront.png",361,240,0,0,0), ObjectStatus.ONLY_ONE,true,false);
       // addSolid("teste2",posX + 970, posY+90,50,150, new Color(255,5,5,255),false);

    }

    @Override
    protected void resetZombies() {

        //zombies.add(new SlowZombie(0, 395, -5, 60, 100));
        //zombies.add(new Zombie(1, 110, -250, 60, 100));
        //zombies.add(new Zombie(2, 310, -300, 60, 100));
        //zombies.add(new Zombie(3, 210, -300, 60, 100));
        //zombies.add(new Zombie(4, -10, -30, 60, 100));
        //zombies.add(new Zombie(5, 210, -300, 60, 100));
        // zombies.add(new Zombie(6, -400, -700, 60, 100));
        //zombies.add(new Zombie(7, 600, -400, 60, 100));

        zombies.add(new SlowZombie(0, -1300, -103, 60, 100));
        zombies.add(new NormalZombie(1, -900, 45, 60, 100));
        zombies.add(new HungryZombie(2, -700, 150, 60, 100));
        zombies.add(new SlowZombie(3, -300, -103, 60, 100));

        zombies.add(new SlowZombie(4, 137, -17, 60, 100));
        zombies.add(new NormalZombie(5, 365, 131, 60, 100));
        zombies.add(new HungryZombie(6, 205, 243, 60, 100));
        zombies.add(new SlowZombie(7, 701, 155, 60, 100));

        zombies.add(new SlowZombie(8, 669, -169, 60, 100));
        zombies.add(new NormalZombie(9, 165, -353, 60, 100));
        zombies.add(new HungryZombie(10, 169, -689, 60, 100));

        zombies.add(new SlowZombie(11, -603, -477, 60, 100));
        zombies.add(new SlowZombie(12, -943, 653, 60, 100));

        for (Zombie z : zombies) {
            addEntity(z);
        }

    }

//    private void NewTable(int posX,int posY){
//        addSolid(new Table("mesa"+posX+posY,posX-400,posY+50,0,0, Table.HORIZONTAL));
//
//        addSolid(new Char("cadeira1"+posX+posY,posX-470,posY+50,0,0, Char.EAST));
//        addSolid(new Char("cadeira2"+posX+posY,posX-185,posY+50,0,0, Char.WEST));
//
//        addSolid(new Char("cadeira3"+posX+posY,posX-335,posY-70,0,0, Char.SOUTH));
//        addSolid(new Char("cadeira4"+posX+posY,posX-335,posY+150,0,0, Char.NORTH));
//    }

}
