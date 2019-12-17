package engine;

public class Gate{

    public static String[] now = new String[2];
    public static String[] going_to = new String[2];
    public static int direction, instance;

    private String[] side1Address;
    private String[] side2Address;

    private SceneObject gateSide1;
    private SceneObject gateSide2;

    private int type;

    public Gate(String[] side1Address, String[] side2Address, SceneObject gateSide1, SceneObject gateSide2){//, int type){
        this.side1Address = side1Address;
        this.side2Address = side2Address;
        this.gateSide1 = gateSide1;
        this.gateSide2 = gateSide2;
    }

    public String[] getSide1Address() {
        return side1Address;
    }

    public String[] getSide2Address() {
        return side2Address;
    }

    public SceneObject getGateSide1() {
        return gateSide1;
    }

    public SceneObject getGateSite2() {
        return gateSide2;
    }

    public SceneObject getCurrentGateSide(GateableObject gateableObject){
        System.out.println("passou aki");
        if(gateableObject.hashCode() == gateSide1.hashCode()) return gateSide1;
        else if(gateableObject.hashCode() == gateSide2.hashCode()) return gateSide2;
        System.out.println("hashcode nunca é igual");
        return null;
    }


    public int getType() {
        return type;
    }
}
