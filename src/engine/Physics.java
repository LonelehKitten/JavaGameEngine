package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Physics {

    private Map<String, SceneObject> sceneObjects;
    private Map<Integer, InteractiveObject> interactiveObjects;
    private Map<Integer, SceneObject> nonStaticObjects;

    //private int[][] objectVector = new int[sceneObjects.size()][3];
    private IntersectionList intersectionList = new IntersectionList();

    private int X1, Y1, XOFF1, YOFF1, X2, Y2, XOFF2, YOFF2; // Defitive
    private int x1, y1, xoff1, yoff1, x2, y2, xoff2, yoff2; // Local

    private int locker = 0;
    private String[] observer = new String[2];

    public Physics(Map<String, SceneObject> sceneObjects, Map<Integer, InteractiveObject> interactiveObjects,  Map<Integer, SceneObject> nonStaticObjects){
        this.sceneObjects = sceneObjects;
        this.interactiveObjects = interactiveObjects;
        this.nonStaticObjects = nonStaticObjects;
    }


    // GATE DETECTION ALGORITHM



    public boolean checkGateEntering(SceneObject obj, ArrayList<GateableObject> gateableObjects){

        for(GateableObject gateable : gateableObjects){
            //if(gateable.getGate() != null && gateable.getGate().hashCode() == Gate.instance){

                SceneObject g = gateable.getGate().getCurrentGateSide(gateable);

                if(obj.getHitBoxes().isEmpty()) {
                    if (obj.getX() < g.getXOffset() && obj.getXOffset() > g.getX() && obj.getY() < g.getYOffset() && obj.getYOffset() > g.getY()) {
                        Gate.going_to[0] = Gate.now[0].equals(gateable.getGate().getSide1Address()[0]) ? gateable.getGate().getSide2Address()[0] : gateable.getGate().getSide1Address()[0];
                        Gate.going_to[1] = Gate.now[1].equals(gateable.getGate().getSide1Address()[1]) ? gateable.getGate().getSide2Address()[1] : gateable.getGate().getSide1Address()[1];
                        //System.out.println("checkGateEntering");
                        Gate.instance = gateable.getGate().hashCode();

                        if (obj.getSpeedY() < 0) {
                            Gate.direction = ObjectStatus.NORTH;
                        } else if (obj.getSpeedY() > 0) {
                            Gate.direction = ObjectStatus.SOUTH;
                        } else if (obj.getSpeedX() < 0) {
                            Gate.direction = ObjectStatus.WEST;
                        } else if (obj.getSpeedX() > 0) {
                            Gate.direction = ObjectStatus.EAST;
                        }

                        return true;

                    }
                }
                else{
                    for( HitBox hb : obj.getHitBoxes()){
                        if (hb.getRelativeX(obj.getX()) < g.getXOffset() && hb.getRelativeXOffset(obj.getX()) > g.getX() && hb.getRelativeY(obj.getY()) < g.getYOffset() && hb.getRelativeYOffset(obj.getY()) > g.getY()) {
                            Gate.going_to[0] = Gate.now[0].equals(gateable.getGate().getSide1Address()[0]) ? gateable.getGate().getSide2Address()[0] : gateable.getGate().getSide1Address()[0];
                            Gate.going_to[1] = Gate.now[1].equals(gateable.getGate().getSide1Address()[1]) ? gateable.getGate().getSide2Address()[1] : gateable.getGate().getSide1Address()[1];
                            //System.out.println("checkGateEntering");
                            Gate.instance = gateable.getGate().hashCode();

                            if (obj.getSpeedY() < 0) {
                                Gate.direction = ObjectStatus.NORTH;
                            } else if (obj.getSpeedY() > 0) {
                                Gate.direction = ObjectStatus.SOUTH;
                            } else if (obj.getSpeedX() < 0) {
                                Gate.direction = ObjectStatus.WEST;
                            } else if (obj.getSpeedX() > 0) {
                                Gate.direction = ObjectStatus.EAST;
                            }

                            return true;

                        }
                    }
                }

            //}
        }

        return false;

    }


    // COLLISION DETECTION ALGORITHM

    public void checkCollision(){

        if(checkIntersections()){
            //observer[0] = "";
            //observer[1] = "";
            fixPosition();
        }
    }

    private void fixPosition(){

        //System.out.println("+++");

        do {

            int intersection = 0, i;
            int[][] matrix;
            boolean[] isCollided = new boolean[2];
            String[] key = new String[2];
            SceneObject a, b;

            for (String[] pair : intersectionList.getKeySet()) {

                a = intersectionList.get(pair)[0];
                b = intersectionList.get(pair)[1];

                matrix = getCollider(a, b);

                if (areOpposites(matrix)) {

                    i = matrix[0][1] / 2 + 1;
                    isCollided[0] = true;
                    isCollided[1] = true;

                } else {

                    if (matrix[0][1] > matrix[1][1]) {
                        i = matrix[0][1] + 1;
                        isCollided[0] = true;
                        isCollided[1] = false;
                    } else {
                        i = matrix[1][1] + 1;
                        isCollided[0] = false;
                        isCollided[1] = true;
                    }

                }

                if (i > intersection) {
                    intersection = i;
                    key = pair;
                    X1 = x1;
                    Y1 = y1;
                    XOFF1 = xoff1;
                    YOFF1 = yoff1;
                    X2 = x2;
                    Y2 = y2;
                    XOFF2 = xoff2;
                    YOFF2 = yoff2;

                }
            }

            if(!fix(key, isCollided, intersection)){
                intersectionList.clear();
                break;
            }

            if(observer[0] == null){
                observer[0] = key[0];
                observer[1] = key[1];
            }
            else if( (observer[0].equals(key[0] ) && observer[1].equals(key[1])) || (observer[0].equals(key[1] ) && observer[1].equals(key[0])) ){
                locker++;
            }

            intersectionList.clear();

        }while(locker < 10 && checkIntersections());

        locker = 0;
        observer[0] = null;
        observer[1] = null;

    }

    private boolean fix(String[] key, boolean[] isCollided, int intersection){

        SceneObject a, b;

        if(!intersectionList.contains(key)) return false;

        a = intersectionList.get(key)[0];
        b = intersectionList.get(key)[1];

        //System.out.println("huehue2");

        if(isCollided[0] && isCollided[1]){

            if(a.getSpeedX() > 0 && b.getSpeedX() < 0 && X1 <= X2 && XOFF1 >= X2){

                a.setX( a.getX() - intersection );
                b.setX( b.getX() + intersection );

            }
            else if(a.getSpeedX() < 0 && b.getSpeedX() > 0 && XOFF1 >= XOFF2 && X1 <= XOFF2){

                a.setX( a.getX() + intersection );
                b.setX( b.getX() - intersection );

            }
            else if(a.getSpeedY() > 0 && b.getSpeedY() < 0 && Y1 <= Y2 && YOFF1 >= Y2){

                a.setY( a.getY() - intersection ); int I = 0;
                b.setY( b.getY() + intersection );

            }
            else if(a.getSpeedY() < 0 && b.getSpeedY() > 0 && YOFF1 >= YOFF2 && Y1 <= YOFF2){

                a.setY( a.getY() + intersection );
                b.setY( b.getY() - intersection );

            }

        }
        else if(isCollided[0]){

            if(a.getSpeedX() > 0 && X1 <= X2 && XOFF1 >= X2){

                a.setX( a.getX() - intersection );

            }
            else if(a.getSpeedX() < 0 && XOFF1 >= XOFF2 && X1 <= XOFF2){

                a.setX( a.getX() + intersection );

            }
            else if(a.getSpeedY() > 0 && Y1 <= Y2 && YOFF1 >= Y2){

                a.setY( a.getY() - intersection );

            }
            else if(a.getSpeedY() < 0 && YOFF1 >= YOFF2 && Y1 <= YOFF2){

                a.setY( a.getY() + intersection );

            }

        }
        else{

            if(b.getSpeedX() > 0 && X2 <= X1 && XOFF2 >= X1){

                b.setX( b.getX() - intersection );

            }
            else if(b.getSpeedX() < 0 && XOFF2 >= XOFF1 && X2 <= XOFF1){

                b.setX( b.getX() + intersection );

            }
            else if(b.getSpeedY() > 0 && Y2 <= Y1 && YOFF2 >= Y1){

                b.setY( b.getY() - intersection );

            }
            else if(b.getSpeedY() < 0 && YOFF2 >= YOFF1 && Y2 <= YOFF1){

                b.setY( b.getY() + intersection );

            }

        }

        return true;

    }

    private boolean checkIntersections(){

        boolean hasIntersection = false;

        SceneObject a, b;

        for (int i = 0; i < nonStaticObjects.size(); i++) {

            a = nonStaticObjects.get(i);

            //System.out.println("\t" + a.getName());
            //generateLog(a.getX(), a.getY(), a.getXOffset(), a.getYOffset());
            for(int j = 0; j < interactiveObjects.size(); j++){

                b = sceneObjects.get(interactiveObjects.get(j).getName());

                //System.out.println("\t\t" + b.getName());
                //generateLog(b.getX(), b.getY(), b.getXOffset(), b.getYOffset());

                if( b.isColliding() && a.hashCode() != b.hashCode() && areIntersected(a, b) && !intersectionList.contains(a.getName(), b.getName())){

                    //System.out.println("\t\t" + b.getName());
                    //generateLog(b.getX(), b.getY(), b.getXOffset(), b.getYOffset());

                    String[] key = intersectionList.put(a.getName(), b.getName(), a, b);
                    //System.out.println(k);
                    //if(k == 0)System.out.println("\t\t\t [COLLISION] " + intersectionList.get(key)[0].getName() + " ; " + intersectionList.get(key)[1].getName());

                    hasIntersection = true;

                }


            }

        }

        //System.out.println("...");

        return hasIntersection;
    }

    private void generateLog(int x, int y, int xoffset, int yoffset){

        System.out.println( " =============================================================================== " );
        System.out.println( " \t\t(x, y): " + x + " , " + y + " (xoff, yoff): " + xoffset + " , " + yoffset);

        //System.out.println( " \tCollision: " + ( collision ? "TRUE" : "FALSE" ) );

    }

    private int[][] getCollider(SceneObject a, SceneObject b){

        // INFO( A{direção correção} , B{direção, correção} )
        int[][] info = new int[2][2];
        int exception = 0;

        x1 = y1 = xoff1 = yoff1 = x2 = y2 = xoff2 = yoff2 = 0;

        if(a.getHitBoxes() != null && b.getHitBoxes() != null){
            for(HitBox hb1 : a.getHitBoxes()){
                for(HitBox hb2 : b.getHitBoxes()){
                    if(
                            hb1.getRelativeX(a.getX()) < hb2.getRelativeXOffset(b.getX()) &&
                            hb1.getRelativeY(a.getY()) < hb2.getRelativeYOffset(b.getY()) &&
                            hb1.getRelativeXOffset(a.getX()) > hb2.getRelativeX(b.getX()) &&
                            hb1.getRelativeYOffset(a.getY()) > hb2.getRelativeY(b.getY())
                    ) {
                        x1 = hb1.getRelativeX(a.getX());
                        y1 = hb1.getRelativeY(a.getY());
                        xoff1 =  hb1.getRelativeXOffset(a.getX());
                        yoff1 = hb1.getRelativeYOffset(a.getY());
                        x2 = hb2.getRelativeX(b.getX());
                        y2 = hb2.getRelativeY(b.getY());
                        xoff2 = hb2.getRelativeXOffset(b.getX());
                        yoff2 = hb2.getRelativeYOffset(b.getY());
                        break;
                    }
                }
            }
        }
        else if(a.getHitBoxes() != null){
            for(HitBox hb1 : a.getHitBoxes()){
                if(
                        hb1.getRelativeX(a.getX()) < b.getXOffset() &&
                        hb1.getRelativeY(a.getY()) < b.getYOffset() &&
                        hb1.getRelativeXOffset(a.getX()) > b.getX() &&
                        hb1.getRelativeYOffset(a.getY()) > b.getY()
                ) {
                    x1 = hb1.getRelativeX(a.getX());
                    y1 = hb1.getRelativeY(a.getY());
                    xoff1 =  hb1.getRelativeXOffset(a.getX());
                    yoff1 = hb1.getRelativeYOffset(a.getY());
                    x2 = b.getX();
                    y2 = b.getY();
                    xoff2 = b.getXOffset();
                    yoff2 = b.getYOffset();
                    break;
                }
            }
        }
        else if(b.getHitBoxes() != null){
            for(HitBox hb2 : b.getHitBoxes()){
                if(
                        a.getX() < hb2.getRelativeXOffset(b.getX()) &&
                        a.getY() < hb2.getRelativeYOffset(b.getY()) &&
                        a.getXOffset() > hb2.getRelativeX(b.getX()) &&
                        a.getYOffset() > hb2.getRelativeY(b.getY())
                ) {
                    x1 = a.getX();
                    y1 = a.getY();
                    xoff1 =  a.getXOffset();
                    yoff1 = a.getYOffset();
                    x2 = hb2.getRelativeX(b.getX());
                    y2 = hb2.getRelativeY(b.getY());
                    xoff2 = hb2.getRelativeXOffset(b.getX());
                    yoff2 = hb2.getRelativeYOffset(b.getY());
                    break;
                }
            }
        }
        else{
            x1 = a.getX();
            y1 = a.getY();
            xoff1 = a.getXOffset();
            yoff1 = a.getYOffset();
            x2 = b.getX();
            y2 = b.getY();
            xoff2 = b.getXOffset();
            yoff2 = b.getYOffset();
        }


            // HORIZONTAL
        // EAST
        if(a.getSpeedX() > 0 && x1 < x2 && xoff1 >= x2) { //direção

            info[0][0] = ObjectStatus.EAST;
            info[0][1] = Math.abs(xoff1 - x2);

        }
        // WEST
        else if(a.getSpeedX() < 0 && xoff1 > xoff2 && x1 <= xoff2){

            info[0][0] = ObjectStatus.WEST;
            info[0][1] = Math.abs(xoff2 - x1);

        }
            // VERTICAL
        // SOUTH
        else if(a.getSpeedY() > 0 && y1 < y2 && yoff1 >= y2) { //direção

            info[0][0] = ObjectStatus.SOUTH;
            info[0][1] = Math.abs(yoff1 - y2);


        }
        // NORTH
        else if(a.getSpeedY() < 0 && yoff1 > yoff2 && y1 <= yoff2){

            info[0][0] = ObjectStatus.NORTH;
            info[0][1] = Math.abs(yoff2 - y1);

        }
        else {
            //System.out.println("NÃO DEU 1");
            exception++;
        }

        //System.out.println("yoff1: " + yoff1 + ", y2: " + y2);
        //System.out.println("yoff2: " + yoff2 + ", y1: " + y1);

            // HORIZONTAL
        // EAST
        if(b.getSpeedX() > 0 && x2 < x1 && xoff2 >= x1) { //direção

            info[1][0] = ObjectStatus.EAST;
            info[1][1] = Math.abs(xoff2 - x1);

        }
        // WEST
        else if(b.getSpeedX() < 0 && xoff2 > xoff1 && x2 <= xoff1){

            info[1][0] = ObjectStatus.WEST;
            info[1][1] = Math.abs(xoff1 - x2);

        }
            // VERTICAL
        // SOUTH
        else if(b.getSpeedY() > 0 && y2 < y1 && yoff2 >= y1) { //direção

            info[1][0] = ObjectStatus.SOUTH;
            info[1][1] = Math.abs(yoff2 - y1);

        }
        // NORTH
        else if(b.getSpeedY() < 0 && yoff2 > yoff1 && y2 <= yoff1){

            info[1][0] = ObjectStatus.NORTH;
            info[1][1] = Math.abs(yoff1 - y2);

        }
        else{
            //System.out.println("NÃO DEU 2");
            exception++;
        }

        if(exception == 2){





        }

        //System.out.println("dirA:" + info[0][0] + "fixA:" + info[0][1] + "dirB:" + info[1][0] + "fixB:" + info[1][1]);


        return info;
    }

    private boolean areIntersected(SceneObject a, SceneObject b){

        boolean are = false;

        if(a.getHitBoxes() != null && b.getHitBoxes() != null){
            for(HitBox hb1 : a.getHitBoxes()){
                for(HitBox hb2 : b.getHitBoxes()){
                    if(
                            hb1.getRelativeX(a.getX()) < hb2.getRelativeXOffset(b.getX()) &&
                            hb1.getRelativeY(a.getY()) < hb2.getRelativeYOffset(b.getY()) &&
                            hb1.getRelativeXOffset(a.getX()) > hb2.getRelativeX(b.getX()) &&
                            hb1.getRelativeYOffset(a.getY()) > hb2.getRelativeY(b.getY())
                    ) {
                        are = true;
                        break;
                    }
                }
            }
        }
        else if(a.getHitBoxes() != null){
            for(HitBox hb1 : a.getHitBoxes()){
                if(
                        hb1.getRelativeX(a.getX()) < b.getXOffset() &&
                        hb1.getRelativeY(a.getY()) < b.getYOffset() &&
                        hb1.getRelativeXOffset(a.getX()) > b.getX() &&
                        hb1.getRelativeYOffset(a.getY()) > b.getY()
                ) {
                    are = true;
                    break;
                }
            }
        }
        else if(b.getHitBoxes() != null){
            for(HitBox hb2 : b.getHitBoxes()){
                if(
                        a.getX() < hb2.getRelativeXOffset(b.getX()) &&
                        a.getY() < hb2.getRelativeYOffset(b.getY()) &&
                        a.getXOffset() > hb2.getRelativeX(b.getX()) &&
                        a.getYOffset() > hb2.getRelativeY(b.getY())
                ) {
                    are = true;
                    break;
                }
            }
        }
        else{
            are = a.getX() < b.getXOffset() && a.getY() < b.getYOffset() && a.getXOffset() > b.getX() && a.getYOffset() > b.getY();
        }

        return are;
    }

    private boolean areOpposites(int[][] matrix){
        return (
                (matrix[0][0] == ObjectStatus.EAST && matrix[1][0] == ObjectStatus.WEST) ||
                (matrix[0][0] == ObjectStatus.WEST && matrix[1][0] == ObjectStatus.EAST) ||
                (matrix[0][0] == ObjectStatus.NORTH && matrix[1][0] == ObjectStatus.SOUTH) ||
                (matrix[0][0] == ObjectStatus.SOUTH && matrix[1][0] == ObjectStatus.NORTH)
        );
    }



}
