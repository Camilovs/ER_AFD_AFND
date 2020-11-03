
import java.util.ArrayList;


public class TableComparator {
    
    private StringBuilder collection;
    private ArrayList<Character> sigma_AFD;
    private String[][] table_states;  
    private ArrayList<Link> links;

    public TableComparator(StringBuilder collection, ArrayList<Character> sigma_AFD, 
            ArrayList<Link> delta) {
        this.collection = collection;
        this.sigma_AFD = sigma_AFD;
        this.links = delta;
        table_states = new String[collection.length()][sigma_AFD.size()];
        buildTable();
    }
    
    private void buildTable(){
        String state;
        String[] dataTable = new String[sigma_AFD.size()];
        
        for (int i = 0; i < collection.length(); i++) {
            state = String.valueOf(collection.charAt(i));
            int j=0;
            for(Link link: links ){
                if(link.getStateInit().equals(state)){
                   dataTable[j++]=link.getStateEnd();                
                }
            }
            table_states[i] = dataTable;
        }
    }
    public void addRow(int i, String[] states){     
        table_states[i]= states;
    }
    
    public void addState(int i,int j, String state){
        table_states[i][j] = state;
    }
   
    
    
    
    
    
}
