import java.util.ArrayList;
import java.util.Scanner;

/**
 * En esta clase se crea un Automata Finito Determinista a partir de un Automata
 * Finito, recibido desde la entrada estandar del programa. Para obtener el automata
 * minimizado se realiza una tecnica de analisis de conjuntos para obtener y mezclar 
 * todos los estados equivalentes (para todas las posibles entradas los estados destinos
 * de estas transiciones son iguales), en nuevos estados.
 * 
 * @author Camilo
 */
public class AFDMin {
    
    private AFD afd_origin;     //AFD de origen
    private AFD afd_min;        //AFD de origan minimizado
    
    /*Tabla que crea conjuntos de estados, es usada en la tecnica de minimizacion
    para juntar los estados equivalentes.*/
    private CollectionsTraductor collectionTable;   
    
    /*Lista de tablas comparativas por cada conjunto equivalente, se utiliza para 
    definir nuevos estados equivalentes*/
    private ArrayList<TableComparator> list_tables;
    
    //Nuevos elementos del afd minimizado
    private ArrayList<Link> new_delta;
    private ArrayList<String> new_K;
    private ArrayList<String> new_F;            
    private String new_s;

    public AFDMin() {
        
        afd_origin = new AFD();
        new_delta = new ArrayList<>();
        new_K = new ArrayList<>();
        new_F = new ArrayList<>();
        new_s = "";                
        list_tables = new ArrayList<>();
        fillTableComparator();
        generateAFDMin();
                     
    }
    /**
     * Constructor para pruebas, trabaja con Scanner y no son stdin 
     *
    public AFDMin(Scanner scanner) {
        //this.afd_origin = afd_origin;
        afd_origin = new AFD(scanner); 
        new_delta = new ArrayList<>();
        new_K = new ArrayList<>();
        new_F = new ArrayList<>();
        new_s = "";       
        //afd_origin.print();
        list_tables = new ArrayList<>();
        fillTableComparator();
        generateAFDMin();
    }    */
    
