
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class tarea1p3 {
    
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner in  = new Scanner(System.in);
        Scanner sfile = new Scanner(new File("AFD.txt"));
        
        AFDMin afdmin = new AFDMin(sfile);
    }
}
