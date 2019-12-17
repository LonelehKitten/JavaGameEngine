package engine;

import java.awt.*;
import java.util.ArrayList;

public class Solid extends SceneObject implements GateableObject{

    protected int render_flag, tilexoff, tileyoff, animationId;

    private Gate gate;
    protected float sizeRelative = 1;

    protected Sprite sprite;


    private Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height){
        this.name = name;
        this.X = x;
        this.x = x + xaxis;
        this.Y = y;
        this.y = y + yaxis;
        this.width = width;
        this.height = height;
        this.speedX = this.speedY = 0;
    }

    public Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height, Color color){
        this(name, x, y, xaxis, yaxis, width, height);
        this.color = color;
        this.colliding = true;
    }

    public Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height, Color color, boolean colliding){
        this(name, x, y, xaxis, yaxis, width, height);
        this.color = color;
        this.colliding = colliding;
    }

    public Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height, Sprite sprite, int render_flag){
        this(name, x, y, xaxis, yaxis, width, height);
        this.sprite = sprite;
        this.render_flag = render_flag;
        this.colliding = true;
    }

    public Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height, Sprite sprite, int render_flag, boolean colliding){
        this(name, x, y, xaxis, yaxis, width, height);
        this.sprite = sprite;
        this.render_flag = render_flag;
        this.colliding = colliding;
    }

    public Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height, Sprite sprite, int render_flag, double proportion){
        this(name, x, y, xaxis, yaxis, (int) (width*proportion), (int) (height*proportion));
        this.sprite = sprite;
        this.render_flag = render_flag;
        this.colliding = true;
    }

    public Solid(String name, int x, int y, int xaxis, int yaxis, int width, int height, Sprite sprite, int render_flag, double proportion,  boolean colliding){
        this(name, x, y, xaxis, yaxis, (int) (width*proportion), (int) (height*proportion));
        this.sprite = sprite;
        this.render_flag = render_flag;
        this.colliding = colliding;
    }

    @Override
    public void update(){

    }

    @Override
    public void render() {
        if(sprite == null){
            RenderStrategy.getStrategy().setColor(color);
            RenderStrategy.getStrategy().fillRect(RenderStrategy.getXOf(x), RenderStrategy.getYOf(y),width, height);
        }
        else{
            if(render_flag == ObjectStatus.TILED){
                for(int i = x; i < getAbsoluteXOffset(); i += sprite.getWidth()){
                    for(int j = y; j < getAbsoluteYOffset(); j += sprite.getHeight()){
                        sprite.render(i, j, sprite.getWidth(), sprite.getHeight());
                    }
                }
            }
            else if(render_flag == ObjectStatus.ONLY_ONE){
                sprite.render(x, y,width,height);
            }
            else if(render_flag == ObjectStatus.TILED_ANIMATED){
                for(int i = x; i < getAbsoluteXOffset(); i += sprite.getFrameWidth()){
                    for(int j = y; j < getAbsoluteYOffset(); j += sprite.getFrameHeight()){
                        sprite.animate(i, j, i+sprite.getFrameWidth(), j+sprite.getFrameHeight(), animationId);
                    }
                }
            }
            else if(render_flag == ObjectStatus.ONLY_ONE_ANIMATED){
                sprite.animate(x, y, x+width, y+height, animationId);
            }
        }
    }

    public void updateRelativeToScene(int xaxis, int yaxis){
        /*
        if (engine.EventHandler.isUp()) {
            //y += yaxis;
            y = Y + yaxis;
        }

        if (engine.EventHandler.isDown()) {
            //if(!engine.Camera.getCollisionStatus()) y -= yaxis;
            //else y += yaxis;
            y = Y - yaxis;
        }

        if (engine.EventHandler.isLeft()) {
            //x += xaxis;
            x = X + xaxis;
        }

        if (engine.EventHandler.isRight()) {
            //if(!engine.Camera.getCollisionStatus()) x -= xaxis;
            //else x += xaxis;
            x = X - xaxis;
        }*/
        Y += speedY;
        X += speedX;
        y = Y + yaxis;
        x = X + xaxis;


//        if(gate != null){
//            checkGateStatus(
//                    Camera.getCameraCollisionFactor().getX(),
//                    Camera.getCameraCollisionFactor().getY(),
//                    Camera.getCameraCollisionFactor().getXOffset(),
//                    Camera.getCameraCollisionFactor().getYOffset()
//            );
//        }
    }

//    public void checkGateStatus(int x, int y, int xoffset, int yoffset){
//        if(x < getXOffset() && xoffset > getX() && y < getYOffset() && yoffset > getY()){
//            Gate.going_to[0] = Gate.now[0].equals(gate.getSide1Address()[0]) ? gate.getSide2Address()[0] : gate.getSide1Address()[0];
//            Gate.going_to[1] = Gate.now[1].equals(gate.getSide1Address()[1]) ? gate.getSide2Address()[1] : gate.getSide1Address()[1];
//
//            Gate.instance = gate.hashCode();
//
//            if (EventHandler.isUp()) {
//                Gate.direction = ObjectStatus.NORTH;
//            }
//            else if (EventHandler.isDown()) {
//                Gate.direction = ObjectStatus.SOUTH;
//            }
//            else if (EventHandler.isLeft()) {
//                Gate.direction = ObjectStatus.WEST;
//            }
//            else if (EventHandler.isRight()) {
//                Gate.direction = ObjectStatus.EAST;
//            }
//
//        }
//
//
//    }



    public int[] isCollided(int x, int y, int xoffset, int yoffset){

        int[] results = new int[3];

        if(hitBoxes == null) {

            if (EventHandler.isUp() && x < getAbsoluteXOffset() && xoffset > getAbsoluteX()) {
                if (y - Camera.getDefaultAcceleration() < getAbsoluteYOffset() && yoffset > getAbsoluteY()) {

                    results[0] = 1;
                    results[1] = getAbsoluteYOffset();
                    results[2] = 1;
                }

            } else if (EventHandler.isDown() && x < getAbsoluteXOffset() && xoffset > getAbsoluteX()) {
                if (y < getAbsoluteYOffset() && yoffset + Camera.getDefaultAcceleration() > getAbsoluteY()) {

                    results[0] = 1;
                    results[1] = getAbsoluteY();
                    results[2] = 3;
                }

            } else if (EventHandler.isLeft() && y < getAbsoluteYOffset() && yoffset > getAbsoluteY()) {
                if (x - Camera.getDefaultAcceleration() < getAbsoluteXOffset() && xoffset > getAbsoluteX()) {

                    results[0] = 1;
                    results[1] = getAbsoluteXOffset();
                    results[2] = 4;
                }

            } else if (EventHandler.isRight() && y < getAbsoluteYOffset() && yoffset > getAbsoluteY()) {
                if (x < getAbsoluteXOffset() && xoffset + Camera.getDefaultAcceleration() > getAbsoluteX()) {

                    results[0] = 1;
                    results[1] = getAbsoluteX();
                    results[2] = 2;
                }

            }

        }
        else{

            for ( HitBox hb : hitBoxes) {

                if(hb.isCollided(x, y, xoffset, yoffset, this.x, this.y)){
                    results = hb.getCollisionResults();
                    break;
                }

            }

        }

        return results;
    }

    @Override
    public void addGate(Gate gate){
        this.gate = gate;
    }

    @Override
    public Gate getGate(){
        return gate;
    }

    public int getAnimationId() {
        return animationId;
    }

    public void setAnimationId(int animationId) {
        this.animationId = animationId;
    }


}
