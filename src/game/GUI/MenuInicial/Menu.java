package game.GUI.MenuInicial;

import engine.EventHandler;
import engine.GameSystem;
import engine.IConfig;
import engine.InteractiveObject;
import engine.RenderStrategy;

import java.awt.*;
import javax.swing.ImageIcon;

public class Menu implements InteractiveObject {
    private int heightButton;
    private int widthButton;
    private int x;
    private int y;
    private Button[] buttons;
    private Button buttonPressed;
    private Image imagemFundo;

    public Menu(){
        widthButton = 300;
        heightButton = 50;
        x = (IConfig.MONITOR_WIDTH - widthButton) / 2;
        y = (IConfig.MONITOR_HEIGHT - heightButton) / 2;

        buttons = new Button[2];
        buttons[0] = new Button("SinglePlayer", x, y, heightButton, widthButton);
        //buttons[1] = new Button("MultiPlayer", x, y + 70, heightButton, widthButton);
        buttons[1] = new Button("Exit", x, y + 70, heightButton, widthButton);
        buttonPressed = null;

        imagemFundo = new ImageIcon(getClass().getResource("imagemfundomenu.jpg")).getImage().getScaledInstance(IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT, 0);
    }

    public void update(){
        //if(GameSystem.isLeftButtonPressed()){
            for(Button i : buttons){
                if (
                        GameSystem.getMouseXPressed() > i.getX() && GameSystem.getMouseXPressed() < i.getX() + i.getWidth() &&
                        GameSystem.getMouseYPressed() > i.getY() && GameSystem.getMouseYPressed() < i.getY() + i.getHeight()
                ){

                    clicked(i);
                    if(i.getName().equals("Exit"))
                        System.exit(0);
                    break;

                }
            }
        //}
    }

    public void render(){
        //fundo
        RenderStrategy.getStrategy().drawImage(imagemFundo, 0,0, null);

        renderLogo(x - 200, y - 150);

        for(Button i : buttons)
            renderButton(i);
    }

    private void renderButton(Button button){
        //new Color(139,0,0,150));
        if(button.isPressed()){
            RenderStrategy.getStrategy().setColor(new Color(0,0,0,200));
            RenderStrategy.getStrategy().fillRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        }
        else{
            RenderStrategy.getStrategy().setColor(new Color(0,0,0,150));
            RenderStrategy.getStrategy().fillRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        }

        //string
        RenderStrategy.getStrategy().setFont(new Font("",Font.ITALIC, 20));
        RenderStrategy.getStrategy().setColor(Color.white);
        RenderStrategy.getStrategy().drawString(button.getName(), button.getX() + button.getWidth()/2 - (button.getName().length()*9)/2, button.getY() + 32);
    }

    public void renderLogo(int x, int y){
        RenderStrategy.getStrategy().setFont(new Font("",Font.ITALIC, 100));
        RenderStrategy.getStrategy().setColor(Color.white);

        RenderStrategy.getStrategy().drawString("The Last Cure", x, y);

        //copyright
        RenderStrategy.getStrategy().setFont(new Font("",Font.ITALIC, 16));
        RenderStrategy.getStrategy().setColor(Color.white);
        RenderStrategy.getStrategy().drawString("The Last Cure © 2019 CodersRize Alrights Reserved",IConfig.MONITOR_WIDTH-450 , IConfig.MONITOR_HEIGHT-20);

    }

    public void clicked(Button button){
        button.setPressed(true);
        buttonPressed = button;
    }

    public Button getButtonPressed(){ return buttonPressed; }

    public boolean play(){
        return buttonPressed != null && buttonPressed.getName().equals(buttons[0].getName());
    }

    public String getName(){ return "Menu Inicial"; }


}
