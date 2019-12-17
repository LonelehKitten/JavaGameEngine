package engine;

import javax.swing.*;
import java.awt.event.*;

public class EventHandler {

    private static boolean up, down, left, right,esc;

    private Game game;

    public EventHandler(Game game){
        this.game = game;
        init();
    }

    private void init(){

        up = down = left = right = false;
    }

    public void setKeyEvents(){
        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {

                GameSystem.setSomeKeyPressed(true);
                GameSystem.setKey(e.getKeyCode());

                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                        up = true;
                        break;
                    case KeyEvent.VK_A:
                        left = true;
                        break;
                    case KeyEvent.VK_S:
                        down = true;
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        esc = true;
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                GameSystem.setSomeKeyPressed(false);
                GameSystem.setKey(0);

                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                        up = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                    case KeyEvent.VK_ENTER:
                        esc = false;
                        break;

                }
            }
        });
    }

    public void setMouseEvents(){
        game.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                GameSystem.setMouseX(e.getX());
                GameSystem.setMouseY(e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e){
                GameSystem.setMouseX(e.getX());
                GameSystem.setMouseY(e.getY());
            }
        });

        game.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    GameSystem.setLeftButtonPressed(true);
                    GameSystem.setMouseXPressed(e.getX());
                    GameSystem.setMouseYPressed(e.getY());
                }
                if(SwingUtilities.isRightMouseButton(e)) GameSystem.setRightButtonPressed(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) GameSystem.setLeftButtonPressed(false);
                if(SwingUtilities.isRightMouseButton(e)) GameSystem.setRightButtonPressed(false);
            }

        });
    }

    public static boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public static boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public static boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public static boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public static boolean isEsc() {
        return esc;
    }

    public void setEsc(boolean esc) {
        this.esc = esc;
    }
}
