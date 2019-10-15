import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Place {

    private String name;
    private int type;

    private Map<String, Place> path = new HashMap<>();
    private Map<String, Scene> scene = new HashMap<>();

    public Place(String name, int type){
        this.name = name;
        this.type = type;
    }

    public void update(){

        for (String name : scene.keySet()) {
            if(scene.get(name).isCurrent()) {
                scene.get(name).update();
                break;
            }
        }

    }

    public void render(Graphics2D g2d){

        for (String name : scene.keySet()) {
            if(scene.get(name).isCurrent()) {
                scene.get(name).render(g2d);
                break;
            }
        }

    }

    public void addPath(Place place){
        path.put(place.getName(), place);
    }

    public Place getPath(String name){
        return path.get(name);
    }

    public Set getPathList(){
        return path.keySet();
    }


    public Scene addScene(String name, int xaxis, int yaxis){
        scene.put(name, new Scene(xaxis, yaxis));
        return scene.get(name);
    }

    public Scene getScene(String name){
        return scene.get(name);
    }

    public Set getSceneList(){
        return scene.keySet();
    }


    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    

}
