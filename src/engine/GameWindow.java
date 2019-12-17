package engine;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    //private GraphicsDevice gd;
    private GraphicsDevice[] gd;
    private Game game;

    public GameWindow(Game game){
        this.game = game;
        init();
    }

    private void init(){

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Fiz uma mudança para eu conseguir executar o jogo e editar também
        //gd = ge.getDefaultScreenDevice();

        gd = ge.getScreenDevices();

        this.setUndecorated(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);

        //this.setSize(IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);
        //this.setLocationRelativeTo(null);
        //this.setLayout(new GridLayout());

        this.setVisible(true);
        this.requestFocus();
        if(gd.length == 1){
            gd[0].setFullScreenWindow(this);
        }else{
            gd[0].setFullScreenWindow(this);
        }

        //gd.setFullScreenWindow(this);

        this.add(game);
        game.init();

    }

}