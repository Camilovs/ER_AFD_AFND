import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class AFD {
    
    private ArrayList<String> K;            //Estados
    private ArrayList<Character> sigma;     //alfabeto
    private String s;                       //Estado inicial
    private ArrayList<String> F;            //Estado final
    private ArrayList<Link> delta;          //transiciones

    public AFD() {
        this.delta = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> transitions = new ArrayList<>();
        Stack<String> rest = new Stack<>();
        Stack<String> restInOrder = new Stack<>();
        String statesLine, sigmaLine, finalStatesLine, startStateLine;

        scanner.nextLine(); // La primera línea del archivo no sirve
        statesLine = scanner.nextLine(); // La segunda línea es el conjunto de estados K del AFND
        sigmaLine =  scanner.nextLine(); // La tercera línea es el alfabeto sigma
        scanner.nextLine(); // La cuarta línea no sirve (dice "delta")

        // Las lineas a continuación son una cantidad indefinida de 
        // funciones de transición delta, seguidas del estado inicial y el
        // conjunto de estados finales
        while(scanner.hasNext()) {
            rest.push(scanner.next());
        }

        finalStatesLine = rest.pop(); // La última línea es el conjunto de estados finales
        startStateLine = rest.pop(); // La penúltima línea es el estado inicial

        // Lo que queda en el stack son las transiciones
        transitions = extractTransitions(rest);
        String[] splitted;
        Link link;
        for(String transition : transitions) {
            splitted = splitTransition(transition); // Retorna un arreglo de Strings con los 3 elementos de una transicion
            link = new Link(splitted[0], splitted[1], splitted[2]);
            this.delta.add(link);
        }


        this.K = extractStates(statesLine);
        this.sigma = extractSigma(sigmaLine);
        this.s = startStateLine.substring(2);
        this.F = extractStates(finalStatesLine);

    }
    /**
     * CONSTRUCTOR NO VALIDO SOLO PARA FINES DE PRUEBAS +
     * acepta scanner y no trabaja con stdin
     *
    public AFD(Scanner scanner) { 
        this.delta = new ArrayList<>();
        //Scanner scanner = new Scanner(System.in);
        ArrayList<String> transitions = new ArrayList<>();
        Stack<String> rest = new Stack<>();
        Stack<String> restInOrder = new Stack<>();
        String statesLine, sigmaLine, finalStatesLine, startStateLine;

        scanner.nextLine(); // La primera línea del archivo no sirve
        statesLine = scanner.nextLine(); // La segunda línea es el conjunto de estados K del AFND
        sigmaLine =  scanner.nextLine(); // La tercera línea es el alfabeto sigma
        scanner.nextLine(); // La cuarta línea no sirve (dice "delta")

        // Las lineas a continuación son una cantidad indefinida de 
        // funciones de transición delta, seguidas del estado inicial y el
        // conjunto de estados finales
        while(scanner.hasNext()) {
            rest.push(scanner.next());
        }

        finalStatesLine = rest.pop(); // La última línea es el conjunto de estados finales
        startStateLine = rest.pop(); // La penúltima línea es el estado inicial

        // Lo que queda en el stack son las transiciones
        transitions = extractTransitions(rest);
        String[] splitted;
        Link link;
        for(String transition : transitions) {
            splitted = splitTransition(transition); // Retorna un arreglo de Strings con los 3 elementos de una transicion
            link = new Link(splitted[0], splitted[1], splitted[2]);
            this.delta.add(link);
        }


        this.K = extractStates(statesLine);
        this.sigma = extractSigma(sigmaLine);
        this.s = startStateLine.substring(2);
        this.F = extractStates(finalStatesLine);

    }*/

    private ArrayList<String> extractStates(String statesLine) {
        ArrayList<String> states = new ArrayList<>();
        String str = statesLine.substring(statesLine.indexOf('{') + 1, statesLine.indexOf('}'));
        String[] aux = str.split(",");

        for(int i = 0; i < aux.length; i++) {
          states.add(aux[i]);
        }
        return states;
    }

    private static ArrayList<Character> extractSigma(String sigmaLine) {
        ArrayList<Character> sigma = new ArrayList<>();
        sigmaLine = sigmaLine.substring(sigmaLine.indexOf('{') + 1, sigmaLine.indexOf('}'));
        String[] aux = sigmaLine.split(",");

        for(int i = 0; i < aux.length; i++) {
          sigma.add(aux[i].charAt(0));
        }

        return sigma;
    }

    // Recibe una transisión de la forma (q1,a,q2) y retorna un arrreglo con
    // los valores q1,a,q2
    private String[] splitTransition(String transition) {
        transition = transition.substring(1, transition.length() - 1);
        String[] splitted = transition.split(",");
        return splitted;
    }

    private ArrayList<String> extractTransitions(Stack<String> delta) {
        ArrayList<String> transitions = new ArrayList<>();
        Stack<String> deltaInOrder = new Stack<>();
        int size = delta.size();

        for(int i = 0; i < size; i++) {
          deltaInOrder.push(delta.pop());
        }

        for(int i= 0; i < size; i++) {
          transitions.add(deltaInOrder.pop());
        }

        return transitions;
    }
    
    private static void printArray(ArrayList<String> arr) {
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i));
            if(i + 1 < arr.size())
                System.out.print(",");
        }
    }
    
    private static void printCharArray(ArrayList<Character> arr) {
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i));
            if(i + 1 < arr.size())
                System.out.print(",");
        }
    }



    public ArrayList<String> getK() {
        return this.K;
    }

    public ArrayList<Character> getSigma() {
        return this.sigma;
    } 

    public String getS() {
        return this.s;
    }

    public ArrayList<String> getF() {
        return this.F;
    }

    public ArrayList<Link> getDelta(){ 
        return this.delta;
    }

    public void setK(ArrayList<String> K) {
        this.K = K;
    }

    public void setSigma(ArrayList<Character> sigma) {
        this.sigma = sigma;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setF(ArrayList<String> F) {
        this.F = F;
    }

    public void setDelta(ArrayList<Link> delta) {
        this.delta = delta;
    }

    public void print(String title) {
        System.out.println(title);

        System.out.print("K={");
        for(int i = 0; i < this.K.size(); i++) {
            System.out.print(this.K.get(i));
            if(i + 1 < this.K.size())
                System.out.print(",");
        }
        System.out.println("}");

        System.out.print("Sigma={");
        printCharArray(sigma);
        System.out.println("}");

        System.out.println("delta:");
        for(Link link : this.delta) {
            link.print();
        }

        System.out.println("s=" + this.s);

        System.out.print("F={");
        printArray(F);
        System.out.println("}");

    }

    // Unit testing
    public static void main(String[] args) {
        AFD afd = new AFD();       
        afd.print("AFD M");
        //System.out.println("K");
        //for(String k : afd.getK()) {
            //System.out.println(k);
        //}

        //System.out.println("sigma");
        //for(Character ch : afd.getSigma()) {
            //System.out.println(ch);
        //}

        //System.out.println("s");
        //System.out.println(afd.getS());

        //System.out.println("F");
        //for(String f : afd.getF()) {
            //System.out.println(f);
        //}

        //System.out.println("delta");
        //for(Link link : afd.getDelta());
    }
 }
