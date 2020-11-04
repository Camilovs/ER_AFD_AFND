


import java.util.ArrayList;
import java.util.Scanner;


public class AFDMin {
    
    private AFD afd_origin;
    private AFD afd_min;
    private CollectionsTraductor collectionTable;

    public AFDMin() {
        //this.afd_origin = afd_origin;
        //afd_origin = new AFD(file);
        afd_origin = new AFD();
        afd_origin.print();
        generateMinAFD();
        
    }

    public AFDMin(Scanner scanner) {
        //this.afd_origin = afd_origin;
        afd_origin = new AFD(scanner); 
        System.out.println("Imprimiento AFD origin:");
        afd_origin.print();
        generateMinAFD();

    }    
    private void generateMinAFD(){
        ArrayList<TableComparator> list_tables = new ArrayList<>();
        collectionTable = new CollectionsTraductor(afd_origin.getK(), 
                afd_origin.getF());
        collectionTable.printCollection();
        boolean end = false;
        while(!end){
            
            for (StringBuilder set: collectionTable.getCollections()) {
                //list_tables.add(new TableComparator(set, afd_origin.getSigma(), afd_origin.getDelta()));
                TableComparator table = new TableComparator(set.length(), afd_origin.getSigma().size());
                String state;
                
                    
                for (int i = 0; i < set.length(); i++) {
                    String[] dataTable = new String[afd_origin.getSigma().size()+1];
                    state = String.valueOf(set.charAt(i));
                    System.out.println("state: "+state);
                    int j=0;
                    dataTable[j++] = state;
                    for(Link link: afd_origin.getDelta() ){
                        if(link.getStart().equals(state)){                    
                           dataTable[j++]= collectionTable.getStateOf(link.getFinish());                
                        }                    
                    }              
                    table.addRow(i, dataTable);
                    table.printTable();
                }
                System.out.println("Agregando tabla");
                list_tables.add(table);                                     
            }
            System.out.println("TERMINANDO RECORRIDO SET");
            int num_tables_final = 0;
            for (TableComparator table : list_tables) {
                if(table.isFinal()){
                    num_tables_final++;
                }
            }
            if(num_tables_final==list_tables.size()){
                end = true;
            }else{
                //-> partir tablas <-----------------------------------
                list_tables.removeAll(list_tables);
            }                   
        }
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
