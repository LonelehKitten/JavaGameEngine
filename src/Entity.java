import java.awt.*;

public class Entity extends Solid{

    private int type;

    private String name;
    private int breed;
    private int work;

    public Entity(String name, int type, int breed, int work, int x, int y, int xaxis, int yaxis, int width, int height, Color color){
        super(x, y, xaxis, yaxis, width, height, color);
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.work = work;
    }


}
