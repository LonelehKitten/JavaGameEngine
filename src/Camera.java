public class Camera {

    private static int x, y, acceleration, default_acceleration;
    private static boolean is_collided;

    public Camera(){
        init();
    }

    private void init(){
        x = IConfig.MONITOR_WIDTH/2;
        y = IConfig.MONITOR_HEIGHT/2;
        default_acceleration = 10;
    }

    public static int getAccelerationStatus(int[] collision_data){
      if(collision_data[0] == 1) is_collided = true;

      int reference = 0;

      if(collision_data[2] == 1)
        reference = getPlayerY();
      if(collision_data[2] == 2)
        reference = getPlayerXOffset();
      if(collision_data[2] == 3)
        reference = getPlayerYOffset();
      if(collision_data[2] == 4)
        reference = getPlayerX();

      return is_collided ? reference - collision_data[1] : default_acceleration;
    }

    public static int getAcceleration(){
        return acceleration;
    }

    public static void setAcceleration(int determined_acceleration){
        acceleration = determined_acceleration;
    }

    public static boolean getCollisionStatus(){ return is_collided;}

    public static void resetCollisionStatus(){ is_collided = false;}

    public static int getX(){ return x; }

    public static int getY(){ return y; }

    public static int getPlayerX(){
      return x-16;
    }

    public static int getPlayerY(){
      return y-16;
    }

    public static int getPlayerXOffset(){
      return x+16;
    }

    public static int getPlayerYOffset(){
      return y+16;
    }

}
