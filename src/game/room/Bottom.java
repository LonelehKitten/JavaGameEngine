package game.room;

import game.room.object.Elevator;

import java.awt.*;

public class Bottom extends Room {

    public Bottom(String name, int xaxis, int yaxis) {
        super(name, xaxis, yaxis);
    }

    @Override
    protected void init() {

        addBackground(new Elevator("elevator-background", -162, -189, 325, 377));

        addSolid("north-limit", -162, -189, 325, 138, new Color(0, 0, 0, 0), false);
        addSolid("south-limit", -162, 189, 325, 20, new Color(0, 0, 0, 0), false);
        addSolid("west-limit", -182, -189, 20, 377, new Color(0, 0, 0, 0), false);
        addSolid("east-limit", 162, -189, 20, 377, new Color(0, 0, 0, 0), false);

    }

    @Override
    protected void resetZombies() {}
}
