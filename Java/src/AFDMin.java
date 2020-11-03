


import java.util.ArrayList;
import java.util.Scanner;


public class AFDMin {
    
    private AFD afd_origin;
    private AFD afd_min;
    private CollectionsTraductor collectionTable;

    public AFDMin(TextFile file) {
        this.afd_origin = afd_origin;
        afd_origin = new AFD(file);
        generateMinAFD(afd_origin);
        
    }
    
    private void generateMinAFD(AFD afd_origin){
        ArrayList<TableComparator> list_tables = new ArrayList<>();
        collectionTable = new CollectionsTraductor(afd_origin.getK(), 
                afd_origin.getF());
        
        while(true){
            
            for (StringBuilder set: collectionTable.getCollections()) {
                list_tables.add(new TableComparator(set, afd_origin.getSigma(), afd_origin.getDelta()));
            }
            for (TableComparator table : list_tables) {
                
            }
            
            list_tables.removeAll(list_tables);
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
