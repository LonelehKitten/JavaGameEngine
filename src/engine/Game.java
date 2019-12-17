package engine;

import game.GUI.MenuInicial.Menu;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas{

    private BufferStrategy bufferStrategy;

    //private engine.Song song = new engine.Song("assets/songs/Zayan-track.wav");

    private GameLoop gameloop = new GameLoop(this);
    private EventHandler eventHandler = new EventHandler(this);
    private RenderStrategy renderStrategy = new RenderStrategy(this, 200);

    protected Camera camera;

    protected Place place;

    private int sceneChangeInterval;

    //
    //private Menu menu;
    //

    public void init(){
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0, IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);
        eventHandler.setKeyEvents();
        eventHandler.setMouseEvents();

        this.createGame();

        //song.play(0);

        this.sceneChangeInterval = 2;
        gameloop.start();
        //menu = new Menu();
    }


    protected void createGame(){

    }

    public void update(){

        /*
        if (eventHandler.isUp()) {
            if(!place.isCollided(player.getX(), player.getY()-10, player.getXOffset(), player.getYOffset()-10))
                player.setY(player.getY() - 10);
            else{
                player.setY(600);
            }

        }

        if (eventHandler.isDown()) {
            if(!place.isCollided(player.getX(), player.getY()+10, player.getXOffset(), player.getYOffset()+10))
                player.setY(player.getY() + 10);
            else{
                player.setY(300 - player.getHeight());
            }
        }

        if (eventHandler.isLeft()) {
            if(!place.isCollided(player.getX()-10, player.getY(), player.getXOffset()-10, player.getYOffset()))
                player.setX(player.getX() - 10);
            else{
                player.setX(600);
            }
        }

        if (eventHandler.isRight()) {
            if (!place.isCollided(player.getX()+10, player.getY(), player.getXOffset()+10, player.getYOffset()))
                player.setX(player.getX() + 10);
            else{
                player.setX(300 - play.setHud(hud)er.getWidth());
            }
        }
        */

        if(place != null) place.update();

        if(sceneChangeInterval > 0) sceneChangeInterval--;
        //menu.update();

    }

    protected void checkPlaceStatus(SceneObject obj){
        if(Gate.going_to[0] != null){
            System.out.println("checkPlaceStatus");
            if(place.getName().equals(Gate.going_to[0])){
                System.out.println(1);
                place.getScene(Gate.now[1]).setCurrent(false);
                place.getScene(Gate.going_to[1]).enter(obj);
            }
            else{
                System.out.println(2);
                place.getScene(Gate.now[1]).setCurrent(false);
                place = place.getPath(Gate.going_to[0]);
                place.getScene(Gate.going_to[1]).enter(obj);
            }

            Gate.now[0] = Gate.going_to[0];
            Gate.now[1] = Gate.going_to[1];

            Gate.going_to[0] = null;
            Gate.going_to[1] = null;

            sceneChangeInterval = 2;

        }

    }

    public void render(){

        if(bufferStrategy == null){
            createBufferStrategy(3);
        }

        bufferStrategy = getBufferStrategy();
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        if(sceneChangeInterval == 0) renderStrategy.render(this, g2d);

        g2d.dispose();
        if(!bufferStrategy.contentsLost()) bufferStrategy.show();

        //menu.render();
    }

    public Place getPlace(){
        return place;
    }

}
