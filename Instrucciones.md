## Instrucciones de Desarrollo (Requerimientos) 📝
### Parte 1: Er -> AFND 
En la primera parte de la tarea tienen que escribir un programa que reciba una ER desde
la entrada estándar y escriba en la salida estándar el AFND equivalente.
A continuación se muestran los símbolos y operaciones que se utilizan y cuál(es)
son los caracteres que están asociados:

- Concatenación: .
- Unión: |
- Estrella de Kleene: *
- Paréntesis ( )
- ε: _
- Φ: ~
- Σ: abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789

El símbolo de la concatenación es el punto ., el de ε el guión bajo \_ y el de la ER vacía ~. Noten que el texto sólo esta formado por letras mayúsculas, minúsculas y dígitos. 
Se excluyen ñ y Ñ. Los demás símbolos (., |, *, (, ), \_ y ~) se utilizan para escribir las ERs o para describir los autómatas.
Entonces, van a recibir la ER de la entrada estándar y van a escribir el AFND en la
salida estándar. Por ejemplo, en la consola de linux ejecutan lo siguiente:  
```
tarea1P1 < er.txt > afnd.txt 
```
Por ejemplo, para calcular la transformación de la ER **ab**, podemos usar el siguiente
archivo para la ER, note que la concatenación se indica de forma explícita con el .:

**a.b**

La salida de esta parte debería ser:  
```
AFND M:  
K={q0,q1,q2,q3}  
Sigma={a,b}  
Delta:  
(q0,a,q1)  
(q1,_,q2)  
(q2,b,q3)  
s=q0  
F={q3}
```

Sigan los formatos de los ejemplos, vale decir, la ER se escribe en un archivo que sólo
contiene una línea (que podria ser larga) y el AFND se escribe en un archivo en que la
primera línea tiene el encabezado AFND M:, luego viene el conjunto de estados K en una
línea, en la siguiente línea el alfabeto Σ del autómata también en una línea, luego la relación
2
de transición ∆, con cada transición en una línea separada, otra línea para el estado inicial
y la última línea para el conjunto de estados finales F.

### Parte 2: AFND -> AFD  

En la segunda parte de la Tarea 1 se les solicita que transformen un AFND a AFD
utilizando el algoritmo visto en clases. El AFND se recibe desde la entrada estándar, y el
resultado se escribe en la salida estándar. Es decir, en la consola de linux van a ejecutar lo
siguiente:    
```
tarea1P2 < afnd.txt > afd.txt 
```
Se usa la misma notación que en la Parte 1, vale decir, el alfabeto del archivo de texto
es el siguiente: Σ = {[a-z],[A-Z],[0-9],|,\_}. Por simpleza, se excluye la “ñ” del alfabeto.
Se usa el caracter “\_” para representar el símbolo ε. el caracter “:” para estados equivalentes, y otros caracteres (: = { , } ( )) para mejorar la visualización
del archivo.
Si consideramos el archivo del AFND producido de la Parte 1 se obtiene la siguiente
salida. Noten que podrían haber pequeñas variantes si es que cambian de orden los elementos

mostrados en el archivo.  
```
AFD M:  
K={q0,q1,q2,q3}
Sigma={a,b}  
delta:  
(q0,a,q1)  
(q0,b,q3)  
(q1,a,q3)  
(q1,b,q2)  
(q2,a,q3)  
(q2,b,q3)  
(q3,a,q3)  
(q3,b,q3)  
s=q0  
F={q2}
```

De nuevo, sigan el formato de los ejemplos. En este caso el AFD se escribe en un archivo
en que la primera línea tiene el encabezado AFD M:, luego viene el conjunto de estados K
en una línea, en la siguiente línea el alfabeto Σ del autómata también en una línea, luego la
funcion de transición δ, con cada transición en una línea separada (usando la misma notación
que en ∆), otra línea para el estado inicial y la última línea para el conjunto de estados finales
F.

### Parte 3: Minimización del AFD

En la tercera parte se les solicita que transforme un AFD en un AFD minimizado utilizando el algoritmo que se muestra en el libro Teoría de autómatas, lenguajes y computación,
de John E. Hopcroft, Rajeev Motwani y Jeffrey D. Ullman, Cap´ıtulo 4.4.3.
El AFD original se recibe desde la entrada estándar siguiendo el formato de la Parte
2 (noten que en este caso, en vez de nombrar a los estados como q0, q1, etc, se nombran
como A, B, etc). El resultado corresponde al AFD minimizado, que también se escribe en la
salida estándar usando la notación sugerida en el libro. Es decir, en la consola de linux van
a ejecutar lo siguiente:
```
tarea1P3 < afd.txt > afdM.txt
```
El alfabeto del archivo de texto es el siguiente: Σ = {[a-z],[A-Z],[0-9],|,\_}. Por
simpleza, se excluye la “ñ” del alfabeto. Se usa el caracter “\_” para representar el símbolo ε.
el caracter “:” para estados equivalentes, y otros caracteres (: = { , } ( )) para mejorar
la visualización del archivo.
Si consideramos el siguiente archivo de entrada (noten que conserva el formato del AFD
de la primera parte):  
```
AFD A:  
K={A,B,C,D,E,F,G,H}  
Sigma={0,1}  
delta:  
(A,0,B)  
(A,1,F)  
(B,0,G)  
(B,1,C)  
(C,0,A)  
(C,1,C)  
(D,0,C)  
(D,1,G)  
(E,0,H)  
(E,1,F)  
(F,0,C)  
(F,1,G)  
(G,0,G)  
(G,1,E)  
(H,0,G)  
(H,1,C)  
4  
s=A  
F={C} 
```
La salida correcta, utilizando el formato que se exige es:
```
AFDM A:  
K={A|E,G,B|H,D|F,C}  
Sigma={0,1}  
delta:  
(A|E,0,B|H)  
(A|E,1,D|F)  
(B|H,0,G)  
(B|H,1,C)  
(D|F,0,C)  
(D|F,1,G)  
(G,0,G)  
(G,1,A|E)  
(C,0,A|E)  
(C,1,C)  
s=A|E  
F={C}
```
