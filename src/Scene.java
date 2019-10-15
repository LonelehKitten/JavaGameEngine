import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Scene {

    private int xaxis, yaxis;

    private boolean current;
    private Map<String, Solid> solid = new HashMap<>();
    //private RenderStrategy scenery = new RenderStrategy(0, 0, BufferedImage.TYPE_INT_ARGB);


    public Scene(int xaxis, int yaxis){
        this.xaxis = Camera.getX() + xaxis;
        this.yaxis = Camera.getY() + yaxis;
        current = false;
    }

    public void update(){

        for (String name : solid.keySet()) {
            if(!Camera.getCollisionStatus() && solid.get(name).isColliding())
                Camera.setAcceleration(Camera.getAccelerationStatus(solid.get(name).isCollided(
                        Camera.getPlayerX(),
                        Camera.getPlayerY(),
                        Camera.getPlayerXOffset(),
                        Camera.getPlayerYOffset()))
                );
        }

        moveAxis(Camera.getAcceleration());

        for (String name : solid.keySet()) {
            solid.get(name).move(xaxis, yaxis);
        }

        Camera.resetCollisionStatus();
    }

    public void render(Graphics2D g2d){

        for (String name : solid.keySet()) {
            solid.get(name).render(g2d);
        }

    }

    public void enter(){
        for (String name : solid.keySet()) {
            if(solid.get(name).getGate() != null && solid.get(name).getGate().hashCode() == Gate.instance){

                if(Gate.direction == ObjectStatus.NORTH) {

                    xaxis = Camera.getX();
                    yaxis = Camera.getY() - (solid.get(name).getAbsoluteY() - 50);
                }
                else if(Gate.direction == ObjectStatus.SOUTH){

                    xaxis = Camera.getX();
                    yaxis = Camera.getY() - (solid.get(name).getAbsoluteYOffset() + 50);

                }
                else if(Gate.direction == ObjectStatus.WEST){
                    xaxis = Camera.getX() - (solid.get(name).getAbsoluteX() - 50);
                    yaxis = Camera.getY();
                }
                else if(Gate.direction == ObjectStatus.EAST){
                    xaxis = Camera.getX() - (solid.get(name).getAbsoluteXOffset() + 50);
                    yaxis = Camera.getY();
                }

                setCurrent(true);
            }

        }
    }

    private void moveAxis(int acceleration){
        if (EventHandler.isUp()) {
            yaxis += acceleration;
        }

        if (EventHandler.isDown()) {
            if(!Camera.getCollisionStatus()) yaxis -= acceleration;
            else yaxis += acceleration;
        }

        if (EventHandler.isLeft()) {
            xaxis += acceleration;
        }

        if (EventHandler.isRight()) {
            if(!Camera.getCollisionStatus()) xaxis -= acceleration;
            else xaxis += acceleration;
        }
    }



    public Scene addSolid(String name, int x, int y, int width, int height, Color color){
        solid.put(name, new Solid(x, y, xaxis, yaxis, width, height, color));//, scenery));
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Color color, boolean is_colliding){
        solid.put(name, new Solid(x, y, xaxis, yaxis, width, height, color, is_colliding));//, scenery));
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Sprite sprite, int render_flag){
        solid.put(name, new Solid(x, y, xaxis, yaxis, width, height, sprite, render_flag));//, scenery));
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Sprite sprite, int render_flag, boolean is_colliding){
        solid.put(name, new Solid(x, y, xaxis, yaxis, width, height, sprite, render_flag, is_colliding));//, scenery));
        return this;
    }

    public Scene addEntity(String name, int type, int breed, int work, int x, int y, int width, int height, Color color){
        solid.put(name, new Entity(name, type, breed, work, x, y, xaxis, yaxis, width, height, color));
        return this;
    }

    public Scene setCurrent(boolean current){
        this.current = current;
        return this;
    }

    public boolean isCurrent(){
        return current;
    }

    public Solid getSolid(String name){
        return solid.get(name);
    }

    public Set getSolidList(){
        return solid.keySet();
    }


    public int getXAxis() {
        return xaxis;
    }

    public void setXAxis(int xaxis) {
        this.xaxis = xaxis;
    }

    public int getYAxis() {
        return yaxis;
    }

    public void setYAxis(int yaxis) {
        this.yaxis = yaxis;
    }
}
