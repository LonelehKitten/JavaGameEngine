package engine;

import game.GUI.HUD;
import game.GUI.MenuInicial.Menu;
import game.GUI.MenuInicial.Button;

import java.awt.*;
import java.util.*;

public class Scene implements InteractiveObject{

    protected int xaxis, yaxis, j;
    protected boolean current;
    private String name;


    protected Map<String, SceneObject> sceneObjects = new LinkedHashMap<>();
    protected Map<Integer, InteractiveObject> interactiveObjects = new LinkedHashMap<>();
    protected Map<Integer, SceneObject> nonStaticObjects = new HashMap<>();
    protected ArrayList<GateableObject> gateableObjects = new ArrayList<>();
    protected Map<Integer, SceneObject> backgrounds = new HashMap<>();

    private Physics physics = new Physics(sceneObjects, interactiveObjects, nonStaticObjects);

    private HUD hud;
    //private Menu menu = new Menu();

    public Scene(String name, int xaxis, int yaxis){
        this.name = name;
        this.xaxis = Camera.getX() + xaxis;
        this.yaxis = Camera.getY() + yaxis;
        current = false;
    }

    @Override
    public void update(){
        if(hud != null) hud.update();
        preUpdate();

//        for (String name : sceneObjects.keySet()) {
//            if(!Camera.getCollisionStatus() && sceneObjects.get(name).isColliding())
//                Camera.setAcceleration(Camera.getAccelerationStatus(sceneObjects.get(name).isCollided(
//                        Camera.getCameraCollisionFactor().getX(),
//                        Camera.getCameraCollisionFactor().getY(),
//                        Camera.getCameraCollisionFactor().getXOffset(),
//                        Camera.getCameraCollisionFactor().getYOffset()
//                        ))
//                );
//        }

        for(String name : sceneObjects.keySet()){
            sceneObjects.get(name).updateRelativeToScene(xaxis, yaxis);
        }

        physics.checkCollision();

        preBackgroundUpdate();

        for (int i = 0; i < backgrounds.size(); i++) {
            backgrounds.get(i).update();
        }

        preInteractiveObjectUpdate();

        for (int i : interactiveObjects.keySet()) {
            interactiveObjects.get(i).update();
            //if(j == 0)System.out.println(i);
        }

        preInteractiveObjectSort();

        sortRender();

        //j = 1;

        //Camera.resetCollisionStatus();

        //moveAxis(Camera.getAcceleration());

        postUpdate();

        //isto aqui ta mais pra uma gambiarra
        /*if(menu.getButtonPressed() == null)
            menu.update();*/
    }

    /**
     *  Scene update calls it before SceneObject updateRelativeToScene.
     *  The first thing the update method does.
     */
    protected void preUpdate(){}

    /**
     *  Scene update calls it before backgrounds update.
     *  First after preUpdate.
     */
    protected void preBackgroundUpdate(){}

    /**
     *  Scene update calls it before InteractiveObject update.
     *  Second after preUpdate.
     */
    protected void preInteractiveObjectUpdate(){}

    /**
     *  Scene update calls it before InteractiveObject sort.
     *  Third after preUpdate.
     */
    protected void preInteractiveObjectSort(){}

    /**
     *  Scene update calls it before everything.
     */
    protected void postUpdate(){ }



    @Override
    public void render(){

        for (int i = 0; i < backgrounds.size(); i++) {
            backgrounds.get(i).render();
        }

        for (int i : interactiveObjects.keySet()) {
            interactiveObjects.get(i).render();
            RenderStrategy.resetConfigurations();
        }

        if(hud != null) hud.render();

        //isto aqui ta mais pra uma gambiarra
       /* if(menu.getButtonPressed() == null)
            menu.render();*/

    }



