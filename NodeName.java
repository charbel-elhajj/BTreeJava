/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreetd;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Charbel
 */
public class NodeName implements Comparator<NodeName>{
    private static StringComparator stringComp = new StringComparator();
    public int minChildren;
    public int maxChildren;
    public NodeName parent = null;
    public ArrayList<String> keys;
    public ArrayList<NodeName> children;
    int level=0;
    
    
    public NodeName(NodeName parent, int minChildren, int maxChildren){
        this.parent = parent;
        this.maxChildren = maxChildren;
        this.minChildren = minChildren;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }
    
    public boolean isLeaf(){
        return this.children.isEmpty();
    }
    
    public boolean isLegal(){
        return this.keys.size() <= this.maxChildren-1;
    }
    
    public void sortKeys(){
        this.keys.sort(stringComp);
    }
    
    public void addChild(NodeName child){
        child.parent = this;
        this.children.add(child);
        this.children.sort(this);
    }
   

    @Override
    public String toString() {
        String display = "[";
        for(String key : this.keys){
            display += key + ",";
        }
        display += "]";
        
        return display;
    }

    @Override
    public int compare(NodeName o1, NodeName o2) {
        return o1.keys.get(0).compareTo(o2.keys.get(0));
    }
    
    
    
    
}
class StringComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

}

