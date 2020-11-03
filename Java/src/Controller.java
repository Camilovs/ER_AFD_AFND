
public class Controller {
    private int ejec_option;
    private ER expression;
    private AFD afd;
    private AFND afnd;
    private TextFile file;

    public Controller(String option, TextFile fopen) {
        
        switch (option.toLowerCase()) {
            case "tarea1p1":// EJECUCION DE ER -> AFND
                this.ejec_option = 1;
                ER regex = new ER(file.getTextFile());
                AFND afnd = new AFND(regex);     
                break;
            case "tarea1p2"://EJECUCION DE AFND -> AFD
                this.ejec_option = 2;
                break;
            case "tarea1p3"://EJECUCION DE Miminizacion de AFD
                this.ejec_option = 3;
                AFDMin adfmin = new AFDMin(fopen);          
                break;              
            default:
                System.err.println("Primer argumento invalido, cerrando programa");
                System.exit(1);
        }
        this.file = fopen;
        
        
    }
    
    
    
    
}