    /**
     * Metodo que Crea la tabla de conjuntos equivalentes a partir de tablas 
     * comparadoras de transiciones. Realiza un ciclo hasta que todas las tablas
     * comparadoras se encuentran "terminadas", es decir, todos los estados fueron
     * juntados con sus equivalentes. 
     */
    private void fillTableComparator(){     
        //Se crea la tabla de conjunto de estados para la primera iteracion
        collectionTable = new CollectionsTraductor(afd_origin.getK(), afd_origin.getF());
        
        //Representa una etiqueta para un conjunto de estados ej {q1,q2,q3} -> A, A seria la etiqueta
        String id_collection;
        
        //Comienza el ciclo de creacion de tablas comparadoras y division de conjuntos.
        boolean end = false;
        while(!end){
            int index_collection=0;  
            
            //Cilco que recorre todo la tabla de conjuntos.
            for (StringBuilder set: collectionTable.getCollections()) {
                
                //se obiene la etiqueta del conjunto en cuestion.
                id_collection= collectionTable.getIdCollection(index_collection);
                
                //Se crea una nueva tabla comparadora para el conjunto en cuestion.
                TableComparator table = new TableComparator(set.length(),
                        afd_origin.getSigma().size(),id_collection,set.toString());              
                
                /*Ciclo comienza a recorrer cada estado de un conjunto de estados para comenzar
                a crear las tablas de comparacion*/
                String state; 
                for (int i = 0; i < set.length(); i++) {   
                    
                    //Se crea una fila de la tabla de comparacion 
                    String[] dataTable = new String[afd_origin.getSigma().size()];
                   
                    //se obtiene el estado en cuestion
                    state = String.valueOf(set.charAt(i));                
                    int j=0;
                    //Se agrega el estado original de cabezera para la matriz.
                    table.addOriginalState(state); 
                    
                    /*Ciclo que compara las transiciones disponibles para obtener los
                    caminos del estado en cuestion*/
                    for(Link link: afd_origin.getDelta() ){
                        if(link.getStart().equals(state)){                    
                           dataTable[j++]= collectionTable.getStateOf(link.getFinish());                
                        }                    
                    }              
                    table.addRow(i, dataTable);                               
                }             
                list_tables.add(table);             
                ++index_collection;
            }
            
            /*Cuando se crean todas las tablas, para todos los conjuntos
            se analiza si estas tablas estan "terminadas", es decir, todas sus filas
            son iguales (estados equivalentes)*/
            int num_tables_final = 0;
            for (TableComparator table : list_tables) {
                if(table.isFinal()){
                    num_tables_final++;
                }
                
                /*Si se encuentra una tabla que no esta terminada, esta se divide en los 
                estados equivalentes y los no equivalentes, agregando un nuevo conjunto
                a la tabla de conjuntos (los no equivalentes)*/         
                else{
                    ArrayList<String> list_coll = table.joinEqualStates();
                    String[] new_collection = list_coll.toArray(new String[0]);
                    collectionTable.splitCollection(new_collection);
                }
            }
            //Si las tablas son finales, terminamos la ejecucion del metodo
            if(num_tables_final==list_tables.size()){
                end = true;
            
            }else{//Si no, limpiamos la lista de tablas y creamos nuevas para los nuevos conjuntos.               
                list_tables.removeAll(list_tables);
            }                   
        }
    }
    /**
     * Metodo que genera el Automata minimizado a partir de las tablas terminadas
     * y del nuevo conjunto de estados.
     */
    private void generateAFDMin(){
        
        //Setear variables necesarias del automata
        ArrayList<Character> sigma = afd_origin.getSigma();       
        String set_WithFormat;
        String dest_withFormat;
        String destination;
        String tag;
        String s = afd_origin.getS();
        ArrayList<String> f = afd_origin.getF();
        
        //Se inicia un ciclo para recorrer todas las tablas terminadas
        for (TableComparator table : list_tables) { 
            
            /*Se obtiene la primera fila de la table en cuestion, 
            como son todas iguales no importa cual obtener.*/
            String[] firstRow = table.getRow(0);
            
            //Se transorma el conjunto de estados al formato de salida requerido
            set_WithFormat = addSeparationSet(table.getSet_collection());
            
            //Se agrega un elemento al conjunto K y se define el estado inicial.
            if(!new_K.contains(set_WithFormat)){
                new_K.add(set_WithFormat);               
                if(set_WithFormat.contains(s)){                   
                    new_s = set_WithFormat;                  
                }            
            }
            
            /*Ciclo que recorre la fila elegida para crear las nuevas transiciones
            del nuevo estado representado por la tabla*/
            for (int i = 0; i < firstRow.length; i++) {            
                destination = collectionTable.getSetOfId(firstRow[i]); 
                dest_withFormat = addSeparationSet(destination);
                tag = Character.toString(sigma.get(i));
                Link link = new Link(set_WithFormat, tag, dest_withFormat);
                new_delta.add(link);
                //link.print();
            }          
        } 
        //Ciclo pada definir el conjunto de estados finales
        for(String f_atomic: f){
            for (int i = 0; i < new_K.size(); i++) {
                if(new_K.get(i).contains(f_atomic)){
                    new_F.add(new_K.get(i));
                }
            }
        }
        //Se transforma el antiguo automata a uno minimizado.
        afd_min = afd_origin;
        afd_min.setK(new_K);
        afd_min.setDelta(new_delta);
        afd_min.setS(new_s);
        afd_min.setF(new_F);
        
        //Se imprime el automata minimizado.
        afd_min.print("AFDM A");
        
    }
    /**
     * Metodo que agrega el caracter "|" entre medio de los estados equivalentes.
     * @param Set de estados equivalentes
     * @return nueva representacion de los estados con el formato requerido.
     */
    public String addSeparationSet(String set){
        String separation = "|";
        StringBuilder setWithSeparation= new StringBuilder();
        for (int i = 0; i < set.length(); i++) {
            setWithSeparation.append(set.charAt(i));
            if(i!=set.length()-1){
                setWithSeparation.append(separation);
            }       
        }
        return setWithSeparation.toString();
    }
    //Getters y setters
    public AFD getAfd_origin() {
        return afd_origin;
    }

    public void setAfd_origin(AFD afd_origin) {
        this.afd_origin = afd_origin;
    }

    public AFD getAfd_min() {
        return afd_min;
    }

    public void setAfd_min(AFD afd_min) {
        this.afd_min = afd_min;
    }

    public CollectionsTraductor getCollectionTable() {
        return collectionTable;
    }

    public void setCollectionTable(CollectionsTraductor collectionTable) {
        this.collectionTable = collectionTable;
    }
    
    
    
    
}