    public void enter(SceneObject obj){
        System.out.println(getName());
        if(!sceneObjects.containsValue(obj)) {
            for (GateableObject gateable : gateableObjects) {
                System.out.println("enter");
                if (gateable.getGate() != null && gateable.getGate().hashCode() == Gate.instance) {

                    addSolid(obj, true);
                    System.out.println("ll");

                    int gx = gateable.getGate().getCurrentGateSide(gateable).getX();
                    int gy = gateable.getGate().getCurrentGateSide(gateable).getY();
                    int gxoff = gateable.getGate().getCurrentGateSide(gateable).getXOffset();
                    int gyoff = gateable.getGate().getCurrentGateSide(gateable).getYOffset();
                    int gw = gateable.getGate().getCurrentGateSide(gateable).getWidth();
                    int gh = gateable.getGate().getCurrentGateSide(gateable).getHeight();

                    int distance = 100;

                    if (obj.getHitBoxes().isEmpty()) {
                        if (Gate.direction == ObjectStatus.NORTH) {

                            obj.setX(gx + gw / 2 - obj.getWidth() / 2);
                            obj.setY(gy - distance);

                        } else if (Gate.direction == ObjectStatus.SOUTH) {

                            obj.setX(gx + gw / 2 - obj.getWidth() / 2);
                            obj.setY(gyoff + distance + obj.getHeight());

                        } else if (Gate.direction == ObjectStatus.WEST) {

                            obj.setX(gx - distance);
                            obj.setY(gy + gh / 2 - obj.getHeight() / 2);

                        } else if (Gate.direction == ObjectStatus.EAST) {

                            obj.setX(gxoff + distance + obj.getWidth());
                            obj.setY(gy + gh / 2 - obj.getHeight() / 2);

                        }
                    } else {
                        for (HitBox hb : obj.getHitBoxes()) {
                            if (Gate.direction == ObjectStatus.NORTH) {

                                obj.setX(gx + gw / 2 - hb.getWidth() / 2);
                                obj.setY(gy - distance);

                            } else if (Gate.direction == ObjectStatus.SOUTH) {

                                obj.setX(gx + gw / 2 - hb.getWidth() / 2);
                                obj.setY(gyoff + distance + hb.getHeight());

                            } else if (Gate.direction == ObjectStatus.WEST) {

                                obj.setX(gx - distance);
                                obj.setY(gy + gh / 2 - hb.getHeight() / 2);

                            } else if (Gate.direction == ObjectStatus.EAST) {

                                obj.setX(gxoff + distance + hb.getWidth());
                                obj.setY(gy + gh / 2 - hb.getHeight() / 2);

                            }

                            break;
                        }
                    }

                    setCurrent(true);
                    break;
                }

            }
        }
    }

    private void sortRender(){

        /*
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already in gapped order
            // keep adding one more element until the entire array is
            // gap sorted
            for (int i = gap; i < n; i += 1)
            {
                // add a[i] to the elements that have been gap sorted
                // save a[i] in temp and make a hole at position i
                int temp = arr[i];

                // shift earlier gap-sorted elements up until the correct
                // location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];

                //  put temp (the original a[i]) in its correct location
                arr[j] = temp;
            }
        }

         */

        InteractiveObject aux;
        int j;

        for( int gap = interactiveObjects.size() ; gap > 0 ; gap /= 2 ){

            for( int i = gap ; i < interactiveObjects.size() ; i++ ){

                aux = interactiveObjects.get(i);

                for( j = i ; j >= gap && isABehindB(aux, interactiveObjects.get(j - gap)) ; j -= gap ){

                    interactiveObjects.replace(j, interactiveObjects.get(j - gap));

                }

                interactiveObjects.replace(j, aux);

            }

        }

    }

    private boolean isABehindB(InteractiveObject A, InteractiveObject B){

        SceneObject Ai = sceneObjects.get(A.getName());
        SceneObject Bi = sceneObjects.get(B.getName());

        int AiY = Ai.getYOffset();//Bi.isColliding() ? Ai.getY() : Ai.getYOffset();
        int BiY = Bi.getYOffset();//Bi.isColliding() ? Bi.getY() : Bi.getYOffset();

        return AiY < BiY;
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
        //HUD.getPos(xaxis,yaxis);
    }

    public Scene addSolid(SceneObject sceneObject) {
        sceneObjects.put(sceneObject.getName(), sceneObject);
        interactiveObjects.put(interactiveObjects.size(), sceneObject);
        return this;
    }

    public Scene addSolid(SceneObject sceneObject, boolean nonstatic) {
        sceneObjects.put(sceneObject.getName(), sceneObject);
        if(nonstatic) nonStaticObjects.put(nonStaticObjects.size(), sceneObject);
        interactiveObjects.put(interactiveObjects.size(), sceneObject);
        return this;
    }

    public Scene addSolid(Map<String,Solid> sceneObject) {
        Solid solid;
        for (String name : sceneObject.keySet()) {
            solid = sceneObject.get(name);
            sceneObjects.put(solid.getName(), solid);
            interactiveObjects.put(interactiveObjects.size(), solid);
        }
        return this;
    }
    


