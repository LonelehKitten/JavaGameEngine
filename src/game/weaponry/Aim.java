package game.weaponry;

import engine.GameSystem;
import engine.InteractiveObject;
import engine.RenderStrategy;

import java.awt.*;

public class Aim implements InteractiveObject {

    private int x1, y1, x2, y2, size, distance, pivotX, pivotY;
    private Color color;
    private Stroke stroke;

    private String name;

    public Aim(String name, int pivotX, int pivotY, int distance, int size, Color color, Stroke stroke){
        this.name = name;
        this.pivotX = pivotX;
        this.pivotY = pivotY;
        this.distance = distance;
        this.size = size;
        this.color = color;
        this.stroke = stroke;
    }

    @Override
    public void update(){
        x1 = pivotX + (int) rotX(distance);
        y1 = pivotY + (int) rotY(distance);
        x2 = pivotX + (int) rotX(size);
        y2 = pivotY + (int) rotY(size);
    }

    @Override
    public void render() {

        RenderStrategy.getStrategy().setColor(color);
        RenderStrategy.getStrategy().setStroke(stroke);
        RenderStrategy.getStrategy().drawLine(x1, y1, x2, y2);

    }

    private double rotX(double r){
        return r * ( (GameSystem.getMouseX() - pivotX ) / Math.sqrt( Math.pow( GameSystem.getMouseX() - pivotX , 2) + Math.pow( GameSystem.getMouseY() - pivotY , 2) ));
    }

    private double rotY(double r){
        return r * ( (GameSystem.getMouseY() - pivotY ) / Math.sqrt( Math.pow( GameSystem.getMouseX() - pivotX , 2) + Math.pow( GameSystem.getMouseY() - pivotY , 2) ));
    }

    @Override
    public String getName() {
        return name;
    }
}
