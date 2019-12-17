package game.room;

import engine.Scene;
import game.Player;
import game.entity.MOB.Zombie;
import game.weaponry.Bullet;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Room extends Scene {

    protected ArrayList<Zombie> zombies = new ArrayList<>();
    protected Player player;
    protected boolean zombiesReseted = false;

    public Room(String name, int xaxis, int yaxis) {
        super(name, xaxis, yaxis);
        init();
    }

    protected abstract void init();
    protected abstract void resetZombies();

    @Override
    protected void preUpdate() {

        if(!zombiesReseted){
            resetZombies();
            zombiesReseted = true;
        }
        else {

            Iterator<Zombie> zit = zombies.iterator();
            while (zit.hasNext()) {
                Zombie z = zit.next();

                ArrayList<Bullet> ammo = player.getWeapon().getBullet();
                Iterator<Bullet> bit = ammo.iterator();


                while (bit.hasNext()) {

                    Bullet b = bit.next();
                    if (z.isNextEnough() && b.isFired() && z.wasHitBy(b, player.getWeapon().getDamage())) {

                        bit.remove();

                    }

                }

                if (z.getHP() <= 0) {
                    remove(z.getName(), true, false);
                    zit.remove();
                    player.setCash(player.getCash() + 50);
                    continue;
                }

                z.setPivotX(sceneObjects.get("player").getX());
                z.setPivotY(sceneObjects.get("player").getY());

                if (z.causingDamage()) {
                    player.setHp(player.getHp() - z.getDamage());
                }
            }
        }

    }

    @Override
    protected void postUpdate() {
        if(checkSceneChange(getSolid("player"))){

            for(Zombie z : zombies) remove(z.getName(), true, false);
            zombies.clear();

            zombiesReseted = false;

        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}
