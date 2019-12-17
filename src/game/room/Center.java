package game.room;

import engine.*;
import game.entity.MOB.HungryZombie;
import game.entity.MOB.NormalZombie;
import game.entity.MOB.SlowZombie;
import game.entity.MOB.Zombie;
import game.room.object.*;
import java.awt.*;


public class Center extends Room{


    public Center(String name, int xaxis, int yaxis){
        super(name,xaxis,yaxis);
    }

    public int range(int i,int n,int m){
        if(i == n) return 0;
        if(i == m) return 2;
        return 1;
    }

    @Override
    public void init() {

        //setCurrent(true);
        addBackground(new Solid("background-3", -1493, -780, 0, -300, 3400, 1400, new engine.Sprite("assets/imgs/shopping_tile.png"), engine.ObjectStatus.TILED, false));

        //addBackground(new Solid("background-4", -1493, -780, 0, -300, 3400, 1400, new engine.Sprite("assets/imgs/blood.png"), engine.ObjectStatus.TILED, false));

        addSolid("north-limit",-1493 , -780, 3400, 20, new Color(0, 0, 0,0), false);
        addSolid("south-limit",-1493 , 600, 3400, 20, new Color(0,0,0,0), false);

        addSolid("west-limit",-1493 , -780, 20, 1400, new Color(0,0,0,0), false);
        addSolid("east-limit",1907 , -780, 20, 1400, new Color(0,0,50,0), false);

        int posX = 0, posY = 0, step = 0, i, type = 0, total = 7;

        step = 350;
        posY = 338;
        posX = -100;
        for (i = -4; i < 0; i++) {
            type = range(i, -4, 0 - 1);
            addSolid(new Tree("canteiro-bottom-left" + i, posX + (step * i), posY, 0, 0, type));
        }

        posY = -355;
        for (i = -4; i < 0; i++) {
            type = range(i, -4, 0 - 1);
            addSolid(new Tree("canteiro-top-left" + i, posX + (step * i), posY, 0, 0, type));
        }

        posX = 496;
        posY = 338;
        for (i = 0; i < 4; i++) {
            type = range(i, 0, 3);
            addSolid(new Tree("canteiro-bottom-right" + i, posX + (step * i), posY, 0, 0, type));
        }

        posY = -355;
        for (i = 0; i < 4; i++) {
            type = range(i, 0, 3);
            addSolid(new Tree("canteiro-top-right" + i, posX + (step * i), posY, 0, 0, type));
        }
        posX = -430;
        posY = -980;

        addSolid(new Store("loja-left-1", posX - 1050, posY, 0, 0, Store.LINHA1, Store.COLUNA2));
        addSolid(new Store("loja-left-2", posX - 730, posY - 50, 0, 0, Store.LINHA2, Store.COLUNA1));
        addSolid(new Store("loja-left-3", posX - 334, posY, 0, 0, Store.LINHA1, Store.COLUNA1));
        addSolid(new Store("loja-left-4", posX + 0, posY, 0, 0, Store.LINHA1, Store.COLUNA2));
        posY = -970;
        addSolid(new Store("loja-left-5", posX + 885, posY - 20, 0, 0, Store.LINHA3, Store.COLUNA2));
        addSolid(new Store("loja-left-6", posX + 1140, posY - 20, 0, 0, Store.LINHA3, Store.COLUNA3));
        addSolid(new Store("loja-left-7", posX + 1495, posY - 17, 0, 0, Store.LINHA4, Store.COLUNA1));
        addSolid(new Store("loja-left-8", posX + 1830, posY - 20, 0, 0, Store.LINHA3, Store.COLUNA2));
        addSolid(new Store("loja-left-9", posX + 2080, posY - 40, 0, 0, Store.LINHA3, Store.COLUNA4));
/*
        560
         85
         480
        */

        addSolid(new Fountain("fountain-center",0,-50,0,0,0));

    }

    @Override
    protected void resetZombies() {

        zombies.add(new SlowZombie(0, -1300, -103, 60, 100));
        zombies.add(new NormalZombie(1, -900, 45, 60, 100));
        zombies.add(new HungryZombie(2, -700, 150, 60, 100));
        zombies.add(new SlowZombie(3, -300, -103, 60, 100));

        zombies.add(new SlowZombie(6, -1300, -748, 60, 100));
        zombies.add(new NormalZombie(7, -900, -575, 60, 100));
        zombies.add(new HungryZombie(8, -700, -430, 60, 100));
        zombies.add(new SlowZombie(9, -300, -748, 60, 100));
        zombies.add(new NormalZombie(10, -700, -575, 60, 100));
        zombies.add(new HungryZombie(11, -500, -430, 60, 100));

        zombies.add(new SlowZombie(12, 51, -425, 60, 100));
        zombies.add(new NormalZombie(13, 279, -617, 60, 100));
        zombies.add(new HungryZombie(14, -17, -717, 60, 100));

        zombies.add(new NormalZombie(16, 1300, -103, 60, 100));
        zombies.add(new HungryZombie(17, 900, 45, 60, 100));
        zombies.add(new SlowZombie(18, 700, 150, 60, 100));
        zombies.add(new NormalZombie(19, 300, -103, 60, 100));

        zombies.add(new NormalZombie(22, 1300, -748, 60, 100));
        zombies.add(new HungryZombie(23, 900, -575, 60, 100));
        zombies.add(new SlowZombie(24, 700, -430, 60, 100));
        zombies.add(new NormalZombie(25, 300, -748, 60, 100));
        zombies.add(new HungryZombie(26, 700, -575, 60, 100));
        zombies.add(new SlowZombie(27, 500, -430, 60, 100));

        for (Zombie z : zombies) {
            addEntity(z);
        }

    }
}
