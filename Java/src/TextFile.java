


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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Scanner getFscan() {
        return fscan;
    }

    public void setFscan(Scanner fscan) {
        this.fscan = fscan;
    }

    public FileWriter getFwrite() {
        return fwrite;
    }

    public void setFwrite(FileWriter fwrite) {
        this.fwrite = fwrite;
    }

    public BufferedWriter getBwrite() {
        return bwrite;
    }

    public void setBwrite(BufferedWriter bwrite) {
        this.bwrite = bwrite;
    }

    public String getTextFile() {
        return textFile;
    }

    public void setTextFile(String textFile) {
        this.textFile = textFile;
    }
    
    
    
    
    
}