    public Scene addSolid(Solid solid, boolean isGate){
        solid.setAbsoluteX( xaxis + solid.getX() );
        solid.setAbsoluteY( yaxis + solid.getY() );
        sceneObjects.put(solid.getName(), solid);
        interactiveObjects.put(interactiveObjects.size(), solid);
        if(isGate) gateableObjects.add(solid);
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Color color, boolean isGate){
        Solid newSolid = new Solid(name, x, y, xaxis, yaxis, width, height, color);
        sceneObjects.put(name, newSolid);
        interactiveObjects.put(interactiveObjects.size(),  newSolid);
        if(isGate) gateableObjects.add(newSolid);
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Color color, boolean is_colliding, boolean isGate){
        Solid newSolid = new Solid(name, x, y, xaxis, yaxis, width, height, color, is_colliding);
        sceneObjects.put(name, newSolid);
        interactiveObjects.put(interactiveObjects.size(), newSolid);
        if(isGate) gateableObjects.add(newSolid);
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Sprite sprite, int render_flag, boolean isGate){
        Solid newSolid = new Solid(name, x, y, xaxis, yaxis, width, height, sprite, render_flag);
        sceneObjects.put(name, newSolid);
        interactiveObjects.put(interactiveObjects.size(), newSolid);
        if(isGate) gateableObjects.add(newSolid);
        return this;
    }

    public Scene addSolid(String name, int x, int y, int width, int height, Sprite sprite, int render_flag, boolean is_colliding, boolean isGate){
        Solid newSolid = new Solid(name, x, y, xaxis, yaxis, width, height, sprite, render_flag, is_colliding);
        sceneObjects.put(name, newSolid);
        interactiveObjects.put(interactiveObjects.size(), newSolid);
        if(isGate) gateableObjects.add(newSolid);
        return this;
    }

    public Scene addEntity(Entity entity){
        entity.setAbsoluteX( xaxis + entity.getX() );
        entity.setAbsoluteY( yaxis + entity.getY() );
        sceneObjects.put(entity.getName(), entity);
        nonStaticObjects.put(nonStaticObjects.size(), entity);
        interactiveObjects.put(interactiveObjects.size(), entity);
        return this;
    }

    public Scene addEntity(String name, int type, int x, int y, int width, int height, Color color){
        Entity entity = new Entity(name, type, x, y, xaxis, yaxis, width, height, color);
        sceneObjects.put(name, entity);
        nonStaticObjects.put(nonStaticObjects.size(), entity);
        interactiveObjects.put(interactiveObjects.size(), entity);
        return this;
    }

    public Scene addBackground(SceneObject background){
        background.setAbsoluteX( xaxis + background.getX() );
        background.setAbsoluteY( yaxis + background.getY() );
        sceneObjects.put(background.getName(), background);
        backgrounds.put(backgrounds.size(), background);
        return this;
    }

    public void remove(String name, boolean nonStatic, boolean isGate){

        SceneObject out = getSolid(name);
        int i, j;

        if(isGate){

            GateableObject g;

            for (i = 0; i < gateableObjects.size(); i++) {
                g = gateableObjects.get(i);
                if (g.getGate().getCurrentGateSide(g).getName().equals(name)){
                    gateableObjects.remove(i);
                    break;
                }
            }
        }

        sceneObjects.remove(name);

        for(i = 0; i < interactiveObjects.size(); i++){
            if(interactiveObjects.get(i).getName().equals(name)) break;
        }

        for(j = i+1; j < interactiveObjects.size(); j++, i++){
            interactiveObjects.replace(i, interactiveObjects.get(j));
        }

        interactiveObjects.remove(j);

        if(nonStatic) {
            for (i = 0; i < nonStaticObjects.size(); i++) {
                if (nonStaticObjects.get(i).getName().equals(name)) break;
            }

            for (j = i + 1; j < nonStaticObjects.size(); j++, i++) {
                nonStaticObjects.replace(i, nonStaticObjects.get(j));
            }

            nonStaticObjects.remove(j);
        }

    }

    public Scene setCurrent(boolean current){
        this.current = current;
        return this;
    }

    public boolean isCurrent(){
        return current;
    }

    public SceneObject getSolid(String name){
        return sceneObjects.get(name);
    }

    public Set getSolidList(){
        return sceneObjects.keySet();
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

    public Scene setHud(HUD hud) {
        this.hud = hud;
        return this;
    }

    @Override
    public String getName(){
        return name;
    }

    protected boolean checkSceneChange(SceneObject obj){

        if(physics.checkGateEntering(obj, gateableObjects)){
            remove(obj.getName(), true, false);
            return true;
        }

        return false;
    }
}
