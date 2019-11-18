import javax.sound.sampled.Clip;
import java.awt.*;

public class Player extends SceneObject{

    private double hp, mp, stamina;

    private int direction;

    private Song step = new Song("assets/songs/ground-footsteps.wav");
    private Sprite sprite;

    public Player(int x, int y, int z, int width, int height, double hp, double mp, double stamina, Color color){
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.mp = mp;
        this.stamina = stamina;
        this.color = color;
        this.direction = 3;
        sprite = new Sprite("assets/imgs/player.png", 48, 48, 3, 4, 5);
        sprite.addAnimation(4, new int[][]{ {1, 4}, {2, 4}, {3, 4} });
        sprite.addAnimation(2, new int[][]{ {1, 2}, {2, 2}, {3, 2} });
        sprite.addAnimation(1, new int[][]{ {1, 1}, {2, 1}, {3, 1} });
        sprite.addAnimation(3, new int[][]{ {1, 3}, {2, 3}, {3, 3} });
    }

    public void update(){

        if(EventHandler.isUp()){
            direction = 4;
        }
        else if(EventHandler.isDown()){
            direction = 1;
        }
        else if(EventHandler.isLeft()){
            direction = 2;
        }
        else if(EventHandler.isRight()){
            direction = 3;
        }


        if(!step.isPlaying() && (EventHandler.isUp() || EventHandler.isDown() || EventHandler.isLeft() || EventHandler.isRight())){
            step.play(Clip.LOOP_CONTINUOUSLY);
        }
        else if(!(EventHandler.isUp() || EventHandler.isDown() || EventHandler.isLeft() || EventHandler.isRight())){
            if(step.isPlaying()) step.pause();
            if(direction > 0) direction *= -1;
        }
    }

    @Override
    public void render(Graphics2D g2d){
        //g2d.setColor(color);
        //g2d.fillRect(x, y, width, height);

        if(direction > 0)
            sprite.animate(x, y, getXOffset(), getYOffset(), direction);
        else
            sprite.render(x, y, getXOffset(), getYOffset(), 2, - direction);
    }


    /* ==== GETTERS AND SETTERS ==== */

    public void setDirection(int direction){
        this.direction = direction;
    }

}
