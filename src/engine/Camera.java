package engine;

import java.util.function.Supplier;

public class Camera {

    private static int x, y, acceleration, defaultAcceleration;
    private static boolean is_collided;

    private static CameraCollisionFactor cameraCollisionFactor;

    public Camera(CameraCollisionFactor cameraCollisionFactor){
        Camera.cameraCollisionFactor = cameraCollisionFactor;
        init();
    }

    private void init(){
        x = IConfig.MONITOR_WIDTH/2;
        y = IConfig.MONITOR_HEIGHT/2;
        defaultAcceleration = 10;
    }

    public static int getAccelerationStatus(int[] collision_data){
      if(collision_data[0] == 1) is_collided = true;

      int reference = 0;

      if(collision_data[2] == 1)
        reference = cameraCollisionFactor.getY();
      if(collision_data[2] == 2)
        reference = cameraCollisionFactor.getXOffset();
      if(collision_data[2] == 3)
        reference = cameraCollisionFactor.getYOffset();
      if(collision_data[2] == 4)
        reference = cameraCollisionFactor.getX();

      return is_collided ? reference - collision_data[1] : defaultAcceleration;
    }

    public static int getAcceleration(){
        return acceleration;
    }

    public static void setAcceleration(int determined_acceleration){
        acceleration = determined_acceleration;
    }

    public static int getDefaultAcceleration() {
        return defaultAcceleration;
    }

    public static void setDefaultAcceleration(int defaultAcceleration) {
        Camera.defaultAcceleration = defaultAcceleration;
    }

    public static boolean getCollisionStatus(){ return is_collided;}

    public static void resetCollisionStatus(){ is_collided = false;}

    public static int getX(){ return x; }

    public static int getY(){ return y; }

//    public static int getPlayerX(){
//      return getPlayerXMethod.get();
//    }
//
//    public static int getPlayerY(){
//      return getPlayerYMethod.get();
//    }
//
//    public static int getPlayerXOffset(){
//      return getPlayerXOffsetMethod.get();
//    }
//
//    public static int getPlayerYOffset(){
//        return getPlayerYOffsetMethod.get();
//    }
//
//    private static Supplier<Integer> getPlayerXMethod;
//    private static Supplier<Integer> getPlayerYMethod;
//
//    private static Supplier<Integer> getPlayerXOffsetMethod;
//    private static Supplier<Integer> getPlayerYOffsetMethod;
//
//    public static void givePlayerX(Supplier<Integer> getPlayerXMethod){
//        Camera.getPlayerXMethod = getPlayerXMethod;
//    }
//
//    public static void givePlayerY(Supplier<Integer> getPlayerYMethod){
//        Camera.getPlayerYMethod = getPlayerYMethod;
//    }
//
//    public static void givePlayerXOffset(Supplier<Integer> getPlayerXOffsetMethod){
//        Camera.getPlayerXOffsetMethod = getPlayerXOffsetMethod;
//    }
//
//    public static void givePlayerYOffset(Supplier<Integer> getPlayerYOffsetMethod){
//        Camera.getPlayerYOffsetMethod = getPlayerYOffsetMethod;
//    }

    public static CameraCollisionFactor getCameraCollisionFactor() {
        return cameraCollisionFactor;
    }

    public static void setCameraCollisionFactor(CameraCollisionFactor cameraCollisionFactor) {
        Camera.cameraCollisionFactor = cameraCollisionFactor;
    }
}
