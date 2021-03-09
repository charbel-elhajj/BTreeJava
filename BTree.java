/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreetd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Charbel
 */
public class BTree {
    Node root = null;
    int minChildren = 2;
    int maxChildren = 3;
    
    public String toString(){
        String display="";
        Queue<Node> queue = new LinkedList<Node>();
        this.root.level = 0;
        queue.add(this.root);
        int lastLevel = 0;
        Node current;
        while(!queue.isEmpty()){
            current = queue.remove();
            if(current.level>lastLevel){
                lastLevel = current.level;
                display += "\n";
            }
            
            display += current.toString();
            for(Node children : current.children){
                children.level = current.level+1;
                queue.add(children);
            }
            
            display += ";;";
           
        }
        return display;
    }
    
    
    public void printTree(){

        Queue<Node> queue = new LinkedList<Node>();
        this.root.level = 0;
        queue.add(this.root);
        int lastLevel = 0;
        Node current;
        while(!queue.isEmpty()){
            current = queue.remove();
            if(current.level>lastLevel){
                lastLevel = current.level;
                System.out.println();
            }
            
            System.out.print(current.toString());
            for(Node children : current.children){
                children.level = current.level+1;
                queue.add(children);
            }
            
            System.out.print(";;");
           
        }

    }
    
    
    boolean search(int key){
        if(this.root == null){
            return false;
        }
        Node current = this.root;
        
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
            for(int nodeKey : current.keys){
                if(key>nodeKey){
                    i++;
                }
            }
            current = current.children.get(i); 
            
        }
        return false;
        
    }
    
    public boolean insert(int key){
        if(this.root == null){
            Node node = new Node(null, this.minChildren, this.maxChildren);
            node.keys.add(key);
            this.root = node;
            return true;
        }
        
        Node current = this.root;
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
                for(int nodeKey : current.keys){
                    if(key>nodeKey){
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
        int median;
        Node node;
        while(!current.isLegal()){
            median = current.keys.get(1);
            node = new Node(null, this.minChildren, this.maxChildren);
            node.keys.add(median);
            
            Node child1 = new Node(null, this.minChildren, this.maxChildren);
            child1.keys.add(current.keys.get(0));
            Node child2 = new Node(null, this.minChildren, this.maxChildren);
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

    private Node fuseNodes(Node parent, Node node) {
        if(parent == null){
            this.root = node;
            return this.root;
        }
        for(int key : node.keys){
            parent.keys.add(key);
        }
        parent.sortKeys();
        int index = 0;
        for(Node child: parent.children){
            if(!child.keys.contains(node.keys.get(0))){
                index++;
            }
            else{
                break;
            }
        }
        parent.children.remove(index);
        for(Node child: node.children){
            parent.addChild(child);
        }
        
        parent.children.sort(parent);
        
        return parent;
    }
    
    
}


