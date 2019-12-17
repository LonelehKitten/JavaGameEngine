package engine;

public class GameSystem {

    private static int mouseX, mouseY, mouseXPressed, mouseYPressed, key;
    private static boolean rightButtonPressed, leftButtonPressed, someKeyPressed;

    public static int getMouseX() {
        return mouseX;
    }

    public static void setMouseX(int mouseX) {
        GameSystem.mouseX = mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static void setMouseY(int mouseY) {
        GameSystem.mouseY = mouseY;
    }

    public static int getMouseXPressed() {
        return mouseXPressed;
    }

    public static void setMouseXPressed(int mouseXPressed) {
        GameSystem.mouseXPressed = mouseXPressed;
    }

    public static int getMouseYPressed() {
        return mouseYPressed;
    }

    public static void setMouseYPressed(int mouseYPressed) {
        GameSystem.mouseYPressed = mouseYPressed;
    }

    public static boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public static void setRightButtonPressed(boolean rightButtonPressed) {
        GameSystem.rightButtonPressed = rightButtonPressed;
    }

    public static boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public static void setLeftButtonPressed(boolean leftButtonPressed) {
        GameSystem.leftButtonPressed = leftButtonPressed;
    }

    public static boolean isSomeKeyPressed() {
        return someKeyPressed;
    }

    public static void setSomeKeyPressed(boolean someKeyPressed) {
        GameSystem.someKeyPressed = someKeyPressed;
    }

    public static int getKey() {
        return key;
    }

    public static void setKey(int key) {
        GameSystem.key = key;
    }
}