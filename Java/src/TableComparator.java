
import java.util.ArrayList;
import java.util.Arrays;


public class TableComparator {
    
    //private StringBuilder collection;
    //private ArrayList<Character> sigma_AFD;
    private String[][] table_states;  
    //private ArrayList<Link> links;

    public TableComparator(int numCollections, int numSigma){   
        //table_states = new String[collection.length()][sigma_AFD.size()];
        table_states = new String[numCollections][numSigma];   
    }
    
    public boolean isFinal(){
        int condition_true = table_states.length;
        int count_equals = 0;
        String row=Arrays.toString(table_states[0]);  
        for (int i = 1; i < table_states.length; i++) {       
            if(row.equals(Arrays.toString(table_states[i]))){
                count_equals++;
            }
        }
        if(count_equals==condition_true){
            return true;
        }else{
            return false;
        }
    }
    public void addRow(int i, String[] states){     
        table_states[i]= states;
    }
    
    public void addState(int i,int j, String state){
        table_states[i][j] = state;
    }
    
    public void printTable(){
        System.out.println("Print table");
        for (int i = 0; i < table_states.length; i++) {
            for (int j = 0; j < table_states[i].length; j++){
                System.out.print("|"+table_states[i][j]);
            }
            System.out.print("|");
            System.out.println("");
        }
    }
    
    
    
    
    
    
    
}
