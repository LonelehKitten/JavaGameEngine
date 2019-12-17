package game.entity.MOB;

import engine.*;
import game.GUI.HUD;
import game.weaponry.Bullet;

import java.awt.*;

public class Zombie extends Entity {

    public final static int UP_MOVING = 5;
    public final static int DOWN_MOVING = 1;
    public final static int LEFT_MOVING = 2;
    public final static int RIGHT_MOVING = 6;
    public final static int UP_LEFT_MOVING = 8;
    public final static int UP_RIGHT_MOVING = 4;
    public final static int DOWN_LEFT_MOVING = 3;
    public final static int DOWN_RIGHT_MOVING = 7;
    public final static int UP_STOPPED = -5;
    public final static int DOWN_STOPPED = -1;
    public final static int LEFT_STOPPED = -2;
    public final static int RIGHT_STOPPED = -6;
    public final static int UP_LEFT_STOPPED = -8;
    public final static int UP_RIGHT_STOPPED = -4;
    public final static int DOWN_LEFT_STOPPED = -3;
    public final static int DOWN_RIGHT_STOPPED = -7;

    private int directionX, directionY, pivotX, pivotY, damageIntervalCounter = 0;
    private boolean triggered;

    protected int damage, hp;
    protected double acceleration;

    private Color lifecolor = new Color(0xff0000);
    private Stroke lifebar = new BasicStroke(2);

    public Zombie(int id, int x, int y, int width, int height, String spritePath){
        super("zombie"+id, ObjectStatus.MOB, x, y, 0, 0, width, height,
                new Sprite(
                        spritePath,
                        60,
                        100,
                        3,
                        8,
                        3
                ),
                ObjectStatus.ONLY_ONE_ANIMATED
        );

        animationId = 1;
        triggered = false;
        //HP = width;

        init();
    }

    private void init(){

        sprite.addAnimation(5, new int[][]{ {3, 5}, {1, 5}, {2, 5}, {1, 5}});
        sprite.addAnimation(2, new int[][]{ {1, 2}, {2, 2}, {3, 2}, {2, 2}});
        sprite.addAnimation(1, new int[][]{ {1, 1}, {2, 1}, {3, 1}, {2, 1}});
        sprite.addAnimation(6, new int[][]{ {1, 6}, {2, 6}, {3, 6}, {2, 6}});
        sprite.addAnimation(8, new int[][]{ {1, 8}, {2, 8}, {3, 8}, {2, 8}});
        sprite.addAnimation(4, new int[][]{ {1, 4}, {2, 4}, {3, 4}, {2, 4}});
        sprite.addAnimation(3, new int[][]{ {1, 3}, {2, 3}, {3, 3}, {2, 3}});
        sprite.addAnimation(7, new int[][]{ {1, 7}, {2, 7}, {3, 7}, {2, 7}});

        addHitBox(new HitBox(0, 75, width, 25));

    }

    @Override
    public void update() {
        super.update();

        //HP = hp*100/width;

        if(damageIntervalCounter > 0) damageIntervalCounter--;

        if(!triggered && hipotenusa() < 500 && hipotenusa() > 80) triggered = true;

        if(triggered){

            angle();

            speedX = (int) (relativeCos(10) * acceleration);
            speedY = (int) (relativeSin(10) * acceleration);

            if(hipotenusa() > 700 || hipotenusa() <= 80){
                triggered = false;
                speedX = 0;
                speedY = 0;
            }

        }
    }

    @Override
    public void render() {

        if(triggered) super.render();
        else
            sprite.render(getAbsoluteX(), getAbsoluteY(), getAbsoluteXOffset(), getAbsoluteYOffset(), 2, animationId);

        RenderStrategy.getStrategy().setStroke(lifebar);
        RenderStrategy.getStrategy().setColor(lifecolor);
        RenderStrategy.getStrategy().drawLine(RenderStrategy.getXOf(x), RenderStrategy.getYOf(y-10), RenderStrategy.getXOf(x+hp), RenderStrategy.getYOf(y-10));
        RenderStrategy.getStrategy().drawString(""+hp, RenderStrategy.getXOf(x+hp), RenderStrategy.getYOf(y-10));
    }

