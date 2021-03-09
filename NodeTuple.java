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
public class NodeTuple implements Comparator<NodeTuple>{
    
    private static TupleComparator tupleComp = new TupleComparator();
    public int minChildren;
    public int maxChildren;
    public NodeTuple parent = null;
    public ArrayList<Tuple> keys;
    public ArrayList<NodeTuple> children;
    int level=0;
    
    
    public NodeTuple(NodeTuple parent, int minChildren, int maxChildren){
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
        this.keys.sort(tupleComp);
    }
    
    public void addChild(NodeTuple child){
        child.parent = this;
        this.children.add(child);
        this.children.sort(this);
    }
   

    @Override
    public String toString() {
        String display = "[";
        for(Tuple key : this.keys){
            display += "("+key.name+"|"+key.grade + "),";
        }
        display += "]";
        
        return display;
    }

    
    @Override
    public int compare(NodeTuple o1, NodeTuple o2) {
        return o1.keys.get(0).name.compareTo(o2.keys.get(0).name);
    }
    
    
    
    
}




class TupleComparator implements Comparator<Tuple>{

    @Override
    public int compare(Tuple o1, Tuple o2) {
        return o1.name.compareTo(o2.name);
    }

}