package engine;

import javax.sound.sampled.Clip;

public class GamePlayer extends Solid{

    protected double hp, mp, stamina;

    protected int direction;

    protected Song step;

    public GamePlayer(String name, int x, int y, int z, int width, int height, double hp, double mp, double stamina){
        //super(name,-width/2, -height/2, x, y, width, height, color, false);
        super(name, x, y, 0, 0, width, height, null);
        this.z = z;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.mp = mp;
        this.stamina = stamina;
        this.direction = 3;

    }



    @Override
    public void update(){
        super.update();

        if(step != null && !step.isPlaying() && (EventHandler.isUp() || EventHandler.isDown() || EventHandler.isLeft() || EventHandler.isRight())){
            step.play(Clip.LOOP_CONTINUOUSLY);
        }
        else if(!(EventHandler.isUp() || EventHandler.isDown() || EventHandler.isLeft() || EventHandler.isRight())){
            if(step != null && step.isPlaying()) step.pause();
        }
    }

    @Override
    public void updateRelativeToScene(int xaxis, int yaxis) {
        super.updateRelativeToScene(xaxis, yaxis);
    }

    @Override
    public void render(){

        if(direction > 0)
            sprite.animate(getAbsoluteX(), getAbsoluteY(), getAbsoluteXOffset(), getAbsoluteYOffset(), direction);
        else
            sprite.render(getAbsoluteX(), getAbsoluteY(), getAbsoluteXOffset(), getAbsoluteYOffset(), 2, - direction);
    }




    /* ==== GETTERS AND SETTERS ==== */

    public void setDirection(int direction){
        this.direction = direction;
    }



}
