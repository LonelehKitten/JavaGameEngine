package game;

import engine.*;
import java.awt.*;

import game.GUI.HUD;
import game.GUI.MenuInicial.Menu;
import game.GUI.loja.StoreInterface;
import game.room.*;

import javax.sound.sampled.Clip;

public class TheLastCure extends Game {

    private Player player = new Player(
            "player",
            0,
            0,
            0,
            60,
            100,
            100,
            100,
            100
    );

    private Center center;// = new Center("Hall",0,0);
    private Left left;// = new Left("Praça",0,0);
    private Top top;// = new Top("Laboratorio",0,0);
    private Bottom bottom;// = new Bottom("Elevador", 0, 0);
    private Right right;// = new Right("Right", 0, 0);
    private Seller seller;//new Seller("Seller", 0, 0);
    private Menu menu;
    /**
     *          ESPAÇO RESERVADO PARA AS IDEIAS DO JOGO
     *
     *
     *     HISTORIA:
     *    $PROJECT_DIR$/out
     *
     *
     *     MECANICA:
     *
     *          Mira com o mouse e atira com botão esquerdo.
     *
     *     METAS:
     *
     *          [PRONTO] Tiros: (Classe, mecanica) Se guiam por x, y pivos proprios.
     *          Loja de equipamentos: Vende itens como armas, bandagens, acessórios(?) .
     *          Tem um NPC [Entity] , é uma casa no cenario com uma gate para o cenario da parte interna da loja.
     *
     *
     *
     *     SUGESTÕES:
     *
     *
     *
     */

    final private int MENU = 0;
    final private int PLAY = 1;
    final private int INGAME = 2;

    private int gameState;

    private Song soundtrack = new Song("assets/songs/soundtrack.wav");

    public TheLastCure(){

        this.gameState = MENU;

        this.camera = new Camera(new CameraCollisionFactor() {
            @Override
            public int getX() {
                return player.getAbsoluteX()+player.getWidth()/2;
            }

            @Override
            public int getY() {
                return player.getAbsoluteY()+player.getWidth()/2;
            }

            @Override
            public int getXOffset() {
                return player.getAbsoluteXOffset()+player.getHeight()/2;
            }

            @Override
            public int getYOffset() {
                return player.getAbsoluteYOffset()+player.getHeight()/2;
            }

            @Override
            public int getX2() {
                return player.getX();
            }

            @Override
            public int getY2() {
                return player.getY();
            }
        });

    }

    @Override
    protected void createGame(){

        menu = new Menu();

        RenderStrategy.setRenderSpecialAddition(() -> {
            if(gameState == MENU) menu.render();
        });

        soundtrack.play(Clip.LOOP_CONTINUOUSLY);

    }

    @Override
    public void update() {
        super.update();

        if(gameState == MENU){

            menu.update();

            if(menu.play()){
                gameState = PLAY; System.out.println("preupdate2");
            }

        }
        else if(gameState == PLAY){System.out.println("preupdate1");
            initGame();
            gameState = INGAME;
        }
        else{
            checkPlaceStatus(player);
        }


        System.out.println("update");
    }

    private void initGame(){

        center = new Center("Hall",0,0);
        left = new Left("Praça",0,0);
        top = new Top("Laboratorio",0,0);
        bottom = new Bottom("Elevador", 0, 0);
        right = new Right("Right", 0, 0);

        place = new Place("Shopping", ObjectStatus.VILLAGE);

        Gate gate, gate2, gate3, gate4, gate5;
        Solid gateCenterLeft = new Solid("center-to-left-gate", -1493, -150, 0, 0, 100, 650, new Color(0, 0, 0, 0), false);
        Solid gateLeftCenter = new Solid("left-to-center-gate", 1897, -150, 0, 0, 100, 650, new Color(0, 0, 0, 0), false);

        Solid gateCenterTop = new Solid("center-to-top-gate", -85, -800, 0, 0, 560, 50, new Color(0, 0, 0, 0), false);
        Solid gateTopCenter = new Solid("top-to-center-gate", 0, 980, 0, 0, 600, 50, new Color(0, 0, 0, 0), false);

        Solid gateCenterBottom = new Solid("center-to-bottom-gate", -95, 575, 0, 0, 600, 50, new Color(0, 0, 0, 0), false);
        Solid gateBottomCenter = new Solid("bottom-to-center-gate", -50, -185, 0, 0, 99, 138, new Color(0, 0, 0, 0), false);

        Solid gateCenterRight = new Solid("center-to-right-gate", 1887, -100, 0, 0, 50, 650, new Color(0, 0, 0, 0), false);
        Solid gateRightCenter = new Solid("right-to-center-gate", -1493, -150, 0, 0, 50, 650, new Color(0, 0, 0, 0), false);


        Solid gateRightSaller = new Solid("right-to-saller-gate", 560, -880, 0, 0, 50, 150, new Color(0, 0, 0, 0), false);
        Solid gateSallerRight = new Solid("saller-to-right-gate", 150, 60, 0, 0, 100, 30, new Color(0, 0, 0, 0), false);

        StoreInterface storeInterface = new StoreInterface(player);

        HUD hud = new HUD(player);

        seller = new Seller("Seller", 0, 0, storeInterface);

        place.addScene(center);
        place.addScene(left);
        place.addScene(top);
        place.addScene(bottom);
        place.addScene(seller);
        place.addScene(right);


        center
                .setHud(hud)
                .addSolid(gateCenterLeft, true)
                .addSolid(gateCenterTop, true)
                .addSolid(gateCenterBottom, true)
                .addSolid(gateCenterRight, true);



        left.setHud(hud).addSolid(gateLeftCenter, true);
        top.setHud(hud).addSolid(gateTopCenter, true);

        right
                .setHud(hud)
                .addSolid(gateRightCenter, true)
                .addSolid(gateRightSaller, true);

        seller.addSolid(gateSallerRight, true);
        bottom
                .setCurrent(true)
                .setHud(hud)
                .addSolid((SceneObject) player, true)
                .addSolid(gateBottomCenter, true);

        center.setPlayer(player);
        left.setPlayer(player);
        right.setPlayer(player);
        top.setPlayer(player);

        gate = new Gate(new String[]{"Shopping", "Hall"}, new String[]{"Shopping", "Praça"}, gateCenterLeft, gateLeftCenter);

        gateCenterLeft.addGate(gate);
        gateLeftCenter.addGate(gate);

        gate2 = new Gate(new String[]{"Shopping", "Hall"}, new String[]{"Shopping", "Laboratorio"}, gateCenterTop, gateTopCenter);

        gateCenterTop.addGate(gate2);
        gateTopCenter.addGate(gate2);

        gate3 = new Gate(new String[]{"Shopping", "Hall"}, new String[]{"Shopping", "Elevador"}, gateCenterBottom, gateBottomCenter);

        gateCenterBottom.addGate(gate3);
        gateBottomCenter.addGate(gate3);

        gate4 = new Gate(new String[]{"Shopping", "Hall"}, new String[]{"Shopping", "Right"}, gateCenterRight, gateRightCenter);

        gateCenterRight.addGate(gate4);
        gateRightCenter.addGate(gate4);

        gate5 = new Gate(new String[]{"Shopping", "Right"}, new String[]{"Shopping", "Seller"}, gateRightSaller, gateSallerRight);

        gateRightSaller.addGate(gate5);
        gateSallerRight.addGate(gate5);

        Gate.now[0] = "Shopping";
        Gate.now[1] = "Elevador";
        Gate.going_to[0] = null;
        Gate.going_to[1] = null;

    }


    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow( new TheLastCure() );
    }

}
