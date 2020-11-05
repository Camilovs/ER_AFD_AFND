
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase que representa una Tabla comparadora de un set de estados. Esta tabla
 * contiene los estado destino de cada estado, segun las transiciones definidas 
 * por el conjunto delta. 
 * @author Camilo
 */
public class TableTransition {
    
   
    private String[][] table_states;   //Matriz de estados, representa la tabla 
    private ArrayList<String> states_original; //cabezera de la matriz, contiene los estados de inicio
   
    private String id_collection;       //conjunto de estados
    private String set_collection;      //etiqueta del conjunto
    private boolean isFinal = false;    //tabla terminada

    public TableTransition(int numCollections, int numSigma, String id_coll,
            String set_coll){   
        
        table_states = new String[numCollections][numSigma];   
        id_collection = id_coll;
        set_collection = set_coll;
        states_original = new ArrayList<>();
    }
    /**
     * Metodo que analiza si la tabla esta terminada, o todos sus estados
     * son equivalentes. 
     * @return 
     */
    public boolean isFinal(){
        int condition_true = table_states.length;
        int count_equals = 0;      
        String row=Arrays.toString(table_states[0]);         
        for (int i = 0; i < table_states.length; i++) {      
            if(row.equals(Arrays.toString(table_states[i]))){
                count_equals++;
            }
        }
        
        if(count_equals==condition_true){
            isFinal = true;
        }
        else{
        }
        
        return isFinal;       
    }
    public void addRow(int i, String[] states){     
        table_states[i]= states;
    }
    
    public void addState(int i,int j, String state){
        table_states[i][j] = state;
    }
    
    public void printTable(){      
        System.out.println("Table "+set_collection+" -> "+id_collection);
        for (int i = 0; i < table_states.length; i++) {
            for (int j = 0; j < table_states[i].length; j++){
                System.out.print("|"+table_states[i][j]);
            }
            System.out.print("|");
            System.out.println("");
        }
    }
    /**
     * Metodo que junta los estados equivalentes en un nuevo conjunto, y separa
     * los estados que son diferentes a este conjunto. 
     * @return Una lista con conjuntos de estados.
     */
    public ArrayList<String> joinEqualStates(){
        //System.out.println("Iniciando join equals");
        ArrayList<String> listEquals = new ArrayList<>();
        String equal="",set1,set2;
        ArrayList<Integer> indexDel = new ArrayList<>();
        if(!isFinal){
            for (int i = 0; i < table_states.length; i++) {          
            set1 =Arrays.toString(table_states[i]);          
                for (int j = 0; j < table_states.length; j++) {
                    if(!indexDel.contains(j)){
                        set2 =Arrays.toString(table_states[j]);
                        if(set1.equals(set2)){
                            equal = equal+states_original.get(j);
                            indexDel.add(j);
                        }    
                    }              
                }                                        
                if(!equal.isEmpty()){
                    //System.out.println("Aregando equals: "+equal);
                    listEquals.add(equal);  
                    equal="";
                }           
            }
        }    
        return listEquals;
    }
    
    public String[] getRow(int i){
        return table_states[i];
    }
    public void addOriginalState(String state){
       states_original.add(state);
    }

    public String getId_collection() {
        return id_collection;
    }

    public void setId_collection(String id_collection) {
        this.id_collection = id_collection;
    }

    public String getSet_collection() {
        return set_collection;
    }

    public void setSet_collection(String set_collection) {
        this.set_collection = set_collection;
    }

    public String[][] getTable_states() {
        return table_states;
    }

    public void setTable_states(String[][] table_states) {
        this.table_states = table_states;
    }

    public ArrayList<String> getStates_original() {
        return states_original;
    }

    public void setStates_original(ArrayList<String> states_original) {
        this.states_original = states_original;
    }

    public boolean isIsFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
    
    
    
    
    
    
    
    
    
    
}
