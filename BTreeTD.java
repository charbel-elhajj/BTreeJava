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
public class BTreeTD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        BTreeName tree = new BTreeName();
//        
//        tree.insert("Charbel");
//        tree.insert("Joseph");
//        tree.insert("Elie");
//        tree.insert("Elias");


        
//        BTree tree = new BTree();
//        
//        tree.insert(3);
//        tree.insert(3);
//        tree.insert(5);
//        tree.insert(6);
//
//
//        tree.insert(4);
//        tree.insert(0);
//        tree.insert(-1);
//        tree.insert(-2);
//        tree.insert(8);
//        tree.insert(10);
//        tree.insert(11);
//        tree.insert(12);


        BTreeTuple tree = new BTreeTuple();
        
        tree.insert("Charbel ElHajj",20);
        tree.insert("Joseph AbiRached",17);
        tree.insert("Elie BouSaba",15);
        tree.insert("Elias Zouein",6);
        tree.insert("Karim Mrad",12);
        tree.insert("Mario Faddoul", 6.5f);
        tree.insert("Anthony Dfouny", 13.5f);
        tree.insert("Anthony Dfouny", 16.5f);
//        Tuple t = tree.search("Joseph AbiRached");
//        System.out.println(t);


        
        System.out.println(tree);
    }
    
}
