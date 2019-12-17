package game.GUI;

import engine.*;
import game.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class HUD implements InteractiveObject {
    private int hp = 100;
    private double cash = 500;
    //posicoes do HP
    private int xHP = 10;
    private int yHP = 10;
    private int widthHP = 100;
    private int heightHP = 20;

    //posicoes do box pontuacao players
    private int xBOX = IConfig.MONITOR_WIDTH - 280;
    private int yBOX = 10;
    private int widthBOX = 200;
    private int heightBOX = 100;

    //dinheiro
    private ImageIcon img  = new ImageIcon(getClass().getResource("cash.png"));
    private Image image = img.getImage().getScaledInstance(55, 50, 0);
    private int xCash = 10;
    private int yCash = 40;

    //medkit
    private int qtdMedKit = 5;
    private Image medKit = new ImageIcon(getClass().getResource("medkit.png")).getImage().getScaledInstance(100, 57, 0);

    //icones armas
    private Image []guns;
    private static int index = 0;


    //??????????????????????????????
    public static boolean isOpen=false;
    private boolean lock=false;
    public static void setOpen(boolean value){isOpen = value;}

    private Player player;

    public HUD(Player player){
        this.player = player;
        guns = new Image[4];
        try{
            guns[0] = new ImageIcon(getClass().getResource("loja/guns/Pistol.png")).getImage().getScaledInstance(100, 57, 0);
            guns[1] = new ImageIcon(getClass().getResource("loja/guns/Shotgun.png")).getImage().getScaledInstance(100, 57, 0);
            guns[2] = new ImageIcon(getClass().getResource("loja/guns/SubMachineGun.png")).getImage().getScaledInstance(100, 57, 0);
            guns[3] = new ImageIcon(getClass().getResource("loja/guns/MachineGun.png")).getImage().getScaledInstance(100, 57, 0);
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar a imagem");
        }
    }

    @Override
    public void update() {
        hp = player.getHp();
        cash = player.getCash();
        qtdMedKit = player.getKits();
        int i;
        if(GameSystem.isSomeKeyPressed()) {
            if (!lock) {
                if (GameSystem.getKey() == KeyEvent.VK_Q) {         //q
                    index--;
                    if (index < 0)
                        index = Player.ArmasCompradas.size();

                } else if (GameSystem.getKey() == KeyEvent.VK_E) {    //e
                    index++;
                    if (index > Player.ArmasCompradas.size())
                        index = 0;

                }
            }
            lock = true;
        }else{
            lock = false;
        }
    }

    @Override
    public void render(){
        renderHP();
        //renderBOX();
        renderCash();
        renderMedKit();
        renderIconeArma();
    }

    public void renderHP(){
        /*  HP PLAYER    */
        RenderStrategy.getStrategy().setFont(new Font("",Font.PLAIN, 12));
        //HP
        RenderStrategy.getStrategy().setColor(Color.getHSBColor((float) (((hp * 1.2))/360.0f), 1.0f, 1.0f));
        RenderStrategy.getStrategy().fillRect(xHP, yHP, hp*3, heightHP);    //*3 pra ficar maior na tela

        //borda
        RenderStrategy.getStrategy().setColor(Color.black);
        RenderStrategy.getStrategy().drawRect(xHP, yHP, widthHP*3, heightHP);   //*3 pra ficar maior na tela

        //string
        RenderStrategy.getStrategy().setColor(Color.black);
        RenderStrategy.getStrategy().drawString(hp + "/100", (int) (xHP + (hp*3)/2.5), yHP + 15);  //baita gambiarra pra string ficar no meio ficar no meio

    }

    public void renderBOX(){
        /*  BOX pontuacoes    */
        RenderStrategy.getStrategy().setFont(new Font("",Font.PLAIN, 12));
        //borda
        RenderStrategy.getStrategy().setColor(Color.black);
        RenderStrategy.getStrategy().drawRect(xBOX, yBOX, widthBOX, heightBOX);

        //players e pontuacao
        RenderStrategy.getStrategy().setColor(Color.blue);
        RenderStrategy.getStrategy().drawString("player 1:     100 pts", xBOX + 5, yBOX + 15);
        RenderStrategy.getStrategy().drawString("player 2:     -5 pts", xBOX + 5, yBOX + 30);

    }

    public void renderCash(){
        RenderStrategy.getStrategy().setFont(new Font("",Font.BOLD, 30));
        RenderStrategy.getStrategy().setColor(Color.black);
        //imagem
        RenderStrategy.getStrategy().drawImage(image, xCash, yCash, null);
        //string
        RenderStrategy.getStrategy().drawString("" + cash, xCash + 60, yCash + 40);

        //RenderStrategy.getStrategy().drawString("X: "+(int)(Camera.getX()-getX)+"  Y: "+(int)(Camera.getY()-getY), xCash, yCash + 80);
        // + 60 - +70
        RenderStrategy.getStrategy().drawString("X: " + (Camera.getCameraCollisionFactor().getX2()) + ", Y: " + (Camera.getCameraCollisionFactor().getY2()), xCash, yCash+80);
    }

    public void renderMedKit(){
        //image
        //Image medKit = new ImageIcon(getClass().getResource("firstaid.png")).getImage().getScaledInstance(100, 57, 0);
        RenderStrategy.getStrategy().drawImage(medKit, IConfig.MONITOR_WIDTH-100, IConfig.MONITOR_HEIGHT/2 - 42, null);

        //qtdMedKit
        RenderStrategy.getStrategy().setColor(Color.black);
        RenderStrategy.getStrategy().drawString("" + qtdMedKit, IConfig.MONITOR_WIDTH-50, IConfig.MONITOR_HEIGHT/2 + 20);
    }

    public void renderIconeArma(){
        RenderStrategy.getStrategy().drawImage(guns[player.getCurrentWeaponID()], 200, yCash, null);
    }
    public static int getIndexArmaSelecionada(){ return index; }

    private static int getX;
    private static int getY;

    public static void getPos(int getX,int getY){
        HUD.getX = getX;
        HUD.getY = getY;
    }

    public String getName(){
        return "GUI";
    }
}