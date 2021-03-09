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
public class BTreeName {
    NodeName root = null;
    int minChildren = 2;
    int maxChildren = 3;
    
    public String toString(){
        String display="";
        Queue<NodeName> queue = new LinkedList<NodeName>();
        this.root.level = 0;
        queue.add(this.root);
        int lastLevel = 0;
        NodeName current;
        while(!queue.isEmpty()){
            current = queue.remove();
            if(current.level>lastLevel){
                lastLevel = current.level;
                display += "\n";
            }
            
            display += current.toString();
            for(NodeName children : current.children){
                children.level = current.level+1;
                queue.add(children);
            }
            
            display += ";;";
           
        }
        return display;
    }
    
    
    public void printTree(){

        Queue<NodeName> queue = new LinkedList<NodeName>();
        this.root.level = 0;
        queue.add(this.root);
        int lastLevel = 0;
        NodeName current;
        while(!queue.isEmpty()){
            current = queue.remove();
            if(current.level>lastLevel){
                lastLevel = current.level;
                System.out.println();
            }
            
            System.out.print(current.toString());
            for(NodeName children : current.children){
                children.level = current.level+1;
                queue.add(children);
            }
            
            System.out.print(";;");
           
        }

    }
    
    
    boolean search(String key){
        if(this.root == null){
            return false;
        }
        NodeName current = this.root;
        
        boolean found = false;
        while(!found){
            if(current.keys.contains(key)){
                found = true;
                return true;
            }
            if(current.isLeaf()){
                return false;
            }
            int i=0;
            for(String nodeKey : current.keys){
                if(key.compareTo(nodeKey)>0){
                    i++;
                }
            }
            current = current.children.get(i); 
            
        }
        return false;
        
    }
    
    public boolean insert(String key){
        if(this.root == null){
            NodeName node = new NodeName(null, this.minChildren, this.maxChildren);
            node.keys.add(key);
            this.root = node;
            return true;
        }
        
        NodeName current = this.root;
        boolean foundLeaf = false;
        while(!foundLeaf){
            if(current.keys.contains(key)){
                return false;
            }
            if(current.isLeaf()){
                foundLeaf = true;
            }
            else{
                int i=0;
                for(String nodeKey : current.keys){
                    if(key.compareTo(nodeKey)>1){
                        i++;
                    }
                }
                current = current.children.get(i);
            }
             
        }
        
        current.keys.add(key);
        current.sortKeys();
        if(current.isLegal()){
            return true;
        }
        int m;
        String median;
        NodeName node;
        while(!current.isLegal()){
            median = current.keys.get(1);
            node = new NodeName(null, this.minChildren, this.maxChildren);
            node.keys.add(median);
            
            NodeName child1 = new NodeName(null, this.minChildren, this.maxChildren);
            child1.keys.add(current.keys.get(0));
            NodeName child2 = new NodeName(null, this.minChildren, this.maxChildren);
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

    private NodeName fuseNodes(NodeName parent, NodeName node) {
        if(parent == null){
            this.root = node;
            return this.root;
        }
        for(String key : node.keys){
            parent.keys.add(key);
        }
        parent.sortKeys();
        int index = 0;
        for(NodeName child: parent.children){
            if(!child.keys.contains(node.keys.get(0))){
                index++;
            }
            else{
                break;
            }
        }
        parent.children.remove(index);
        for(NodeName child: node.children){
            parent.addChild(child);
        }
        
        parent.children.sort(parent);
        
        return parent;
    }
}
