package game.entity.MOB;

import engine.*;

import java.awt.*;
import java.util.ArrayList;

public class Boss extends Entity{

    private int directionX, directionY, pivotX, pivotY, damageIntervalCounter = 0, animationTicks = 0;
    private boolean triggered;

    final private int INACTIVE = 0;
    final private int LEFT_ATTACK = 1;
    final private int RIGHT_ATTACK = 2;
    final private int ZOMBIE_ATTACK = 3;
    final private int DEFEATED = 4;

    private int state = INACTIVE;

    private ArrayList<BossJavelin> bossJavelins = new ArrayList<>();

    private Color c = new Color(0xff0000);

    public Boss(int x, int y, int width, int height){
        super("boss", ObjectStatus.MOB, x, y, 0, 0, width, height,
                new Sprite(
                        "assets/imgs/boss.png",
                        919,
                        535,
                        5,
                        1,
                        10
                ),
                ObjectStatus.ONLY_ONE_ANIMATED
        );

        animationId = 0;
        triggered = false;
        //HP = width;

        init();
    }

    private void init(){
        /* INACTIVE AND SLEEP - LEFT*/
        sprite.addAnimation(0, new int[][]{ {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}});

        /* LEFT ATTACK */
        sprite.addAnimation(1, new int[][]{ {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {5, 1}, {5, 1}});

        /* INACTIVE AND SLEEP - RIGHT */
        sprite.addAnimation(2, new int[][]{ {4, 1}, {4, 1}, {4, 1}, {4, 1}, {4, 1}, {4, 1}, {4, 1}});

        /* RIGHT ATTACK */
        sprite.addAnimation(3, new int[][]{ {4, 1}, {1, 1}, {2, 1}, {3, 1}, {2, 1}, {2, 1}, {2, 1}});

        /* ZOMBIE ATTACK */
        sprite.addAnimation(4, new int[][]{ {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {4, 1}, {1, 1}});

        addHitBox(new HitBox(0, height-50, width, 50));

    }

    @Override
    public void update() {
        //super.update();

        if(hipotenusa() < 1500){

            if(state == INACTIVE && animationTicks == 0){
                state = LEFT_ATTACK;
                leftAttack();
                animationId = 1;
                animationTicks = 80;c = new Color(0x00ff00);
            }
            else if(state == LEFT_ATTACK && animationTicks == 0){
                state = RIGHT_ATTACK;
                rightAttack();
                animationId = 3;
                animationTicks = 80;c = new Color(0x0000ff);
            }
            else if(state == RIGHT_ATTACK && animationTicks == 0){
                animationId = 4;
                zombieAttack();
                state = ZOMBIE_ATTACK;
                animationTicks = 80;c = new Color(0xff00ff);
            }
            else if(state == ZOMBIE_ATTACK && animationTicks == 0){
                animationId = 0;

                animationTicks = 200;c = new Color(0xffff00);
                state = INACTIVE;
            }

        }
        else {
            state = INACTIVE;
            animationTicks = 0;
        }

        if(animationTicks > 0) animationTicks--;

    }

    private void leftAttack(){
        int angle = 225;
        for(int i = 0; i < 10; i++, angle += 18){
            bossJavelins.add(new BossJavelin(angle, x, y, 0, 0));
        }
    }

    private void rightAttack(){
        int angle = 315;
        for(int i = 0; i < 10; i++, angle -= 18){
            bossJavelins.add(new BossJavelin(angle, 0, 0, 0, 0));
        }
    }

    private void zombieAttack(){

    }

    @Override
    public void render() {
        super.render();
        //sprite.render(x, y, getXOffset(), getYOffset(), animationId, 1);
        //System.out.println("=============");
        //sprite.animate(x, y, getAbsoluteXOffset(), getAbsoluteYOffset(), 0);
        /* ISSO SÃO PRA SABER O ESTADO ATUAL DO BOSS*/
        RenderStrategy.getStrategy().setColor(c);
        RenderStrategy.getStrategy().drawRect(x, y+width, x+20, y+width+20);
    }


    private double hipotenusa(){
        return Math.sqrt( Math.pow( pivotX - getX(), 2 ) + Math.pow( pivotY - getY(), 2 ));
    }

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public int getPivotX() {
        return pivotX;
    }

    public void setPivotX(int pivotX) {
        this.pivotX = pivotX;
    }

    public int getPivotY() {
        return pivotY;
    }

    public void setPivotY(int pivotY) {
        this.pivotY = pivotY;
    }

    public int getDamageIntervalCounter() {
        return damageIntervalCounter;
    }

    public void setDamageIntervalCounter(int damageIntervalCounter) {
        this.damageIntervalCounter = damageIntervalCounter;
    }

    public int getAnimationTicks() {
        return animationTicks;
    }

    public void setAnimationTicks(int animationTicks) {
        this.animationTicks = animationTicks;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
}