    private void angle(){

        if(pivotY < getY() && relativeCos(1) > cosmod(3*Math.PI/4) && relativeCos(1) < cosmod(Math.PI/2)){
            animationId = UP_MOVING;
        }
        else if(pivotY < getY() && relativeCos(1) > cosmod(Math.PI/2) && relativeCos(1) < cosmod(Math.PI/4)){
            animationId = UP_RIGHT_MOVING;
        }
        else if(pivotY < getY() && relativeCos(1) > cosmod(Math.PI) && relativeCos(1) < cosmod(3*Math.PI/4)){
            animationId = UP_LEFT_MOVING;
        }
        else if(pivotY > getY() && relativeCos(1) > cosmod(3*Math.PI/2) && relativeCos(1) < cosmod(7*Math.PI/4)){
            animationId = DOWN_MOVING;
        }
        else if(pivotY > getY() && relativeCos(1) > cosmod(7*Math.PI/4) && relativeCos(1) < cosmod(2*Math.PI)){
            animationId = DOWN_RIGHT_MOVING;
        }
        else if(pivotY > getY() && relativeCos(1) > cosmod(5*Math.PI/4) && relativeCos(1) < cosmod(3*Math.PI/2)){
            animationId = DOWN_LEFT_MOVING;
        }
        else if(pivotX < getX()){
            animationId = LEFT_MOVING;
        }
        else if(pivotX > getX()){
            animationId = RIGHT_MOVING;
        }

    }

    private double cosmod(double t){
        return Math.cos(t - Math.PI/8);
    }

    private double sinmod(double t){
        return Math.sin(t - Math.PI/8);
    }

    private double relativeCos(double r){
        return r * ( (pivotX - getX() ) / Math.sqrt( Math.pow( pivotX - getX() , 2) + Math.pow( pivotY - getY() , 2) ));
    }

    private double relativeSin(double r){
        return r * ( (pivotY - getY() ) / Math.sqrt( Math.pow( pivotX - getX() , 2) + Math.pow( pivotY - getY() , 2) ));
    }

    private double hipotenusa(){
        return Math.sqrt( Math.pow( pivotX - getX(), 2 ) + Math.pow( pivotY - getY(), 2 ));
    }

    public boolean wasHitBy(Bullet bullet, int damage){

        double tan =  /*((double) bullet.sinOfRange(100) / (double) bullet.cosOfRange(100)  );*/ ( (bullet.getY() - bullet.getPivotY() ) / Math.sqrt( Math.pow( bullet.getX() - bullet.getPivotX() , 2) + Math.pow( bullet.getY() - bullet.getPivotY() , 2) )) / ( ( bullet.getX() - bullet.getPivotX() ) / Math.sqrt( Math.pow( bullet.getX() - bullet.getPivotX() , 2) + Math.pow( bullet.getY() - bullet.getPivotY() , 2) ));
        double xyTan = ( (X - bullet.getPivotY() ) / Math.sqrt( Math.pow( X - bullet.getPivotX() , 2) + Math.pow( Y - bullet.getPivotY() , 2) )) / ( ( X - bullet.getPivotX() ) / Math.sqrt( Math.pow( X - bullet.getPivotX() , 2) + Math.pow( Y - bullet.getPivotY() , 2) ));
        double xOffyOffTan = ( (getYOffset() - bullet.getPivotY() ) / Math.sqrt( Math.pow( getXOffset() - bullet.getPivotX() , 2) + Math.pow( getYOffset() - bullet.getPivotY() , 2) )) / ( ( getXOffset() - bullet.getPivotX() ) / Math.sqrt( Math.pow( getXOffset() - bullet.getPivotX() , 2) + Math.pow( getYOffset() - bullet.getPivotY() , 2) ));

        System.out.println("tan:    " + tan + "xyTan:    " + xyTan + "xOffyOffTan:     " + xOffyOffTan);

        if(tan < xyTan && tan > xOffyOffTan){
            triggered = true;
            hp -= damage;
            return true;

        }

        return false;
    }

    public boolean causingDamage(){
        return damageIntervalCounter == 0 && hipotenusa() <= 80;
    }

    public int getDamage(){
        damageIntervalCounter = 20;
        return damage;
    }

    public boolean isNextEnough(){
        return hipotenusa() < 800;
    }

    private int deltaX(int p){
        return X - p;
    }

    private int deltaY(int p){
        return Y - p;
    }

    private int deltaXOff(int p){
        return X - p;
    }

    private int deltaYOff(int p){
        return Y - p;
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

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }
}
