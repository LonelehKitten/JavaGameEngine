public class Gate{

    public static String[] now = new String[2];
    public static String[] going_to = new String[2];
    public static int direction, instance;

    private String[] side1 = new String[2];
    private String[] side2 = new String[2];

    private int type;

    public Gate(String[] side1, String[] side2){//, int type){
        this.side1 = side1;
        this.side2 = side2;
        //this.type = type;
    }

    public String[] getSide1() {
        return side1;
    }

    public String[] getSide2() {
        return side2;
    }

    public int getType() {
        return type;
    }
}
