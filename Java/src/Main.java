
import java.util.Scanner;




public class Main {
  
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String option = args[0];   
        System.out.println(option);
        TextFile file = new TextFile(in);
        System.err.println("hola");
        String[] ej = {"hola","soy","un","ejemplo"};
        file.generateNewTxtFile(ej);
    }    
}
