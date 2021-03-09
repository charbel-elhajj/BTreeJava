/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreetd;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Charbel
 */
public class BTreeTuple {
    NodeTuple root = null;
    int minChildren = 2;
    int maxChildren = 3;
    
    public String toString(){
        String display="";
        Queue<NodeTuple> queue = new LinkedList<NodeTuple>();
        this.root.level = 0;
        queue.add(this.root);
        int lastLevel = 0;
        NodeTuple current;
        while(!queue.isEmpty()){
            current = queue.remove();
            if(current.level>lastLevel){
                lastLevel = current.level;
                display += "\n";
            }
            
            display += current.toString();
            for(NodeTuple children : current.children){
                children.level = current.level+1;
                queue.add(children);
            }
            
            display += ";;";
           
        }
        return display;
    }
    
    
    public void printTree(){

        Queue<NodeTuple> queue = new LinkedList<NodeTuple>();
        this.root.level = 0;
        queue.add(this.root);
        int lastLevel = 0;
        NodeTuple current;
        while(!queue.isEmpty()){
            current = queue.remove();
            if(current.level>lastLevel){
                lastLevel = current.level;
                System.out.println();
            }
            
            System.out.print(current.toString());
            for(NodeTuple children : current.children){
                children.level = current.level+1;
                queue.add(children);
            }
            
            System.out.print(";;");
           
        }

    }
    
    
    public Tuple search(String key){
        if(this.root == null){
            return null;
        }
        NodeTuple current = this.root;
        Tuple t = new Tuple(key,0);
        boolean found = false;
        while(!found){
            if(current.keys.contains(t)){
                for(Tuple tuple : current.keys){
                    if(tuple.name == t.name){
                        return tuple;
                    }
                }
                return null;
            }
            if(current.isLeaf()){
                return null;
            }
            int i=0;
            for(Tuple nodeKey : current.keys){
                if(key.compareTo(nodeKey.name)>0){
                    i++;
                }
            }
            current = current.children.get(i); 
            
        }
        return null;
        
    }
    
    public boolean insert(String name, float grade){
        return insert(new Tuple(name, grade));
    }
    
    public boolean insert(Tuple tuple){
        if(this.root == null){
            NodeTuple node = new NodeTuple(null, this.minChildren, this.maxChildren);
            node.keys.add(tuple);
            this.root = node;
            return true;
        }
        
        NodeTuple current = this.root;
        boolean foundLeaf = false;
        while(!foundLeaf){
            if(current.keys.contains(tuple)){
                return false;
            }
            if(current.isLeaf()){
                foundLeaf = true;
            }
            else{
                int i=0;
                for(Tuple nodeKey : current.keys){
                    if(tuple.name.compareTo(nodeKey.name)>1){
                        i++;
                    }
                }
                current = current.children.get(i);
            }
             
        }
        
        current.keys.add(tuple);
        current.sortKeys();
        if(current.isLegal()){
            return true;
        }
        int m;
        Tuple median;
        NodeTuple node;
        while(!current.isLegal()){
            median = current.keys.get(1);
            node = new NodeTuple(null, this.minChildren, this.maxChildren);
            node.keys.add(median);
            
            NodeTuple child1 = new NodeTuple(null, this.minChildren, this.maxChildren);
            child1.keys.add(current.keys.get(0));
            NodeTuple child2 = new NodeTuple(null, this.minChildren, this.maxChildren);
            child2.keys.add(current.keys.get(current.keys.size()-1));
            
            if(!current.children.isEmpty()){
                child1.addChild(current.children.get(0));
                child1.addChild(current.children.get(1));
                child2.addChild(current.children.get(2));
                child2.addChild(current.children.get(3));
            }
            node.addChild(child1);
            node.addChild(child2);
            current = this.fuseNodes(current.parent, node);
        }
        
        
        return false;
        
    }

    private NodeTuple fuseNodes(NodeTuple parent, NodeTuple node) {
        if(parent == null){
            this.root = node;
            return this.root;
        }
        for(Tuple key : node.keys){
            parent.keys.add(key);
        }
        parent.sortKeys();
        int index = 0;
        for(NodeTuple child: parent.children){
            if(!child.keys.contains(node.keys.get(0))){
                index++;
            }
            else{
                break;
            }
        }
        parent.children.remove(index);
        for(NodeTuple child: node.children){
            parent.addChild(child);
        }
        
        parent.children.sort(parent);
        
        return parent;
    }
}


