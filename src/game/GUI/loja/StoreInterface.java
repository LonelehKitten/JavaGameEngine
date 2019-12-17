package game.GUI.loja;

import engine.*;
import game.GUI.HUD;
import game.Player;
import game.weaponry.MachineGun;
import game.weaponry.Shotgun;
import game.weaponry.SubMachineGun;
import game.weaponry.Weapon;

import java.awt.*;
import java.util.ArrayList;


public class StoreInterface implements InteractiveObject {
    public static Item[] itens;
    private Item itemCheck = null;
    private int qtd;
    private int x;
    private int y;
    private int height;
    private int width;
    private int tamItem;
    private boolean lock = false;

    private ArrayList<Weapon> weaponsForSale = new ArrayList<>();

    private Player player;

    public StoreInterface(Player player){

        this.player = player;

        qtd = 4;
        tamItem = 150;

        x = IConfig.MONITOR_WIDTH/2 - (qtd * tamItem) / 2;
        y = IConfig.MONITOR_HEIGHT/2 - tamItem;

        itens = new Item[qtd];
        //Shotgun   SubMachineGun   MachineGun  KitMedico
        itens[0] = new Item(0, "Shotgun", 2500, 95, 30, 50, 25, x, y);
        itens[1] = new Item(1, "SubMachineGun", 2700, 40, 60, 45, 80, x+(tamItem) ,y);
        itens[2] = new Item(2, "MachineGun", 4000, 65, 75, 55, 75, x+(tamItem*2), y);
        itens[3] = new Item("MedicalKit", 300, 25, x+(tamItem*3), y);

        height = tamItem - 40;
        width = qtd*tamItem;

        init();
    }

    private void init(){

        weaponsForSale.add(new Shotgun(-15, 5, player.getWidth()+25, player.getHeight()));
        weaponsForSale.add(new SubMachineGun(-8, -2, player.getWidth()+18, player.getHeight()));
        weaponsForSale.add(new MachineGun(-15, 5, player.getWidth()+27, player.getHeight()));

    }

    public void update(){
        if (HUD.isOpen && GameSystem.isLeftButtonPressed()) {
            for (Item i : itens) {
                if(!i.getSold()){
                    if ((GameSystem.getMouseX() > i.getXButton()) && (GameSystem.getMouseX() < (i.getXButton() + tamItem -20))) {
                        if ((GameSystem.getMouseY() > i.getYButton()) && (GameSystem.getMouseY() < (i.getYButton() + 40))) {
                            if(player.getCash() >= i.getPreco()) {
                                player.setCash(player.getCash() - i.getPreco());
                                if(i.getName().equals("MedicalKit"))
                                    player.setKits( player.getKits() + 1);
                                else {
                                    player.buyWeapon(weaponsForSale.get(i.getId()));
                                    i.setSold(true);
                                    itemCheck = i;
                                }
                            }
                            GameSystem.setLeftButtonPressed(false);
                        }
                    }
                }
            }
        }

    }
    public void render(){
        //*esc
        if(EventHandler.isEsc()){
            if(!lock){
                HUD.setOpen(!HUD.isOpen);
                lock = true;
            }
        }else{
            lock = false;
        }

        if(!HUD.isOpen) return;
        //*/

        //fundo
        RenderStrategy.getStrategy().setColor(new Color(0,0,0,150));
        RenderStrategy.getStrategy().fillRect(0,0,IConfig.MONITOR_WIDTH,IConfig.MONITOR_HEIGHT);

        //retangulo
        RenderStrategy.getStrategy().setColor(new Color(0,0,0,150));
        RenderStrategy.getStrategy().fillRect(x, y, width, height);

        for (Item i : itens) {
            //imagem
            RenderStrategy.getStrategy().drawImage(i.getImage(), i.getX(), i.getY(), null);
            //preço
            text("R$ " + i.getPreco(), 16, Color.WHITE, i.getX()+5, i.getY() + 100);

            //atributos
            RenderStrategy.getStrategy().setColor(new Color(0,0,0,150));
            RenderStrategy.getStrategy().fillRect(i.getX(), i.getY() + height, tamItem, height * 2);

            if(i.getName().equals("MedicalKit")){
                renderAtributo("Regeneration: ", i.getX(), y + 130, i.getRegeneracao());
                if(i.getSold())
                    renderButton("SOLD", new Color(0, 0, 0, 25), i.getXButton(), i.getYButton());
                else
                    renderButton("BUY", new Color(255, 255, 255, 100), i.getXButton(), i.getYButton());
            }
            else{
                renderAtributo("Damage: ", i.getX(), y + 130, i.getDano());
                renderAtributo("Range: ", i.getX(), y + 170, i.getAlcance());
                renderAtributo("Accuracy: ", i.getX(), y + 210, i.getPrecisao());
                renderAtributo("Fire Rate: ", i.getX(), y + 250, i.getTaxaFogo());

                if(i.getSold())
                    renderButton("SOLD", new Color(0, 0, 0, 25), i.getXButton(), i.getYButton());
                else
                    renderButton("BUY", new Color(255, 255, 255, 100), i.getXButton(), i.getYButton());
            }

        }
    }

    public void text(String text, int sizeFont, Color cor, int x,int y){
        RenderStrategy.getStrategy().setColor(cor);
        RenderStrategy.getStrategy().setFont(new Font("",Font.PLAIN, sizeFont));
        RenderStrategy.getStrategy().drawString(text,x,y);
    }

    public void renderAtributo(String nome, int x, int y, int porcentagem/*sei la*/){
        //string
        text(nome, 14, Color.WHITE, x + 5, y);

        //barra laranja
        RenderStrategy.getStrategy().setColor(new Color(0xFF4500));
        RenderStrategy.getStrategy().fillRect(x + 5, y + 8, (int)(porcentagem * 1.5) - 10, 8);

        //bordinha
        RenderStrategy.getStrategy().setColor(Color.black);
        RenderStrategy.getStrategy().drawRect(x + 4, y + 7, tamItem - 9, 9);
    }

    public void renderButton(String nome, Color cor, int x, int y){
        RenderStrategy.getStrategy().setColor(cor);
        RenderStrategy.getStrategy().fillRect(x, y, tamItem - 20, 40);

        text(nome, 20, Color.BLACK, x + 42, y + 27);
    }

    public String getName(){ return "loja"; }
    //public Item getGunSold(){ return itemCheck; }

}
