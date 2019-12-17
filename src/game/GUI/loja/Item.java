package game.GUI.loja;

import javax.swing.ImageIcon;
import java.awt.*;

public class Item{
    private ImageIcon img;
    private String name;
    private double preco;
    private int dano;       // de 0 à 100
    private int alcance;    // de 0 à 100
    private int precisao;   // de 0 à 100
    private int taxaFogo;   // de 0 à 100
    private static int regeneracao = 25;    //kit medico, de 0 à 100
    private int x;
    private int y;
    private boolean sold;     //vendido
    private int yButton;     //teste
    private int xButton;    //teste
    private int id;

    /*
            BOTEI ISSO SÓ PARA PODER EXECUTAR
     */
    public Item(String name){
        this.name = name;
        sold = false;
    }

    public Item(int id, String name, double preco, int dano, int alcance, int precisao, int taxaFogo, int x, int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.preco = preco;
        this.dano = dano;
        this.alcance = alcance;
        this.precisao = precisao;
        this.taxaFogo = taxaFogo;
        xButton = x + 10;
        yButton = y + 278;
        sold = false;

        try {
            img = new ImageIcon(getClass().getResource("guns/" + name + ".png"));
        } catch (Exception e) {
            System.out.println("não foi possivel carregar a imagem");
        }
    }

    //kit medico
    public Item(String name, double preco, int regeneracao, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.preco = preco;
        this.regeneracao = regeneracao;
        sold = false;

        xButton = x + 10;
        yButton = y + 158;

        try {
            img = new ImageIcon(getClass().getResource("guns/" + name + ".png"));
        } catch (Exception e) {
            System.out.println("não foi possivel carregar a imagem");
        }
    }

    public void setSold(boolean b){ sold = b; }
    public boolean getSold(){ return sold; }
    public void setYButton(int y){ yButton = y; }
    public void setXButton(int x){ xButton = x; }
    public int getYButton(){ return yButton; }
    public int getXButton(){ return xButton; }
    public Image getImage(){ return img.getImage(); }
    public double getPreco(){ return preco; }
    public String getName(){ return name; }
    public int getDano(){ return dano; }
    public int getAlcance(){ return alcance; }
    public int getPrecisao(){ return precisao; }
    public int getTaxaFogo(){ return taxaFogo; }
    public static int getRegeneracao(){ return regeneracao; }
    public int getX(){ return x; }
    public int getY(){ return y; }

    public int getId() {
        return id;
    }
}
