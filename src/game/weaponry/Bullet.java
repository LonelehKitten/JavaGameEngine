package game.weaponry;

import engine.EventHandler;
import engine.RenderStrategy;
import engine.Solid;
import game.GUI.HUD;

import java.awt.*;

public class Bullet extends Solid {

    private int pivotX, pivotY, directionX, directionY, speed, ticks;
    private double angle;
    private boolean fired;
    private static int previousXAxis, previousYAxis;
    private Stroke caliber;

    public Bullet(int pivotX, int pivotY, int directionX, int directionY, int size, int speed, Color color) {
        super(null, 0, 0, pivotX, pivotY, 0, 0, color, false);
        this.pivotX = pivotX;
        this.pivotY = pivotY;
        this.X = cosOfRange(100);
        //this.pivotX += this.X;
        this.Y = sinOfRange(100);
        //this.pivotY += this.Y;
        this.directionX = directionX;
        this.directionY = directionY;
        this.width = cosOfRange(5000);
        this.height = sinOfRange(5000);
        this.speed = speed;
        this.color = color;
        this.fired = false;
        this.ticks = 5;
        this.caliber = new BasicStroke(size);
        this.fired = false;
    }

    @Override
    public void update() {

        X = cosOfRange(100);
        Y = sinOfRange(100);

        x = pivotX + X;
        y = pivotY + Y;

        ticks--;

        fired = true;

    }

    @Override
    public void updateRelativeToScene(int xaxis, int yaxis){
        //System.out.println("px: " + pivotX + ", py: " + pivotY + ", ax: " + xaxis + ", ay: " + yaxis + ", ( X ; Y ): ( " + X + " ; " + Y + " ), spd: " + sinOfRange(speed));

        pivotX = xaxis + (pivotX - Bullet.previousXAxis);
        pivotY = yaxis + (pivotY - Bullet.previousYAxis);

        directionX = xaxis + (directionX - Bullet.previousXAxis);
        directionY = yaxis + (directionY - Bullet.previousYAxis);

    }

    @Override
    public void render() {
        RenderStrategy.getStrategy().setStroke(caliber);
        if(ticks > 1 && ticks < 5) RenderStrategy.getStrategy().drawLine(x, y, x + width, y + height);
    }

    public int cosOfRange(double r){
        return (int) ( r * ( (directionX - pivotX ) / Math.sqrt( Math.pow( directionX - pivotX , 2) + Math.pow( directionY - pivotY , 2) )) );
    }

    public int sinOfRange(double r){
        return (int) ( r * ( (directionY - pivotY ) / Math.sqrt( Math.pow( directionX - pivotX , 2) + Math.pow( directionY - pivotY , 2) )) );
    }

    public void setFired(boolean fired){
        this.fired = fired;
    }

    public boolean isFired(){
        return fired;
    }

    public static void setCurrentAxisPosition(int currentXAxis, int currentYAxis){
        Bullet.previousXAxis = currentXAxis;
        Bullet.previousYAxis = currentYAxis;
    }

    public int getTicks(){
        return ticks;
    }

    public int getPivotX() {
        return pivotX;
    }

    public void setPivotX(int pivotX) {
        this.pivotX = pivotX;
    }

    public int getPivotY() {
        return pivotY;
    }

    public void setPivotY(int pivotY) {
        this.pivotY = pivotY;
    }
}
