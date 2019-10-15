import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JFrame{

    private GraphicsDevice gd;
    private String response;

    public Main(){
        init();
    }

    private void init(){


        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();

        this.setUndecorated(true);
        this.setResizable(false);

        this.setVisible(true);
        //this.setBounds(0, 0, IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*
        System.out.print("Start ? [y/n]    ");
        Scanner sc = new Scanner(System.in);
        response = sc.next();
        if(response.equalsIgnoreCase("yes")){*/
            
            gd.setFullScreenWindow(this);
            this.add(new Game());
        //}

        while(true);

        //System.out.println(response);

        //this.pack();
    }

    public static void main(String[] args) {
        Main screen = new Main();
    }
}