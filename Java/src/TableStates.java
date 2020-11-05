
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Clase que representa una tabla con los conjuntos de estados equivalentes,
 * cada uno representado con una etiqueta o id.
 * @author Camilo
 */
public class TableStates {

    private String collection;                  //Nuevo conjunto de estados ej. {s1,s2,s3} = X
    private ArrayList<String> states_final;     //estados finales (aceptado) del AFD
    private ArrayList<String> states_original;  //Conjunto K de estados del AFD
    private final String[] statesAlph = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    
    //Ambas listas conforman la tabla, primero esta el conjunto de estados y luego su etiqueta.
    private ArrayList<StringBuilder> collections;
    private ArrayList<String> id_collection;
    
    private int stateAlph_take = 0; //numero de letras usadas para etiquetas
    
    /**
     * Constructor de clase
     * @param states_original Conjunto estados originales (conjunto K)
     * @param states_final Conjunto de estados finales (conjunto F)
     */
    public TableStates(ArrayList<String> states_original, ArrayList<String> states_final) {

        collections = new ArrayList<>();
        id_collection = new ArrayList<>();
        this.states_final = states_final;
        this.states_original = states_original;
        fillTable();
    }
    /**
     * Metodo que crea la primera tabla, con el conjunto de estados aceptados 
     * (finales) y no aceptados. 
     */
    private void fillTable() {
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
     /**
     * Metodo que agrega una coleccion de estados a la tabla. Le crea la etiqueta
     * @param set 
     */
    public void addCollection(StringBuilder set) {
        collections.add(set);
        id_collection.add(statesAlph[stateAlph_take++]);
    }
    
    /**
     * Metodo que divide la tabla segun los estados que se requieren sacar.
     * Los estados que no se sacan quedan en el conjunto original con la misma etiqueta,
     * pero los estados extraidos se agregan a un nuevo conjunto con otra etiqueta.
     * @param states_out Set de estados que se requieren extraer
     */
    public void splitCollection(String[] states_out) {
            
        int index=-1;
        
        //nuevo conjunto para los estados que se sacan
        StringBuilder newSet = new StringBuilder();
        
        /*Ciclo para obtener la ubicacion de los estados que ser quieren
        sacar dentro de la tabla */   
        for (int i = 0; i < collections.size(); i++) {             
            String x = states_out[0];
            String y = collections.get(i).toString();          
            if(contain(x,y)){               
                index =  i;
                break;
            }
        }
        
        /*Si se encontraron los estados, procedemos a extraerlos y agregarlos
        a un nuevo conjunto*/
        if(index!=-1){
            StringBuilder set = collections.get(index);
            int indexOf;   
            boolean first = true;
            for (String q : states_out) {  
                for (int i = 0; i < q.length(); i++) {                 
                    char q_atomic = q.charAt(i);
                    indexOf = set.indexOf(Character.toString(q_atomic));
                    if(!first){
                        set.deleteCharAt(indexOf); 
                        newSet.append(q_atomic); 
                    }                                          
                }             
                if(!first){
                    addCollection(newSet);
                    newSet = new StringBuilder();
                }                         
                first= false;
            }                          
        }
        //sino, existio un error encontrando los estados.
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
        int i = 0;
        for (StringBuilder set : collections) {
            System.out.println(set.toString() + " -> " + id_collection.get(i++));
        }
        System.out.println("");
    }
    /**
     * Obtiene la etiqueta del conjunto que contiene un estado,
     * @param state estado para buscar su etiqueta
     * @return la etiqueta del conjunto.
     */
    public String getStateOf(String state) {
        for (int i = 0; i < collections.size(); i++) {
            String set = collections.get(i).toString();
            if (set.contains(state)) {
                return id_collection.get(i);
            }
        }
        return null;
    }
     /**
     * Metodo para comparar dos String y analizar si algun caracter de uno
     * se encuentra en el otro.
     * @param x cadena uno
     * @param y cadena dos
     * @return true si algun caracter de x se encuentra en y, y false si no
     */
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
    /**
     * Obtiene el conjunto de estados correspondientes a una etiqueta.
     * @param id etiqueta de un conjunto dentro de la tabla.
     * @return conjunto de estados, pertenecientes a la etiqueta.
     */
    public String getSetOfId(String id){      
        for (int i = 0; i < id_collection.size(); i++) {
            if(id_collection.get(i).equals(id)){
                return getCollections().get(i).toString();
            }      
        }
        return null;
    }

}
