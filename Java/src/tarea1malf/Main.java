
package tarea1malf;

public class Main {
  
    public static void main(String[] args) {
        TextFile file = new TextFile("ER.txt");
        String[] ej = {"hola","soy"};
        file.generateNewTxtFile(ej, "ej.txt");
    }    
}
