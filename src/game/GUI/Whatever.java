package game.GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class Whatever {
    private String[] itens;
    private GridLayout grid;
    private JInternalFrame janelaInterna;
    private int linhas = 2;
    private int colunas;

    /*  ARMAS   */
    private ImageIcon img;
    private Image arma;

    public Whatever(String []itens){
        this.itens = itens;
        colunas = itens.length;

        gera();
    }

    /*

        NOTA IMPORTANTE:

        Usar as interfaces do swing por cima de um desenho do canvas/jpanel dá
        um problema enorme e chato de resolver.

        SUGESTÃO:

        Faz a interface pintando ela ou renderizando sprites.
        Se quiser faço as sprites mas não acho que seja preciso.

        BY: Daniel

        NOTA3: Fiz as sprites do zombie. Zombie comuna kKJkJKjKJKkKKJkJjJJ :B (vou fazer mais 2 ou 3 modelos de cor)

        BELEUZA
     */

    public void gera(){
        grid = new GridLayout(linhas,colunas);

        janelaInterna = new JInternalFrame("Vamos as compras");
        janelaInterna.setSize(colunas * 100, linhas * 100);           //quadrados de  100 x 100, saporra não funciona
        //janelaInterna.getContentPane().setBackground(Color.WHITE);
        janelaInterna.setVisible(true);
        janelaInterna.setLayout(grid);

        //janelaInterna.add(new JLabel(new ImageIcon(pistola)));
        /*
        for(int i = 0; i < colunas; i++){
            janelaInterna.add(new JLabel(itens[i]));

        }
        */
        for(int i = 0; i < colunas; i++){
            img  = new ImageIcon(getClass().getResource(itens[0] + ".png"));
            arma = img.getImage().getScaledInstance(70, 70, 0);

            janelaInterna.add(new JLabel(new ImageIcon(arma)));
        }

        for(int i = 0; i < colunas; i++)
            janelaInterna.add(new JButton("Comprar"));

    }

    public JInternalFrame getJanelaInterna(){
        return janelaInterna;
    }

    /*
    public static void main(String[] args) {
        String []vetor = new String[4];
        vetor[0] = "pistola";
        vetor[1] = "m4";
        vetor[2] = "ak";
        vetor[3] = "bazuca";  // Q isso! O.O By: Daniel

        JFrame janela = new JFrame("TESTE");
        janela.setSize(400, 200);           //aaaaaa
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setVisible(true);

        CompraItens ci = new CompraItens(vetor);
        janela.add(ci.getJanelaInterna());

    }
    //*/

}
