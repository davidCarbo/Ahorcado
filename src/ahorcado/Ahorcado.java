/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ahorcado;
import java.util.Scanner;

/**
 *
 * @author davidcarbomartinez
 */
public class Ahorcado {

    public static void main(String[] args) {
        // Declaramos variables 
        Scanner tec_frase = new Scanner(System.in);
        Scanner tec_letra = new Scanner(System.in);
        boolean newGame = true;

        while (newGame == true) {
            // Declaramos más variables (las declaramos aquí para que se reinicien para el juego nuevo)
            int intentos = 10, i = 0, j = 0;
            String frase;
            char letra;
            boolean juegoAcabado = false;
            boolean salir = false;
            // Pedimos la frase
            System.out.println("Jugador 1, introduce la frase en minúsculas: ");
            frase = tec_frase.nextLine();
            
            for (i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == ',' || frase.charAt(i) == '.') {
                    System.out.println("La frase solo puede contener letras minúsculas, ningún caracter esepecial. \n"
                            + "Prueba otra vez:");
                    frase = tec_frase.nextLine();
                }
            }

            // Ocultamos la frase:
            // - Hacemos que el array 'frase_oculta' tenga la misma cantidad de caracteres que 'frase'.
            // - Si tiene algún espacio lo añadimos, y si detecta letras, añadimos '_'.)
            char frase_oculta[] = new char[frase.length()];
            for (i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == ' ') {
                    frase_oculta[i] = ' ';
                } else {
                    frase_oculta[i] = '_';
                }
            }

            // Damos espacio en la consola para que jugador 2 no vea la frase
            System.out.println("\n"
                    + "\n"
                    + "\n"
                    + "\n");

            // El jugador 2 empieza a jugar (damos un poco de espacio a la consola para que se vea mejor).
            System.out.println("Mucha suerte jugador 2. \n"
                    + "\n");

            while (intentos > 0 && juegoAcabado == false) {
                // Mostramos frase oculta
                System.out.println("Intentos restantes: " + intentos + "\n");
                System.out.print("'");
                for (i = 0; i < frase_oculta.length; i++) {
                    System.out.print(" ");
                    System.out.print(frase_oculta[i]);
                }
                System.out.println("' \n");

                // Pedimos la letra
                System.out.println("Introduce una letra.");
                letra = tec_letra.next().charAt(0);
                System.out.println("");

                // Comprobamos si la letra está en la frase. 
                // Si está, pasamos al 'Camino 1', si no está, pasamos al 'Camino 2'. 
                boolean letraEnFrase = false;
                salir = false;

                for (i = 0; i < frase_oculta.length && salir == false; i++) {
                    if (letra == frase.charAt(i)) {
                        letraEnFrase = true;
                        salir = true;
                    }
                }

                // 'Camino 1' - La letra está en la frase:
                if (letraEnFrase == true) {
                    boolean letraMencionada = false;
                    for (i = 0; i < frase_oculta.length; i++) {
                        // Comprobamos si ya ha mencionado la letra anteriormente
                        if (letra == frase_oculta[i]) {
                            letraMencionada = true;
                        }
                        // Agregamos la letra a la frase oculta
                        if (letra == frase.charAt(i)) {
                            frase_oculta[i] = frase.charAt(i);
                        }
                    }

                    // Si ya ha mencionado la letra, le restamos un punto: 
                    if (letraMencionada == true) {
                        intentos--;
                    }

                    // Comprobamos si aún quedan '_' en la frase.
                    salir = false;
                    for (i = 0; i < frase_oculta.length && salir == false; i++) {
                        if (frase_oculta[i] == '_') {
                            salir = true;
                        }
                    }

                    // Si no quedan '_' (salir == false), mostramos la frase rellena y finalizamos el juego.
                    if (salir == false) {
                        System.out.print("--> ");
                        for (i = 0; i < frase_oculta.length; i++) {
                            System.out.print(frase_oculta[i]);
                        }
                        System.out.println(" <-- \n"
                                + "\n"
                                + "¡Felicidades! Has ganado.");
                        juegoAcabado = true;
                    }

                    // 'Camino 2' - Si no está la letra en la frase, restamos un intento:    
                } else {
                    intentos--;
                }

            }

            // Si se terminan los intentos, pierde.
            if (intentos <= 0) {
                System.out.println("Lo siento, has perdido.");
            }

            // Preguntamos si quiere inicializar un nuevo juego.
            System.out.println("¿Quieres volver a jugar?");
            String respuesta;
            respuesta = tec_frase.nextLine();

            // Inicializamos de nuevo o terminamos el programa.
            salir = false;
            while (salir == false) {
                if (respuesta.equalsIgnoreCase("no")) {
                    System.out.println("Hasta pronto.");
                    newGame = false;
                    salir = true;
                } else if (respuesta.equalsIgnoreCase("SI")) {
                    newGame = true;
                    salir = true;
                } else {
                    System.out.println("Solo puedes decir: 'si' o 'no'.");
                    respuesta = tec_frase.nextLine();
                }
            }
        }
    }
}
