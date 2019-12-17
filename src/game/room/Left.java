package game.room;

import engine.Scene;
import engine.Solid;
import game.entity.MOB.HungryZombie;
import game.entity.MOB.NormalZombie;
import game.entity.MOB.SlowZombie;
import game.entity.MOB.Zombie;
import game.room.object.*;

import java.awt.*;

public class Left extends Room{

    public Left(String name, int xaxis, int yaxis){
        super(name,xaxis,yaxis);
    }

    public int range(int i,int n,int m){
        if(i == n) return 0;
        if(i == m) return 2;
        return 1;
    }

    @Override
    public void init() {

        addBackground(new Solid("background-3", -1493, -780, 0, -300, 3400, 1400, new engine.Sprite("assets/imgs/shopping_tile.png"), engine.ObjectStatus.TILED, false));

        addSolid("north-limit", -1493, -780, 3400, 20, new Color(0, 0, 0, 0), false);
        addSolid("south-limit", -1493, 600, 3400, 20, new Color(0, 0, 0, 0), false);

        addSolid("west-limit", -1493, -780, 20, 1400, new Color(0, 0, 0, 0), false);
        addSolid("east-limit", 1907, -780, 20, 1400, new Color(0, 0, 50, 0), false);

        addSolid("cubinho", -50, -50, 50, 50, new Color(0x0000ff), false);
        int posX = 0, posY = 0, step = 0, i,j, type = 0, total = 7;

        step = 350;
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

        for(i=-1;i<1;i++) {
            for (j = -2; j < 2; j++) {
                NewTable(450 * j, 400 * i);
            }
        }

        posX = -410;
        posY = -980;
        /*
            1090
             250
              840
         */
        addSolid(new Store("loja-left-4", posX - 1090, posY, 0, 0, Store.LINHA1, Store.COLUNA1));
        addSolid(new Store("loja-left-3", posX - 758, posY-88, 0, 0, Store.LINHA2, Store.COLUNA2));
        addSolid(new Store("loja-left-2", posX  + 53, posY + 2, 0, 0, Store.LINHA1, Store.COLUNA2));
        addSolid(new Store("loja-left-1", posX  + 372, posY-26, 0, 0, Store.LINHA3, Store.COLUNA1));
        addSolid(new Tree("canteiro-top-leftVertical" , 480, -625, 0, 0, 5));

        addSolid(new Store("loja-left-5", posX + 729, posY , 0, 0, Store.LINHA3, Store.COLUNA2));
        addSolid(new Store("loja-left-6", posX + 980, posY , 0, 0, Store.LINHA3, Store.COLUNA3));
        addSolid(new Store("loja-left-7", posX + 1335, posY + 2 , 0, 0, Store.LINHA4, Store.COLUNA1));
        addSolid(new Store("loja-left-8", posX + 1680, posY - 20 , 0, 0, Store.LINHA3, Store.COLUNA1));
        addSolid(new Store("loja-left-9", posX + 2050, posY , 0, 0, Store.LINHA3, Store.COLUNA2));

    }

    private void NewTable(int posX,int posY){
        addSolid(new Table("mesa"+posX+posY,posX-400,posY+50,0,0, Table.HORIZONTAL));

        addSolid(new Char("cadeira1"+posX+posY,posX-470,posY+50,0,0, Char.EAST));
        addSolid(new Char("cadeira2"+posX+posY,posX-185,posY+50,0,0, Char.WEST));

        addSolid(new Char("cadeira3"+posX+posY,posX-335,posY-70,0,0, Char.SOUTH));
        addSolid(new Char("cadeira4"+posX+posY,posX-335,posY+150,0,0, Char.NORTH));
    }

    @Override
    protected void resetZombies() {

        zombies.add(new SlowZombie(0, 1300, -103, 60, 100));
        zombies.add(new NormalZombie(1, 900, 45, 60, 100));
        zombies.add(new HungryZombie(2, 700, 150, 60, 100));
        zombies.add(new SlowZombie(3, 300, -103, 60, 100));

        zombies.add(new SlowZombie(7, 64, -104, 60, 100));
        zombies.add(new NormalZombie(8, 64, 259, 60, 100));
        zombies.add(new NormalZombie(9, 64, 559, 60, 100));

        zombies.add(new SlowZombie(10, -799, -283, 60, 100));
        zombies.add(new NormalZombie(11, -115, 259, 60, 100));
        zombies.add(new HungryZombie(12, -451, 419, 60, 100));

        zombies.add(new SlowZombie(13, 1103, -80, 60, 100));
        zombies.add(new NormalZombie(14, -546, -80, 60, 100));
        zombies.add(new HungryZombie(15, -126, -80, 60, 100));

        for (Zombie z : zombies) {
            addEntity(z);
        }

    }

}
