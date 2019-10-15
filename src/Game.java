import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Game extends Canvas {

    private BufferStrategy bufferStrategy;

    private Player player = new Player(
            (IConfig.MONITOR_WIDTH/2)-16,
            (IConfig.MONITOR_HEIGHT/2)-16,
            0,
            32,
            32,
            100,
            100,
            100,
            new Color(0, 0, 255)
            );
    private Song song = new Song("assets/songs/Zayan-track.wav");

    private GameLoop gameloop = new GameLoop(this);
    private EventHandler eventHandler = new EventHandler();

    private Camera camera = new Camera();

    private Place place;



    public Game(){
        init();
    }

    private void init(){
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0, IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);
        this.setKeyEvents();

        this.createPlaces();

        song.play(0);

        gameloop.start();
    }


    public void createPlaces(){

        Place it, aux;
        place = new Place("Zayan Village", ObjectStatus.VILLAGE);

        Gate gate;

        // Zayan Village

        it = place;

        //Sprite sea = new Sprite("assets/imgs/sea-border-tile.png", 64, 64, 8, 1, 8);
        //sea.addAnimation(0, new int[][]{{1,1}, {2,1}, {3,1}, {4,1}, {5,1}, {6,1}, {7,1}, {8,1}});
        //sea.addAnimation(1, new int[][]{{1,2}});

        it.addScene("inside", 0, -300);
        it.getScene("inside")
                .setCurrent(true)

                //.addSolid("background-3", -2000, -2000, 4000, 4000, new Sprite("assets/imgs/grass-tile.png"), ObjectStatus.TILED, false)
                //.addSolid("background-2", -2000, 300, 4000, 4000, new Sprite("assets/imgs/sand-tile.png"), ObjectStatus.TILED, false)
                //.addSolid("background-1", -2000, 650, 4000, 4000, sea, ObjectStatus.TILED_ANIMATED, false)

                .addSolid("north-limit", -800, -800, 1600, 100, new Color(0, 0, 0, 0))
                .addSolid("south-limit", -800, 700, 1600, 100, new Color(0, 0, 0, 0))
                .addSolid("west-limit", -800, -800,  100, 1600, new Color(0, 0, 0, 0))
                .addSolid("east-limit", 700, -800,  100, 1600, new Color(0, 0, 0, 0))
                .addSolid("a whatever", -600, -600, 256, 256, new Color(0xffaa00))

                .addSolid("house-1", -600, -500,  500, 350, new Color(0xff0000))
                .addSolid("house-2", 250, -500,  350, 500, new Color(0xff0000))
                .addSolid("house-3", -600, 50,  350, 500, new Color(0xff0000))


                .addSolid("main-gate", -50, -725, 100, 50, new Color(0x00ff00), false)
                .addEntity("Max", ObjectStatus.NPC, ObjectStatus.HUMAN, ObjectStatus.BLACKSMITH, 210,-100,32,32, new Color(0xB46000))
        ;


        it.addPath(new Place("Yellow Grassland", ObjectStatus.MEADOW));
        aux = it.getPath("Yellow Grassland");
        aux.addPath(it);

        gate = new Gate(new String[]{"Zayan Village", "inside"},new String[]{"Yellow Grassland", "grasslands"});//, ObjectStatus.PLACE);

        it.getScene("inside").getSolid("main-gate").addGate(gate);

        it = aux;

        it.addScene("grasslands", 0, -400);
        it.getScene("grasslands")
                .addSolid("north-limit", -950, -700, 1900, 100, new Color(0x009933))
                .addSolid("south-limit", -950, 600, 1900, 100, new Color(0x009933))
                .addSolid("west-limit", -950, -700, 100, 1400, new Color(0x009933))
                .addSolid("east-limit", 850, -700, 100, 1400, new Color(0x009933))

                .addSolid("zayan-gate", -50, 575, 100, 50, new Color(0x00ff00), false)
        ;

        it.getScene("grasslands").getSolid("zayan-gate").addGate(gate);


        Gate.now[0] = "Zayan Village";
        Gate.now[1] = "inside";
        Gate.going_to[0] = null;
        Gate.going_to[1] = null;
    }


    public void setKeyEvents(){
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                        eventHandler.setUp(true); player.setDirection(1);
                        break;
                    case KeyEvent.VK_A:
                        eventHandler.setLeft(true); player.setDirection(2);
                        break;
                    case KeyEvent.VK_S:
                        eventHandler.setDown(true); player.setDirection(3);
                        break;
                    case KeyEvent.VK_D:
                        eventHandler.setRight(true); player.setDirection(4);
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                        eventHandler.setUp(false);
                        break;
                    case KeyEvent.VK_A:
                        eventHandler.setLeft(false);
                        break;
                    case KeyEvent.VK_S:
                        eventHandler.setDown(false);
                        break;
                    case KeyEvent.VK_D:
                        eventHandler.setRight(false);
                        break;

                }
            }
        });
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
                player.setX(300 - player.getWidth());
            }
        }
        */

        player.update();
        place.update();
        checkPlaceStatus();
    }

    private void checkPlaceStatus(){
        if(Gate.going_to[0] != null){

            if(place.getName().equals(Gate.going_to[0])){
                place.getScene(Gate.now[1]).setCurrent(false);
                place.getScene(Gate.going_to[1]).enter();
            }
            else{
                place.getScene(Gate.now[1]).setCurrent(false);
                place = place.getPath(Gate.going_to[0]);
                place.getScene(Gate.going_to[1]).enter();
            }

            Gate.now[0] = Gate.going_to[0];
            Gate.now[1] = Gate.going_to[1];

            Gate.going_to[0] = null;
            Gate.going_to[1] = null;

        }
    }

    public void render(){
        
        if(bufferStrategy == null){
          createBufferStrategy(3);
          //return;
        }

        bufferStrategy = getBufferStrategy();

        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);

        place.render(g2d);
        player.render(g2d);

        g2d.dispose();
        if(!bufferStrategy.contentsLost()) bufferStrategy.show();
    }
}
