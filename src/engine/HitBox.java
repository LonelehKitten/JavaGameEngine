package engine;

public class HitBox {

    private int x, y, width, height;
    private int[] collisionResults = new int[3];

    public HitBox(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isCollided(int x, int y, int xoffset, int yoffset, int pivotX, int pivotY){
        return generateCollisionResultsOf(x, y, xoffset, yoffset, pivotX, pivotY);
    }

    public boolean isCollided(SceneObject sceneObject, int pivotX, int pivotY){
        return generateCollisionResultsOf(sceneObject.getAbsoluteX(), sceneObject.getAbsoluteY(), sceneObject.getAbsoluteXOffset(), sceneObject.getAbsoluteYOffset(), pivotX, pivotY);
    }

    public int[] getCollisionResults(){
        return collisionResults;
    }

    private boolean generateCollisionResultsOf(int x, int y, int xoffset, int yoffset, int pivotX, int pivotY){

        if(EventHandler.isUp() && x < getRelativeXOffset(pivotX) && xoffset > getRelativeX(pivotX)){
            if(y - Camera.getDefaultAcceleration() < getRelativeYOffset(pivotY) && yoffset > getRelativeY(pivotY)){

                collisionResults[0] = 1;
                collisionResults[1] = getRelativeYOffset(pivotY);
                collisionResults[2] = 1;

                return true;

            }
        }
        else if(EventHandler.isDown() && x < getRelativeXOffset(pivotX) && xoffset > getRelativeX(pivotX)){
            if(y < getRelativeYOffset(pivotY) && yoffset + Camera.getDefaultAcceleration() > getRelativeY(pivotY)){

                collisionResults[0] = 1;
                collisionResults[1] = getRelativeY(pivotY);
                collisionResults[2] = 3;

                return  true;

            }
        }
        else if(EventHandler.isLeft() && y < getRelativeYOffset(pivotY) && yoffset > getRelativeY(pivotY)){
            if(x - Camera.getDefaultAcceleration() < getRelativeXOffset(pivotX) && xoffset > getRelativeX(pivotX)) {

                collisionResults[0] = 1;
                collisionResults[1] = getRelativeXOffset(pivotX);
                collisionResults[2] = 4;

                return true;

            }
        }
        else if(EventHandler.isRight() && y < getRelativeYOffset(pivotY) && yoffset > getRelativeY(pivotY)){
            if(x < getRelativeXOffset(pivotX) && xoffset + Camera.getDefaultAcceleration() > getRelativeX(pivotX)){

                collisionResults[0] = 1;
                collisionResults[1] = getRelativeX(pivotX);
                collisionResults[2] = 2;

                return true;

            }
        }

        generateLog(x, y, xoffset, yoffset, pivotX, pivotY);

        return false;
    }


    private void generateLog(int x, int y, int xoffset, int yoffset, int pivotX, int pivotY){

        System.out.println( " =============================================================================== " );
        System.out.println( " \tPlayer (x, y): " + x + " , " + y + " | Player (xoff, yoff): " + xoffset + " , " + yoffset);
        System.out.println( " \tHitbox (x, y): " + getRelativeX(pivotX) + " , " + getRelativeY(pivotY) + " | Hitbox (xoff, yoff): " + getRelativeXOffset(pivotX) + " , " + getRelativeYOffset(pivotY));
        System.out.println( " \tPivot (x, y): " + pivotX + " , " + pivotY );
        //System.out.println( " \tCollision: " + ( collision ? "TRUE" : "FALSE" ) );

    }



    public int getRelativeX(int pivotX){
        return pivotX + x;
    }

    public int getRelativeY(int pivotY){
        return pivotY + y;
    }

    public int getRelativeXOffset(int pivotX){
        return pivotX + x + width;
    }

    public int getRelativeYOffset(int pivotY){
        return pivotY + y + height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
