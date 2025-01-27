import java.util.Scanner;

public class HundirLaFlota {
    private Casilla[][] tablero = new Casilla[10][10];
    private Casilla[][] tablero2 = new Casilla[10][10];
    private char simbolo = '\u25A0';

    public void CrearTablero(){
        for (int i = 0; i < tablero.length; i++) 
        {
            for (int j = 0; j < tablero[0].length; j++) 
            {
                tablero[i][j] = new Casilla(); 
            }
        }
        imprimirTablero();
    }

    public void CrearTablero2(){
        for (int i = 0; i < tablero2.length; i++) 
        {
            for (int j = 0; j < tablero2[0].length; j++) 
            {
                tablero2[i][j] = new Casilla(); 
            }
        }
        imprimirTablero2();
    }


    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].getOcupado()) 
                {
                    System.out.print("\u001B[31m" + simbolo + "  ");
                } else 
                {
                    System.out.print("\u001B[32m" + simbolo + "  "); 
                }
            }
            System.out.println("\u001B[0m"); 
        }
    }

    public void imprimirTablero2() {
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                if (tablero2[i][j].getAgua()) {
                    System.out.print("\u001B[34m" + simbolo + "  ");
                } else if (tablero2[i][j].getTocado()) {
                    System.out.print("\u001B[31m" + simbolo + "  ");
                } else {
                    System.out.print("\u001B[32m" + simbolo + "  ");
                }
            }
            System.out.println("\u001B[0m");
        }
    }
    
    public void Jugar()
    {   
        CrearTablero();

        Scanner sc  = new Scanner(System.in);
        
        Barco barcos[] = new Barco[3];

        boolean datosCorrectos = false;

        int fila;
        int columna;
        int orientacion;

        for (int i = 0; i < barcos.length; i++)
        {
            barcos[i] = new Barco(i+1);
        }

        for (int i = 0; i < barcos.length; i++)
        {
            do 
            {
                System.out.println("-------------------");
                System.out.println("Coloca los barcos: ");
                System.out.println("-------------------");
                System.out.println("Introduce la fila: ");
                fila = sc.nextInt();
                System.out.println("-------------------");
                System.out.println("Introduce la columna: ");
                columna = sc.nextInt();
                System.out.println("-------------------");
                System.out.println("Introduce la orientacion (1 = horizontal, 0 = vertical): ");
                System.out.println("-------------------");
                orientacion = sc.nextInt();

                if(fila < 0 || fila > 9 || columna < 0 || columna > 9 || orientacion < 0 || orientacion > 1)
                {   
                    System.out.println("----------X---------");
                    System.out.println("\u001B[31mDatos incorrectos\u001B[0m");
                    System.out.println("----------X---------");
                    datosCorrectos = false;
                }
                else
                {
                    datosCorrectos = true;
                }
            }
            while(!datosCorrectos);

            System.out.println("-------------------");

            if (tablero[fila][columna].getOcupado())
            {
                System.out.println("----------X---------");
                System.out.println("\u001B[31mCasilla ocupada\u001B[0m");
                System.out.println("----------X---------");
                i--;
            }
            else if (orientacion == 0)
                {
                    for (int j = 0; j < barcos[i].getTamanio(); j++)
                    {
                        if (fila + j < tablero.length)
                        {
                            if(tablero[fila+j][columna].getOcupado())
                            {
                                System.out.println("----------X---------");
                                System.out.println("\u001B[31mNo se puede colocar el barco aquí\u001B[0m");
                                System.out.println("----------X---------");
                                i--;
                                for (int a = j; a > 0; a--)
                                {
                                    tablero[fila+a-1][columna].setOcupado(false);
                                    tablero[fila+a-1][columna].setBarco(null);
                                }
                            }
                            else 
                            {
                                tablero[fila+j][columna].setBarco(barcos[i]);
                                tablero[fila+j][columna].setOcupado(true);
                            }
                        }
                        else 
                        {
                            System.out.println("----------X---------");
                            System.out.println("\u001B[31mNo se puede colocar el barco aquí\u001B[0m");
                            System.out.println("----------X---------");
                            i--;
                            
                            for (int a = j; a > 0; a--)
                            {
                                tablero[fila+a-1][columna].setOcupado(false);
                                tablero[fila+a-1][columna].setBarco(null);
                            }
                        }
                    }
                }
                else
                {

                    for (int k = 0; k < barcos[i].getTamanio(); k++)
                    {
                        if (fila + k < tablero.length)
                        {
                            if(tablero[fila][columna+k].getOcupado())
                            {
                                System.out.println("----------X---------");
                                System.out.println("\u001B[31mNo se puede colocar el barco aquí\u001B[0m");
                                System.out.println("----------X---------");
                                i--;
                                for (int a = k; a > 0; a--)
                                {
                                    tablero[fila][columna+a-1].setOcupado(false);
                                    tablero[fila][columna+a-1].setBarco(null);
                                }
                            }
                            else 
                            {
                                tablero[fila][columna+k].setBarco(barcos[i]);
                                tablero[fila][columna+k].setOcupado(true);
                            }
                        }
                        else 
                        {
                            System.out.println("----------X---------");
                            System.out.println("\u001B[31mNo se puede colocar el barco aquí\u001B[0m");
                            System.out.println("----------X---------");
                            i--;
                            
                            for (int a = k; a > 0; a--)
                            {
                                tablero[fila][columna+a-1].setOcupado(false);
                                tablero[fila][columna+a-1].setBarco(null);
                            }
                        }
                    }
                }
                
            imprimirTablero();
        }

        System.out.println("-------------------");
        System.out.println("Has colocado los barcos");
        System.out.println("-------------------");
        System.out.println("Este es el tablero enemigo, para jugar, introduce las coordenadas de disparo:");
        System.out.println("-------------------");
        CrearTablero2();

        boolean juegoAcabado = false;

        do
        {
            do 
            {
                System.out.println("-------------------");
                System.out.println("Introduce la fila: ");
                fila = sc.nextInt();
                System.out.println("-------------------");
                System.out.println("Introduce la columna: ");
                columna = sc.nextInt();
                System.out.println("-------------------");
                tablero2[fila][columna].setAgua(true);
    
                if(fila < 0 || fila > 9 || columna < 0 || columna > 9)
                {   
                    System.out.println("----------X---------");
                    System.out.println("\u001B[31mDatos incorrectos\u001B[0m");
                    System.out.println("----------X---------");
                    datosCorrectos = false;
                }
                else
                {
                    datosCorrectos = true;
                }
            }
            while(!datosCorrectos);
    
            if (tablero2[fila][columna].getOcupado())
            {
                System.out.println("¡Tocado!");
                System.out.println("-------------------");
                tablero2[fila][columna].setTocado(true);
                comprobarHundido(tablero2, fila, columna);
                imprimirTablero2();
            }
            else
            {
                System.out.println("¡Agua!");
                System.out.println("-------------------");
                tablero2[fila][columna].setAgua(true);
                imprimirTablero2();
            }
        }
        while(!juegoAcabado);
    }

    public void comprobarHundido(Casilla[][] tablero, int fila, int columna) {
        Casilla casillaTocada = tablero[fila][columna];
        int recorrido = casillaTocada.getBarco().getTamanio();
        int contador = 0;
        for(int i = 0; i < recorrido; i++)
        {
            if(tablero[fila][columna+i].getTocado()){
                contador++;
            }
        }
        if(contador == 0){
            System.out.println("¡Hundido!");
            casillaTocada.getBarco().hundido = true;
            casillaTocada.tocarCasilla(tablero, casillaTocada.getBarco());
        }
    }
    public static void main(String[] args) {
        HundirLaFlota juego = new HundirLaFlota();
        juego.Jugar();
    }
}