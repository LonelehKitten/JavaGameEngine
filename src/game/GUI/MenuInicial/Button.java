
package game.GUI.MenuInicial;

public class Button {
    private String name;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isPressed;
    
    public Button(String name, int x, int y, int height, int width){
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        
        isPressed = false;
    }
    
    public void setPressed(boolean b){ isPressed = b;}
    public boolean isPressed(){ return isPressed; }
    
    public String getName(){ return name; } 
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getHeight(){ return height; }
    public int getWidth(){ return width; }
}
