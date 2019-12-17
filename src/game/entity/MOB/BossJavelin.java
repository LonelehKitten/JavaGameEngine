package game.entity.MOB;

import engine.RenderStrategy;
import engine.Solid;
import engine.Sprite;

public class BossJavelin extends Solid {

    private int pivotX, pivotY, directionX, directionY, speed, ticks;
    private double angle;
    private boolean fired;
    private static int previousXAxis, previousYAxis;


    public BossJavelin(int angle, int x, int y, int width, int height) {
        super(null, x, y, 0, 0, 0, 0, null, false);

//        this.X = cosOfRange(100);
//        //this.pivotX += this.X;
//        this.Y = sinOfRange(100);
//        //this.pivotY += this.Y;
        this.speedX = (int) (100*Math.cos(angle*Math.PI/180));
        this.speedY = (int) (100*Math.sin(angle*Math.PI/180));
        this.width = width;
        this.height = height;
        this.angle = angle;
//        this.speed = speed;
//        this.color = color;
//        this.fired = false;
//        this.ticks = 5;
//        this.fired = false;

        sprite = new Sprite("assets/imgs/boss-javelin.png");
    }

    @Override
    public void update() {

        speedX = (int) (100*Math.cos(angle*Math.PI/180));
        speedY = (int) (100*Math.sin(angle*Math.PI/180));

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

        pivotX = xaxis + (pivotX - BossJavelin.previousXAxis);
        pivotY = yaxis + (pivotY - BossJavelin.previousYAxis);

        directionX = xaxis + (directionX - BossJavelin.previousXAxis);
        directionY = yaxis + (directionY - BossJavelin.previousYAxis);

    }



    @Override
    public void render() {

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
        BossJavelin.previousXAxis = currentXAxis;
        BossJavelin.previousYAxis = currentYAxis;
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
