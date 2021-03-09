/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreetd;

/**
 *
 * @author Charbel
 */
public class Tuple {
    
    public String name;
    public float grade;
    public Tuple(String name, float grade){
        this.grade = grade;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Tuple){
            Tuple t = (Tuple)o;
            return this.name.equals(t.name);
        }
        return false;
        
    }
    
    @Override
    public String toString(){
        return this.name+"|"+this.grade;
    }

}
