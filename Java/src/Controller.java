


public class Controller {
    private int ejec_option;
    private ER expression;
    private AFD afd;
    private AFND afnd;
    private TextFile file;

    public Controller(String option, TextFile fopen,String fwrite ) {
        
        switch (option.toLowerCase()) {
            case "tarea1p1":
                this.ejec_option = 1;
                break;
            case "tarea1p2":
                this.ejec_option = 2;
                break;
            case "tarea1p3":
                this.ejec_option = 3;
                break;              
            default:
                System.err.println("Primer argumento invalido, cerrando programa");
                System.exit(1);
        }
        this.file = fopen;
        
        
    }
    
    
    
    
}
