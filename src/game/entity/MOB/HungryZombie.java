package game.entity.MOB;

public class HungryZombie extends Zombie{

    public HungryZombie(int id, int x, int y, int width, int height) {
        super(id, x, y, width, height, "assets/imgs/zombie1.png");
        init();
    }

    private void init(){
        this.damage = 15;
        this.hp = 50;
        this.acceleration = 1.4;
    }
}
