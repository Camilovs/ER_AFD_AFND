
package tarea1malf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFile {
    
    private File file;
    private Scanner fscan;
    private FileWriter fwrite;
    private BufferedWriter bwrite;
    private String textFile="";
    

    public TextFile(String nameFile) {
        System.out.println("Iniciando procesamiento fichero "+nameFile);
        file = new File(nameFile);
        try {
            fscan = new Scanner(file);
            this.generateStringFile();
        } catch (FileNotFoundException ex) {
            System.err.println("Error abriendo archivo");
        }
        finally{
            fscan.close();
        }  
        System.out.println("Procesamiento de fichero listo!");
        
    }
    
    private void generateStringFile(){
        System.out.println("Generando string de contenido del archivo..");
        while(fscan.hasNextLine()){
            textFile = textFile+fscan.nextLine();
        }
        System.out.println("Contenido generado: "+textFile);
    }
    
    public void generateNewTxtFile(String[] text, String nameFile){
        try {
            fwrite = new FileWriter(nameFile);
            bwrite = new BufferedWriter(fwrite);
            
            for (String line : text) {
                bwrite.write(line+"\n");
            }
            bwrite.close();
        } catch (IOException ex) {
            Logger.getLogger(TextFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fwrite.close();
            } catch (IOException ex) {
                Logger.getLogger(TextFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
}
