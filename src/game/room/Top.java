package game.room;

import engine.ObjectStatus;
import engine.Scene;
import engine.Solid;
import engine.Sprite;
import game.entity.MOB.Boss;
import game.entity.MOB.SlowZombie;
import game.entity.MOB.Zombie;
import game.room.object.*;

import java.awt.*;

public class Top extends Room{

    private Boss boss;

    public Top(String name, int xaxis, int yaxis){
        super(name,xaxis,yaxis);
    }

    public int range(int i,int n,int m){
        if(i == n) return 0;
        if(i == m) return 2;
        return 1;
    }

    @Override
    public void init() {

        addBackground(new Solid("background-3", -1000, -1000, 0, -300, 2000, 2000, new engine.Sprite("assets/imgs/shopping_tile.png"), engine.ObjectStatus.TILED, false));

        addSolid("north-limit", -1000, -1000, 2000, 20, new Color(0, 0, 0, 0), false);
        addSolid("south-limit", -1000, 1000, 2000, 20, new Color(0, 0, 0, 0), false);

        addSolid("west-limit", -1000, -1000, 20, 2000, new Color(0, 0, 0, 0), false);
        addSolid("east-limit", 1000, -1000, 20, 2000, new Color(0, 0, 0, 0), false);



        int posX = 0, posY = 0, step = 0, i,j, type = 0, total = 7;

        addSolid(new Workbench("table-left-1",-720,381,0,0,Workbench.LEFT));
        addSolid(new Workbench("table-left-2",-720,51,0,0,Workbench.LEFT));
        addSolid(new Workbench("table-left-3",-720,-279,0,0,Workbench.LEFT));
        addSolid(new Workbench("table-left-4",-720,-609,0,0,Workbench.LEFT));

        addSolid(new Workbench("table-right-1",592,381,0,0,Workbench.RIGHT));
        addSolid(new Workbench("table-right-2",592,51,0,0,Workbench.RIGHT));
        addSolid(new Workbench("table-right-3",592,-279,0,0,Workbench.RIGHT));
        addSolid(new Workbench("table-right-4",592,-609,0,0,Workbench.RIGHT));


        addSolid("north-limit", -1000, -1360, 2000, 360, new Color(240,240,240), false);

        addSolid(new Incubators("incubators-top-1",-860,-1150,0,0, Incubators.LEFT));
        addSolid(new Incubators("incubators-top-2",726,-1150,0,0, Incubators.LEFT));
        addSolid(new Incubators("incubators-center",-348,-1360,0,0, Incubators.CENTER));


        addSolid(new Incubators("incubators-left-1",-940,259,0,0, Incubators.RIGHT));
        addSolid(new Incubators("incubators-left-2",-940,-500,0,0, Incubators.RIGHT));

        addSolid(new Incubators("incubators-right-1",806,259,0,0, Incubators.RIGHT));
        addSolid(new Incubators("incubators-right-2",806,-500,0,0, Incubators.RIGHT));
    }

    @Override
    protected void resetZombies() {

        boss = new Boss(-472, -886, 919, 535);
        addSolid(boss, false);

    }

    @Override
    protected void preUpdate() {

        if(!zombiesReseted){
            resetZombies();
            zombiesReseted = true;
        }
        else{
            boss.setPivotX(player.getX());
            boss.setPivotY(player.getY());
        }

    }

    @Override
    protected void postUpdate() {
        checkSceneChange(getSolid("player"));
    }
}
