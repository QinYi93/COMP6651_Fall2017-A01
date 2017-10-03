package pa1;

import java.util.HashMap;

/**
 * This class
 *
 * @author Yi Qin
 * @date 2017-10-02
 */
public class Node {
    private String name;
    private int distanceOfParent;
    private Node parent;
    private HashMap<Integer, Node> childs = new HashMap<>();

    public Node(String name, int distanceOfParent) {
        this.name = name;
        this.distanceOfParent = distanceOfParent;
    }

    public Node() {
        
    }
    
    public boolean hasChild(int key){
        return childs.containsKey(key); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getdistanceOfParent() {
        return distanceOfParent;
    }

    public void setdistanceOfParent(int distanceOfParent) {
        this.distanceOfParent = distanceOfParent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChilds(int i) {
        return childs.get(i);
    }

    public HashMap<Integer, Node> getChilds(){
        return childs;
    }

    public void setChilds(int d , Node node) {
        this.childs.put(d, node);
    }
}
