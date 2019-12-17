package game.entity.MOB;

public class NormalZombie extends Zombie{
    public NormalZombie(int id, int x, int y, int width, int height) {
        super(id, x, y, width, height, "assets/imgs/zombie2.png");
        init();
    }

    private void init(){
        this.damage = 10;
        this.hp = 100;
        this.acceleration = 1;
    }
}
