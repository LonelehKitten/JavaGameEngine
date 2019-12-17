package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntersectionList{

    private Map<String[], SceneObject[]> pair = new HashMap<>();

    public String[] put(String a, String b, SceneObject A, SceneObject B){
        String[] key = new String[]{a, b};
        //if(this.pair.containsKey(key) || this.pair.containsKey(key)) return null;
        pair.put(key, new SceneObject[]{A, B});
        return key;
    }

    public SceneObject[] get(String a, String b){
        if(pair.containsKey(new String[]{a, b})) return pair.get(new String[]{a, b});
        else if(pair.containsKey(new String[]{b, a})) return pair.get(new String[]{b, a});
        return null;
    }

    public SceneObject[] get(String[] key){
        if(pair.containsKey(key)) return pair.get(key);

        return null;
    }

    public void remove(String[] key){
        pair.remove(key);
    }

    public void clear(){
        pair.clear();
    }

//    public String getKeyA(String i, String ii){
//        if(pair.containsKey(i+ii)) return i;
//        else if(pair.containsKey(ii+i)) return ii;
//        return null;
//    }
//
//    public String getKeyB(String i, String ii){
//        if(pair.containsKey(i+ii)) return ii;
//        else if(pair.containsKey(ii+i)) return i;
//        return null;
//    }

    public Set<String[]> getKeySet(){
        return pair.keySet();
    }

    public int getSize(){
        return pair.size();
    }

    public boolean contains(String a, String b){
        boolean has = false;

        for(String[] key : pair.keySet()){
            if( (key[0].equals(a) && key[1].equals(b)) || (key[0].equals(b) && key[1].equals(a)) ){
                has = true;
                break;
            }
        }

        return has;
    }

    public  boolean contains(String[] key){
        return pair.containsKey(key);
    }


}
