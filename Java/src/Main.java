
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Main {
  
    public static void main(String[] args) throws FileNotFoundException {
        
        /*COMENTADO POR EL MOMENTO PARA SIMPLIFICAR DEBUG Y EJECUCION
        RESTAURAR AL FINAL, YA QUE ES LO QUE PERMITE STDIN Y STDOUT 
        Scanner in = new Scanner(System.in);       
        */
        
        //SCANNER Y FILE directamente del directorio, borrar y reemplazar por lo de arriba
        Scanner sfile = new Scanner(new File("AFD.txt"));
        TextFile file = new TextFile(sfile); 
        String option = "tarea1p3"; //modificar segun tarea que se quiera ejecutar
        Controller control = new Controller(option, file);
               
        //String[] ej = {"hola","soy","un","ejemplo"};
        //file.generateNewTxtFile(ej);
    }    
}
