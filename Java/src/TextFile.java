


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class TextFile {
    
    private File file;
    private Scanner fscan;
    private FileWriter fwrite;
    private BufferedWriter bwrite;
    private String textFile="";
    

    public TextFile(Scanner file) {
        System.out.println("Iniciando procesamiento fichero ");
        fscan = file;
        this.generateStringFile();
        fscan.close();
        System.out.println("Procesamiento de fichero listo!");
        
    }
    
    private void generateStringFile(){
        System.out.println("Generando string de contenido del archivo..");
        while(fscan.hasNextLine()){
            textFile = textFile+fscan.nextLine();
        }
        System.out.println("Contenido generado: "+textFile);
    }
    
    public void generateNewTxtFile(String[] text){
        System.out.println("Guardando archivo");
        for (String line : text) {
            //bwrite.write(line+"\n");
            System.out.println(line);
        }
            
        
    }
    
    
    
}
