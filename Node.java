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
public class Node implements Comparator<Node>{
    private static IntegerComparator intComp = new IntegerComparator();
    public int minChildren;
    public int maxChildren;
    public Node parent = null;
    public ArrayList<Integer> keys;
    public ArrayList<Node> children;
    int level=0;
    
    
    public Node(Node parent, int minChildren, int maxChildren){
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
        this.keys.sort(intComp);
    }
    
    public void addChild(Node child){
        child.parent = this;
        this.children.add(child);
        this.children.sort(this);
    }
   

    @Override
    public String toString() {
        String display = "[";
        for(int key : this.keys){
            display += key + ",";
        }
        display += "]";
        
        return display;
    }

    @Override
    public int compare(Node o1, Node o2) {
        return o1.keys.get(0) > o2.keys.get(0)? 1:-1;
    }
    
    
    
    
}
class IntegerComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1>o2?1:o1<o2?-1:0;
    }

}
