package game;

import engine.*;
import game.GUI.HUD;
import game.GUI.loja.Item;
import game.weaponry.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends GamePlayer {

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

    private double angle;

    public int hp = 50;
    public static double cash = 10000;
    public static  int kits = 5;

    private Weapon weapon;


    //HUD.getIndexArmaSelecionada retorna o index da arma selecionada, de 0 a 3, 0 : Pistol, 1 : Shotgun, 2 : subMachineGun, 3 : MachineGun

    private ArrayList<Weapon> weapons = new ArrayList<>();
    public static ArrayList<Integer> ArmasCompradas = new ArrayList<>();

    public Player(String name, int x, int y, int z, int width, int height, double hp, double mp, double stamina) {
        super(name, x, y, z, width, height, hp, mp, stamina);
        //step = new Song("assets/songs/cement-footstep.wav");
        init();

    }

    private void init(){


        sprite = new Sprite("assets/imgs/player.png", 60, 100, 3, 8, 3);
        sprite.addAnimation(UP_MOVING, new int[][]{ {3, 5}, {1, 5}, {2, 5}, {1, 5}});
        sprite.addAnimation(LEFT_MOVING, new int[][]{ {1, 2}, {2, 2}, {3, 2}, {2, 2}});
        sprite.addAnimation(DOWN_MOVING, new int[][]{ {1, 1}, {2, 1}, {3, 1}, {2, 1}});
        sprite.addAnimation(RIGHT_MOVING, new int[][]{ {1, 6}, {2, 6}, {3, 6}, {2, 6}});
        sprite.addAnimation(UP_LEFT_MOVING, new int[][]{ {1, 8}, {2, 8}, {3, 8}, {2, 8}});
        sprite.addAnimation(UP_RIGHT_MOVING, new int[][]{ {1, 4}, {2, 4}, {3, 4}, {2, 4}});
        sprite.addAnimation(DOWN_LEFT_MOVING, new int[][]{ {1, 3}, {2, 3}, {3, 3}, {2, 3}});
        sprite.addAnimation(DOWN_RIGHT_MOVING, new int[][]{ {1, 7}, {2, 7}, {3, 7}, {2, 7}});
        HUD.getPos(x,y);

        addHitBox(new HitBox(0, 75, width, 25));

        weapons.add(new Pistol(2, 0, width+4, height));
        weapon = weapons.get(0);

    }

    @Override
    public void update() {
        super.update();

        checkAnimationStatus();
        checkAngleStatus();
        checkWeaponChange();

        weapon.setDirection(Math.abs(direction));
        weapon.update();
        if(GameSystem.isLeftButtonPressed() && !HUD.isOpen) weapon.fire();


        //usar kit medico

        if(GameSystem.isSomeKeyPressed() && GameSystem.getKey() == KeyEvent.VK_F){       //f
            if(kits > 0) {
                if (hp < 100) {
                    hp += Item.getRegeneracao();
                    kits -= 1;
                    if(hp > 100)
                        hp = 100;
                }
            }
            GameSystem.setKey(0);
        }


    }

    @Override
    public void updateRelativeToScene(int xaxis, int yaxis) {
        if(HUD.isOpen) return;

        if(EventHandler.isUp() || EventHandler.isDown()){
            speedY = (int) (-12d * Math.sin(angle));
        }
        else{
            speedY = 0;
        }

        if(EventHandler.isLeft() || EventHandler.isRight()){
            speedX = (int) (12d * Math.cos(angle));
        }
        else{
            speedX = 0;
        }

        //System.out.println("x: " + x + ", y: " + y + ", spdX: " + speedX + ", spdY: " + speedY);
        //System.out.println("x: " + Camera.getX() + ", y: " + Camera.getY());
       // HUD.getPos(Camera.getX(),Camera.getY());

        super.updateRelativeToScene(xaxis, yaxis);    //System.out.println(Camera.getPlayerX()+"- "+Camera.getPlayerY()+" -"+Camera.getPlayerXOffset()+" = "+Camera.getPlayerYOffset());
        weapon.updateRelativeToScene(x, y);

    }

    @Override
    public void render() {
        super.render();


        weapon.render();


    }

    private void checkWeaponChange(){
        if(GameSystem.isSomeKeyPressed()){

            if(GameSystem.getKey() == KeyEvent.VK_1) weapon = weapons.get(0);

            else if( GameSystem.getKey() == KeyEvent.VK_2 && weapons.size() >= 2 ) weapon = weapons.get(1);

            else if( GameSystem.getKey() == KeyEvent.VK_3 && weapons.size() >= 3 ) weapon = weapons.get(2);

            else if( GameSystem.getKey() == KeyEvent.VK_4 && weapons.size() == 4) weapon = weapons.get(3);

        }
    }

    private double relativeCos(double r){
        return r * ( (GameSystem.getMouseX() - Camera.getX() ) / Math.sqrt( Math.pow( GameSystem.getMouseX() - Camera.getX() , 2) + Math.pow( GameSystem.getMouseY() - Camera.getY() , 2) ));
    }

    private double relativeSin(double r){
        return r * ( (GameSystem.getMouseY() - Camera.getY() ) / Math.sqrt( Math.pow( GameSystem.getMouseX() - Camera.getX() , 2) + Math.pow( GameSystem.getMouseY() - Camera.getY() , 2) ));
    }


    private double moveCos(double r){
        return r * ( (GameSystem.getMouseX() - Camera.getX() ) / Math.sqrt( Math.pow( GameSystem.getMouseX() - Camera.getX() , 2) + Math.pow( GameSystem.getMouseY() - Camera.getY() , 2) ));
    }

    private double moveSin(double r){
        return r * ( (GameSystem.getMouseY() - Camera.getY() ) / Math.sqrt( Math.pow( GameSystem.getMouseX() - Camera.getX() , 2) + Math.pow( GameSystem.getMouseY() - Camera.getY() , 2) ));
    }


    private double cosmod(double t){
        return Math.cos(t - Math.PI/8);
    }

    private double sinmod(double t){
        return Math.sin(t - Math.PI/8);
    }

    private int centerX(){
        return ( x + width/2 );
    }

    private int centerY(){
        return ( y + height/2 );
    }

    private void checkAngleStatus(){
        if(HUD.isOpen) return;
        if(EventHandler.isUp() && EventHandler.isRight()){
            angle = Math.PI/4;
        }
        else if(EventHandler.isUp() && EventHandler.isLeft()){
            angle = 3*Math.PI/4;
        }
        else if(EventHandler.isUp()){
            angle = Math.PI/2;
        }
        else if(EventHandler.isDown() && EventHandler.isRight()){
            angle = 7*Math.PI/4;
        }
        else if(EventHandler.isDown() && EventHandler.isLeft()){
            angle = 5*Math.PI/4;
        }
        else if(EventHandler.isDown()){
            angle = 3*Math.PI/2;
        }
        else if(EventHandler.isLeft()){
            angle = Math.PI;
        }
        else if(EventHandler.isRight()){
            angle = 2*Math.PI;
        }//
    }

    private void checkAnimationStatus(){
        if(HUD.isOpen) return;
        if(!GameSystem.isLeftButtonPressed() && EventHandler.isUp() && EventHandler.isRight()){
            direction = UP_RIGHT_MOVING;
            //angle = Math.PI/4;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isUp() && EventHandler.isLeft()){
            direction = UP_LEFT_MOVING;
            //angle = 3*Math.PI/4;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isUp()){
            direction = UP_MOVING;
            //angle = Math.PI/2;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isDown() && EventHandler.isRight()){
            direction = DOWN_RIGHT_MOVING;
            //angle = 7*Math.PI/4;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isDown() && EventHandler.isLeft()){
            direction = DOWN_LEFT_MOVING;
            //angle = 5*Math.PI/4;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isDown()){
            direction = DOWN_MOVING;
            //angle = 3*Math.PI/2;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isLeft()){
            direction = LEFT_MOVING;
            //angle = Math.PI;
        }
        else if(!GameSystem.isLeftButtonPressed() && EventHandler.isRight()){
            direction = RIGHT_MOVING;
            //angle = 2*Math.PI;
        }//                                                          left                                  right
        else if(GameSystem.getMouseY() < Camera.getY() && relativeCos(1) > cosmod(3*Math.PI/4) && relativeCos(1) < cosmod(Math.PI/2)){
            if(!GameSystem.isSomeKeyPressed()) direction = UP_STOPPED;
            else direction = UP_MOVING;
            //angle = Math.PI/2;
        }
        else if(GameSystem.getMouseY() < Camera.getY() && relativeCos(1) > cosmod(Math.PI/2) && relativeCos(1) < cosmod(Math.PI/4)){
            if(!GameSystem.isSomeKeyPressed()) direction = UP_RIGHT_STOPPED;
            else direction = UP_RIGHT_MOVING;
            //angle = Math.PI/4;
        }
        else if(GameSystem.getMouseY() < Camera.getY() && relativeCos(1) > cosmod(Math.PI) && relativeCos(1) < cosmod(3*Math.PI/4)){
            if(!GameSystem.isSomeKeyPressed()) direction = UP_LEFT_STOPPED;
            else direction = UP_LEFT_MOVING;
            //angle = 3*Math.PI/4;
        }
        else if(GameSystem.getMouseY() > Camera.getY() && relativeCos(1) > cosmod(3*Math.PI/2) && relativeCos(1) < cosmod(7*Math.PI/4)){
            if(!GameSystem.isSomeKeyPressed()) direction = DOWN_STOPPED;
            else direction = DOWN_MOVING;
            //angle = 3*Math.PI/2;
        }
        else if(GameSystem.getMouseY() > Camera.getY() && relativeCos(1) > cosmod(7*Math.PI/4) && relativeCos(1) < cosmod(2*Math.PI)){
            if(!GameSystem.isSomeKeyPressed()) direction = DOWN_RIGHT_STOPPED;
            else direction = DOWN_RIGHT_MOVING;
            //angle = 7*Math.PI/4;
        }
        else if(GameSystem.getMouseY() > Camera.getY() && relativeCos(1) > cosmod(5*Math.PI/4) && relativeCos(1) < cosmod(3*Math.PI/2)){
            if(!GameSystem.isSomeKeyPressed()) direction = DOWN_LEFT_STOPPED;
            else direction = DOWN_LEFT_MOVING;
            //angle = 5*Math.PI/4;
        }
        else if(GameSystem.getMouseX() < Camera.getX()){
            if(!GameSystem.isSomeKeyPressed()) direction = LEFT_STOPPED;
            else direction = LEFT_MOVING;
            //angle = Math.PI;
        }
        else if(GameSystem.getMouseX() > Camera.getX()){
            if(!GameSystem.isSomeKeyPressed()) direction = RIGHT_STOPPED;
            else direction = RIGHT_MOVING;
            //angle = 2*Math.PI;
        }
        else{
            if(direction > 0) direction *= -1;
        }
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void buyWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getKits() {
        return kits;
    }

    public void setKits(int kits) {
        this.kits = kits;
    }

    public int getCurrentWeaponID(){
        return weapons.indexOf(weapon);
    }
}
