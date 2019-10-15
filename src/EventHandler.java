public class EventHandler {

    private static boolean up, down, left, right;

    public EventHandler(){
        init();
    }

    private void init(){
        up = down = left = right = false;
    }

    public static boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public static boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public static boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public static boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
