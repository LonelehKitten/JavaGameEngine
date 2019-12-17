package engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class SceneObject implements InteractiveObject{

    protected int x, y, z, width, height, X, Y, speedX, speedY;
    protected Color color;
    protected boolean colliding;
    protected String name;
    protected ArrayList<HitBox> hitBoxes;

    public int getAbsoluteX() {
        return x;
    }

    public void setAbsoluteX(int x) {
        this.x = x;
    }

    public int getAbsoluteXOffset(){ return x + width; }

    public int getAbsoluteY() {
        return y;
    }

    public void setAbsoluteY(int y) {
        this.y = y;
    }

    public int getAbsoluteYOffset(){ return y + height; }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX(){
        return X;
    }

    public void setX(int X){
        this.X = X;
    }

    public int getY(){
        return Y;
    }

    public void setY(int Y){
        this.Y = Y;
    }

    public int getXOffset() {
        return X + width;
    }

    public int getYOffset() {
        return Y + height;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public String getName() {
        return name;
    }

    public abstract void updateRelativeToScene(int xaxis, int yaxis);

    public abstract int[] isCollided(int x, int y, int xoffset, int yoffset);

    public boolean isColliding() {
        return colliding;
    }

    /**
     *  Os valores da hitbox devem ser relativos ao objeto em questão.
     * @param hitBox
     */
    public void addHitBox(HitBox hitBox){
        if(hitBoxes == null) hitBoxes = new ArrayList<>();
        hitBoxes.add(hitBox);
    }

    public ArrayList<HitBox> getHitBoxes(){
        return hitBoxes;
    }

}
