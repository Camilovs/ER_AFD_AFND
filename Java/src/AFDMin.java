


import java.util.ArrayList;
import java.util.Scanner;


public class AFDMin {
    
    private AFD afd_origin;     //AFD de origen
    private AFD afd_min;        //AFD de origan minimizado
    
    /*Tabla que crea conjuntos de estados, es usada en la tecnica de minimizacion
    para juntar los estados equivalentes.*/
    private CollectionsTraductor collectionTable;   
    
    //Lista de tablas comparativas 
    private ArrayList<TableComparator> list_tables;
    
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
     */
//    public AFDMin(Scanner scanner) {
//        //this.afd_origin = afd_origin;
//        afd_origin = new AFD(scanner); 
//        new_delta = new ArrayList<>();
//        new_K = new ArrayList<>();
//        new_F = new ArrayList<>();
//        new_s = "";       
//        //afd_origin.print();
//        list_tables = new ArrayList<>();
//        fillTableComparator();
//        generateAFDMin();
//    }    
    
    private void fillTableComparator(){     
        collectionTable = new CollectionsTraductor(afd_origin.getK(), 
                afd_origin.getF());
        
        boolean end = false;
        String id_collection;
        while(!end){
            int index_collection=0;         
            for (StringBuilder set: collectionTable.getCollections()) {            
                id_collection= collectionTable.getIdCollection(index_collection);
                TableComparator table = new TableComparator(set.length(),
                        afd_origin.getSigma().size(),id_collection,set.toString());              
                String state; 
                
                /*Ciclo comienza a recorrer cada estado de un conjunto de estados para comenzar
                a crear las tablas de comparacion*/
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
                //Agregando tabla              
                //table.printTable();
                list_tables.add(table); 
                
                ++index_collection;
            }
            //System.out.println("Separando tablas..");
            int num_tables_final = 0;
            for (TableComparator table : list_tables) {
                if(table.isFinal()){
                    num_tables_final++;
                }
                else{
                    ArrayList<String> list_coll = table.joinEqualStates();
                    String[] new_collection = list_coll.toArray(new String[0]);
//                    System.out.println("Nueva coleccion: ");
//                    for (String string : new_collection) {
//                        System.out.println(string);
//                    }
                    collectionTable.splitCollection(new_collection);
                }
            }
            if(num_tables_final==list_tables.size()){
                end = true;
            }else{                           
                list_tables.removeAll(list_tables);
            }                   
        }
    }
    
    private void generateAFDMin(){
        
        ArrayList<Character> sigma = afd_origin.getSigma();       
        String set_WithFormat;
        String dest_withFormat;
        String destination;
        String tag;
        String s = afd_origin.getS();
        ArrayList<String> f = afd_origin.getF();
        
        for (TableComparator table : list_tables) {            
            String[] firstRow = table.getRow(0);
            set_WithFormat = addSeparationSet(table.getSet_collection());
            if(!new_K.contains(set_WithFormat)){
                new_K.add(set_WithFormat);               
                if(set_WithFormat.contains(s)){                   
                    new_s = set_WithFormat;                  
                }            
            }
            for (int i = 0; i < firstRow.length; i++) {            
                destination = collectionTable.getSetOfId(firstRow[i]); 
                dest_withFormat = addSeparationSet(destination);
                tag = Character.toString(sigma.get(i));
                Link link = new Link(set_WithFormat, tag, dest_withFormat);
                new_delta.add(link);
                //link.print();
            }          
        } 
        for(String f_atomic: f){
            for (int i = 0; i < new_K.size(); i++) {
                if(new_K.get(i).contains(f_atomic)){
                    new_F.add(new_K.get(i));
                }
            }
        }
        afd_min = afd_origin;
        
        afd_min.setK(new_K);
        afd_min.setDelta(new_delta);
        afd_min.setS(new_s);
        afd_min.setF(new_F);
        afd_min.print("AFDM A");
        
    }
    
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
