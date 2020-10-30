


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ER {
    
    private String expression;

    public ER(String er) {
        this.expression = er;
    }
    
    
     
    private int obtenerPrecedencia(Character c, Map<Character, Integer> precedencias) {
        Integer precedencia = precedencias.get(c);
        return precedencia == null ? 6 : precedencia;
    }
    public String pasarAPostfijo() {
        Map<Character, Integer> precedencia; //la precedencia de los operadores
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 1);
        map.put('|', 2);
        map.put('.', 3);
        map.put('*', 4);
        precedencia = Collections.unmodifiableMap(map);
        
        String er = this.expression;
        String temp = "";
        Stack<Character> stack = new Stack<Character>();
        for (Character c : er.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while (!stack.peek().equals('(')) {
                        temp += stack.pop();
                    }
                    stack.pop();
                    break;
                default:
                    while (stack.size() > 0) {
                        Character peek = stack.peek();
                        int peekPrecedencia = obtenerPrecedencia(peek, precedencia);
                        int actualPrecedencia = obtenerPrecedencia(c, precedencia);
                        if (peekPrecedencia >= actualPrecedencia) {
                            temp += stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(c);
                    break;
            }
        }
        while (stack.size() > 0) {
            temp += stack.pop();
        }
        return temp;
    }
    
}
