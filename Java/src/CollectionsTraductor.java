
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class CollectionsTraductor {

    //private String state_original;              //Estado original del AFD
    private String collection;                  //Nuevo conjunto de estados ej. {s1,s2,s3} = X
    private ArrayList<String> states_final;     //estados finales (aceptado) del AFD
    private ArrayList<String> states_original;  //Conjunto K de estados del AFD
    private final String[] statesAlph = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ArrayList<StringBuilder> collections;
    private ArrayList<String> id_collection;
    private int stateAlph_take = 0; //ninguna letra del alfabeto usada

    public CollectionsTraductor(ArrayList<String> states_original, ArrayList<String> states_final) {

        collections = new ArrayList<>();
        id_collection = new ArrayList<>();
        this.states_final = states_final;
        fillTable(states_original);
    }

    private void fillTable(ArrayList<String> states_original) {
        StringBuilder set_accetp = new StringBuilder();     //set de aceptados
        StringBuilder set_nonaccept = new StringBuilder();  //set de no aceptados
        for (String q : states_original) {
            if (states_final.contains(q)) {
                set_accetp.append(q);
            } else {
                set_nonaccept.append(q);
            }
        }
        addCollection(set_accetp);
        addCollection(set_nonaccept);
    }

    public void addCollection(StringBuilder set) {
        collections.add(set);
        id_collection.add(statesAlph[stateAlph_take++]);
    }

    public void splitCollection(String[] states_out) {
//        System.out.println("Comenzando slip con states_out: ");
//        for (String string : states_out) {
//            System.out.println(string);
//        }
        int index=-1;
        StringBuilder newSet = new StringBuilder();
        for (int i = 0; i < collections.size(); i++) {             
            String x = states_out[0];
            String y = collections.get(i).toString();
               
            if(contain(x,y)){
                //System.out.println("x:"+x +" y:"+y+" iguales :)");
                index =  i;
                break;
            }
        }
        if(index!=-1){
            StringBuilder set = collections.get(index);
            //System.out.println("set: "+set);
            int indexOf;   
            boolean first = true;
            for (String q : states_out) {  
                //System.out.println("q: "+q);
                for (int i = 0; i < q.length(); i++) {                 
                    char q_atomic = q.charAt(i);
                    //System.out.println("q_a: "+q_atomic);
                    indexOf = set.indexOf(Character.toString(q_atomic));
                    //System.out.println("indexOF: "+indexOf);
                    if(!first){
                        set.deleteCharAt(indexOf); 
                        newSet.append(q_atomic); 
                    }                                          
                }             
                if(!first){
                    //System.out.println("Agregando "+newSet);
                    addCollection(newSet);
                    newSet = new StringBuilder();
                }                         
                first= false;
                //System.out.println("new set: "+set);          
            }                  
            //System.out.println("Nuevo set de Colecciones: ");
            //printCollection();
        }
        else{
            System.out.println("IMPOSIBLE PARTICIONAR COLLECTION TRADUCTOR ERROR INDEX");
        }
        
    }

    public ArrayList<StringBuilder> getCollections() {
        return collections;
    }
    
    public String getIdCollection(int i){
        return id_collection.get(i);
    }
    public void printCollection() {
        //System.out.println("Imprimiendo coleccion..");
        int i = 0;
        for (StringBuilder set : collections) {
            System.out.println(set.toString() + " -> " + id_collection.get(i++));
        }
        System.out.println("");
    }

    public String getStateOf(String state) {
        for (int i = 0; i < collections.size(); i++) {
            String set = collections.get(i).toString();
            if (set.contains(state)) {
                return id_collection.get(i);
            }
        }
        return null;
    }
    public boolean contain(String x, String y){
        Scanner scan = new Scanner(y);
        char x_char = x.charAt(0);       
        for (int i = 0; i < y.length(); i++) {
            if(x_char==y.charAt(i)){
                return true;
            }
        }
        return false;
        
    }
    public String getSetOfId(String id){      
        for (int i = 0; i < id_collection.size(); i++) {
            if(id_collection.get(i).equals(id)){
                return getCollections().get(i).toString();
            }      
        }
        return null;
    }

}
