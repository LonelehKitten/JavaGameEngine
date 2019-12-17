package engine;

public interface ObjectStatus {

    /* ==== NULL ==== */
    int NULL = 0;

    /* ==== DIRECTIONS ==== */
    int NORTH = 1;
    int SOUTH = 2;
    int WEST = 3;
    int EAST = 4;

    /* ==== PLACES ==== */
    int VILLAGE = 5;
    int CITY = 6;
    int MEADOW = 7;
    int FOREST = 8;
    int CAVE = 9;

    /* ==== GATES ==== */
    int PLACE = 10;
    int SCENE = 11;

    /* ==== BREEDS ==== */
    int HUMAN = 12;
    int SLIME = 13;
    int ZOMBIE = 14;

    /* ==== ENTITY TYPE ==== */
    int NPC = 40;
    int MOB = 41;

    /* ==== WORKS ==== */
    int VILLAGER = 100;
    int BLACKSMITH = 101;
    int MERCHANT = 102;
    int MINER = 103;
    int CONSTRUCTOR = 104;
    int INVENTOR = 105;
    int FISHERMAN = 106;
    int GUARDIAN = 107;
    int SOLDIER = 108;

    /* ==== RENDER FLAGS ==== */
    int ONLY_ONE = 1000;
    int TILED = 1001;
    int ONLY_ONE_ANIMATED = 1002;
    int TILED_ANIMATED = 1003;


}
