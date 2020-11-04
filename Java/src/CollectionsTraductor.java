
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Camilo
 */
public class CollectionsTraductor {
    
    
    //private String state_original;              //Estado original del AFD
    private String collection;                  //Nuevo conjunto de estados ej. {s1,s2,s3} = X
    private ArrayList<String> states_final;     //estados finales (aceptado) del AFD
    private ArrayList<String> states_original;  //Conjunto K de estados del AFD
    private final String[] statesAlph = {"A","B","C","D","E","F","G","H","I","J","K","L",
        "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private ArrayList<StringBuilder> collections;
    private ArrayList<String> newState;
    private int stateAlph_take=0; //ninguna letra del alfabeto usada
    
    public CollectionsTraductor(ArrayList<String> states_original, ArrayList<String> states_final) {
        
        collections = new ArrayList<>();
        newState = new ArrayList<>();
        this.states_final = states_final;
        fillTable(states_original);                      
    }
    
    private void fillTable(ArrayList<String> states_original){
        StringBuilder set_accetp = new StringBuilder();     //set de aceptados
        StringBuilder set_nonaccept= new StringBuilder();  //set de no aceptados
        for (String q : states_original) {
            if(states_final.contains(q)){
                set_accetp.append(q);
            }
            else{
                set_nonaccept.append(q);                       
            }
        }      
        addCollection(set_accetp);
        addCollection(set_nonaccept);             
    }
    
    public void separateTable(String[] states_out){
        
        StringBuilder newSet = new StringBuilder();
        
        for (String q : states_out) {
            
        }
    }
    public void addCollection(StringBuilder set){
        collections.add(set);
        newState.add(statesAlph[stateAlph_take++]);
    }
    
    public void splitCollection(String[] states_out, int index){

        StringBuilder set = collections.get(index);
        StringBuilder newSet = new StringBuilder();
        int indexOf;
        for (String q : states_out) {
            indexOf = set.indexOf(q);
            set.deleteCharAt(indexOf);
            newSet.append(q);
        }
        addCollection(newSet);
    }
    public void getIndexCollection(){
        
    }
    
    public void getCollection(){
        
    }
    public void getState(){
        
    }
    
    public ArrayList<StringBuilder> getCollections(){
        return collections;
    }
    
    public void printCollection(){
        System.out.println("Imprimiendo coleccion..");
        int i = 0;
        for (StringBuilder set : collections) {
            System.out.println(set.toString()+" -> "+newState.get(i++));
            
        }
    }
    
    public String getStateOf(String state){
        for (int i = 0; i < collections.size(); i++) {
           String set = collections.get(i).toString();
           if(set.contains(state)){
               return newState.get(i);           
           }
        }
        return null;
    }
    
    
}
