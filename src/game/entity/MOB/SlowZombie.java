package game.entity.MOB;

public class SlowZombie extends Zombie{

    public SlowZombie(int id, int x, int y, int width, int height) {
        super(id, x, y, width, height, "assets/imgs/zombie3.png");
        init();
    }

    private void init(){
        this.damage = 20;
        this.hp = 150;
        this.acceleration = 0.6;
    }

}
