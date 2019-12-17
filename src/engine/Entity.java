package engine;

import java.awt.*;

public class Entity extends Solid{

    private int type;

    public Entity(String name, int type, int x, int y, int xaxis, int yaxis, int width, int height, Color color){
        super(name, x, y, xaxis, yaxis, width, height, color);
        this.type = type;
    }

    public Entity(String name, int type, int x, int y, int xaxis, int yaxis, int width, int height, Sprite sprite, int render_flag){
        super(name, x, y, xaxis, yaxis, width, height, sprite, render_flag);
        this.type = type;
    }

}
