package game.weaponry;

import engine.*;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Weapon extends Solid {

    protected Sprite icon;
    protected ArrayList<Bullet> bullet = new ArrayList<>();
    protected Aim aim;
    protected Song song;

    protected int bulletSpeed, bulletSize, fireInterval, fireSpeedCounter, direction;
    protected long spread;
    private boolean continuous, fired;

    protected int damage;

    private Weapon(String name, int bulletSpeed, int bulletSize, Sprite sprite, Sprite icon, boolean continuous){
        super(name, 0, 0, 0, 0, 0, 0, sprite, ObjectStatus.ONLY_ONE_ANIMATED, false);
        this.icon = icon;
        this.bulletSpeed = bulletSpeed;
        this.bulletSize = bulletSize;
        this.continuous = continuous;
        this.fireSpeedCounter = 0;
    }

    public Weapon(String name, int fireInterval, int bulletSpeed, int bulletSize, Sprite sprite, Sprite icon){
        this(name, bulletSpeed, bulletSize, sprite, icon, false);
        this.fireInterval = fireInterval;
        this.continuous = false;
        this.spread = 0;
    }

    public Weapon(String name, int fireInterval, int bulletSpeed, int bulletSize, long spread, Sprite sprite, Sprite icon){
        this(name, bulletSpeed, bulletSize, sprite, icon, false);
        this.fireInterval = fireInterval;
        this.continuous = false;
        this.spread = spread;
    }

    public Weapon(String name, int fireInterval, int bulletSpeed, int bulletSize, boolean continuous, Sprite sprite, Sprite icon){
        this(name, bulletSpeed, bulletSize, sprite, icon, true);
        this.fireInterval = fireInterval;
        this.continuous = continuous;
        this.spread = 0;
    }

    public Weapon(String name, int fireInterval, int bulletSpeed, int bulletSize, boolean continuous, long spread, Sprite sprite, Sprite icon){
        this(name, bulletSpeed, bulletSize, sprite, icon, true);
        this.spread = spread;
        this.fireInterval = fireInterval;
        this.continuous = continuous;
    }

    @Override
    public void update() {

        if(aim != null) aim.update();
        int i = 0;
        for ( Bullet b : bullet) {
            System.out.println(i++);
            b.update();
        }

        if(continuous && !fired && song.isPlaying()) song.pause();

        if(!GameSystem.isLeftButtonPressed()) fired = false;
    }

    @Override
    public void updateRelativeToScene(int xaxis, int yaxis){
        super.updateRelativeToScene(xaxis, yaxis);
        try {

            Iterator<Bullet> it = bullet.iterator();
            while (it.hasNext()) {
                Bullet b = it.next();
                b.updateRelativeToScene(xaxis, yaxis);

                if (b.getTicks() == 0) {
                    //b.getX() > 3000 || b.getX() < -3000 || b.getY() > 3000 || b.getY() < -3000)
                    //System.out.println("APAGOU APAGOU APAGOU APAGOU");
                    bullet.remove(b);
                }

            }

            Bullet.setCurrentAxisPosition(xaxis, yaxis);

            if (fireSpeedCounter > 0) fireSpeedCounter--;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void render() {

        if(aim != null) aim.render();

        for ( Bullet b : bullet) {
            RenderStrategy.getStrategy().setColor(b.getColor());
            RenderStrategy.getStrategy().setStroke(new BasicStroke(4));
            b.render();
        }
    }

    public void fire() {
        System.out.println("pa");
        if (fireSpeedCounter == 0) {
            System.out.println("panela");

            int x = Camera.getX();
            int y = Camera.getY();
            int pivotx = GameSystem.getMouseX();
            int pivoty = GameSystem.getMouseY();

            if (continuous) {
                if(spread != 0){
                    bullet.add(new Bullet(x, y, pivotx + ( (int) (Math.random() * spread) * (System.nanoTime() % spread == 0 ? 1 : -1) ), pivoty + ( (int) (Math.random() * spread) * (System.nanoTime() % spread == 0 ? 1 : -1) ), bulletSize, bulletSpeed, new Color(0xffff00)));
                }
                else{
                    bullet.add(new Bullet(x, y, pivotx, pivoty, bulletSize, bulletSpeed, new Color(0xffff00)));
                }
            }
            else {

                if (!fired) {
                    //System.out.println("SPREAD FACTOR: " + spread);
                    if(spread > 0){
                        //System.out.println("shotgun YO!");

                        for(int i = 0; i < 8; i++){
                            if(i % 2 == 0)
                                bullet.add(new Bullet(x, y, pivotx + (int) (Math.random() *spread), pivoty + (int) (Math.random() * spread), bulletSize, bulletSpeed, new Color(0xffff00)));
                            else
                                bullet.add(new Bullet(x, y,pivotx - (int) (Math.random() * spread), pivoty - (int) (Math.random() * spread), bulletSize, bulletSpeed, new Color(0xffff00)));
                            System.out.println("x:    " + x + " y:   " + y + " pivotx:    " + pivotx + " pivoty:    " + pivoty + " random: " + Math.random());
                        }

                    }
                    else bullet.add(new Bullet(x, y, pivotx, pivoty, bulletSize, bulletSpeed, new Color(0xffff00)));

                }


            }

            fired = true;

            fireSpeedCounter = fireInterval;
        }

        playShotSound();

    }

    private void playShotSound(){
        if(fired && song != null){
            //System.out.println("SOM N TA NULO");
            if(continuous && !song.isPlaying()){

                song.play(Clip.LOOP_CONTINUOUSLY);
            }
            else if(!continuous && !song.isPlaying()){

                song.reset();
                song.play();

            }

        }

    }


    public Aim getAim() {
        return aim;
    }

    public void setAim(Aim aim) {
        this.aim = aim;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Sprite getIcon() {
        return icon;
    }

    public void setIcon(Sprite icon) {
        this.icon = icon;
    }

    public ArrayList<Bullet> getBullet() {
        return bullet;
    }

    public void setBullet(ArrayList<Bullet> bullet) {
        this.bullet = bullet;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
    }

    public int getFireInterval() {
        return fireInterval;
    }

    public void setFireInterval(int fireInterval) {
        this.fireInterval = fireInterval;
    }

    public int getFireSpeedCounter() {
        return fireSpeedCounter;
    }

    public void setFireSpeedCounter(int fireSpeedCounter) {
        this.fireSpeedCounter = fireSpeedCounter;
    }

    public long getSpread() {
        return spread;
    }

    public void setSpread(long spread) {
        this.spread = spread;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
