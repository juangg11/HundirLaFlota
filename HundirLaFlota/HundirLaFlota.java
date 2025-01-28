import java.util.Scanner;

public class HundirLaFlota {
    private Casilla[][] tablero = new Casilla[10][10];
    private Casilla[][] tablero2 = new Casilla[10][10];
    int NumBarcos = 3;
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

        Barco barcos2[] = new Barco[NumBarcos];

        for (int i = 0; i < barcos2.length; i++)
        {
            barcos2[i] = new Barco(i+1);
        }


        int contador = 0;
        while(contador < NumBarcos)
        {
            int Random1 = (int) (Math.random() * 10);
            int Random2 = (int) (Math.random() * 10);
            int Random3 = (int) (Math.random() * 2);
            int contador2 = 0;
            do
            {
                if(Random3 == 0)
                {
                    tablero2[Random1][Random2+contador2].setBarco(barcos2[contador]);
                    tablero2[Random1][Random2+contador2].setOcupado(true);
                }
                else
                {
                    tablero2[Random1+contador2][Random2].setBarco(barcos2[contador]);
                    tablero2[Random1+contador2][Random2].setOcupado(true);
                }
                contador2++;
            }
            while(contador2<contador+1);

            contador++;
        }
        imprimirTablero2();
    }


    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].getOcupado() && tablero[i][j].getHundido()) 
                {
                    System.out.print("\u001B[31m" + simbolo + "  ");
                } else if (tablero[i][j].getOcupado() && !tablero[i][j].getHundido() && tablero[i][j].getTocado()) 
                {
                    System.out.print("\u001B[33m" + simbolo + "  "); 
                } else if (tablero[i][j].getOcupado() && !tablero[i][j].getHundido() && !tablero[i][j].getTocado()) 
                {
                    System.out.print("\u001B[35m" + simbolo + "  "); 
                } else if (tablero[i][j].getAgua()) 
                {
                    System.out.print("\u001B[34m" + simbolo + "  ");
                } else {
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
                    System.out.print("\u001B[33m" + simbolo + "  ");
                } else if (tablero2[i][j].getHundido()) {
                    System.out.print("\u001B[31m" + simbolo + "  ");
                } else {
                    System.out.print("\u001B[32m" + simbolo + "  ");
                }
            }
            System.out.println("\u001B[0m");
        }
    }
    
    public void Tutorial() {
        System.out.println("-------------------");
        System.out.println("\u001B[1mBienvenido a Hundir la Flota");
        System.out.println("El objetivo del juego es hundir los barcos enemigos antes de que él hunda los tuyos");
        System.out.println("Para colocar los barcos, introduce la fila, la columna y la orientación (1 = horizontal, 0 = vertical)");
        System.out.println("Para disparar, introduce la fila y la columna");
        System.out.println("-------------------");
        System.out.println("\u001B[34m" + simbolo + "\u001B[0m Indica Agua");
        System.out.println("\u001B[33m" + simbolo + "\u001B[0m Indica Tocado");
        System.out.println("\u001B[31m" + simbolo + "\u001B[0m Indica Hundido");
        System.out.println("\u001B[35m" + simbolo + "\u001B[0m Indica Barco");
        System.out.println("-------------------");
    }

    public void Jugar()
    {   
        CrearTablero();

        Scanner sc  = new Scanner(System.in);
        
        Barco barcos[] = new Barco[NumBarcos];

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
        int contadorHundidos1 = 0;
        int contadorHundidos2 = 0;

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
    
                if(fila < 0 || fila > 9 || columna < 0 || columna > 9 || tablero2[fila][columna].getAgua() || tablero2[fila][columna].getHundido() || tablero2[fila][columna].getTocado())
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
    
            if (tablero2[fila][columna].getOcupado() && !tablero2[fila][columna].getAgua())
            {
                System.out.println("¡Tocado!");
                System.out.println("-------------------");
                tablero2[fila][columna].setTocado(true);
                tablero2[fila][columna].setAgua(false);
                tablero2[fila][columna].getBarco().numTocado++;
                
                if(tablero2[fila][columna].getBarco().comprobarHundido())
                {
                    for(int i = 0; i < tablero2.length; i++)
                    {
                        for(int j = 0; j < tablero2[0].length; j++)
                        {
                            if(tablero2[i][j].getBarco() == tablero2[fila][columna].getBarco())
                            {
                                tablero2[i][j].setHundido(true);
                                tablero2[i][j].setTocado(false);
                            }
                        }
                    }
                    contadorHundidos1++;
                }
            }
            else if (tablero2[fila][columna].getAgua())
            {
                System.out.println("¡Casilla Repetida!");
                System.out.println("-------------------");
            }
            else
            {
                System.out.println("¡Agua!");
                System.out.println("-------------------");
                tablero2[fila][columna].setAgua(true);
            }
            
            imprimirTablero2();

            int Random1;
            int Random2;
            boolean disparoValido = false;
            while (!disparoValido) {
                Random1 = (int) (Math.random() * 10);
                Random2 = (int) (Math.random() * 10);
                if (!tablero[Random1][Random2].getAgua() && !tablero[Random1][Random2].getTocado() && !tablero[Random1][Random2].getHundido()) {
                    disparoValido = true;
                    if (tablero[Random1][Random2].getOcupado()) {
                        System.out.println("El enemigo ha tocado un barco");
                        System.out.println("-------------------");
                        tablero[Random1][Random2].setTocado(true);
                        tablero[Random1][Random2].getBarco().numTocado++;

                        if(tablero[Random1][Random2].getBarco().comprobarHundido())
                        {
                            for(int i = 0; i < tablero.length; i++)
                            {
                                for(int j = 0; j < tablero[0].length; j++)
                                {
                                    if(tablero[i][j].getBarco() == tablero[Random1][Random2].getBarco())
                                    {
                                    tablero[i][j].setHundido(true);
                                    tablero[i][j].setTocado(false);
                                    }
                                }
                            }
                        contadorHundidos2++;
                        }

                    } else {
                        System.out.println("El enemigo ha disparado al agua");
                        System.out.println("-------------------");
                        tablero[Random1][Random2].setAgua(true);
                    }
                }
            }

            imprimirTablero();

            if (contadorHundidos1 == NumBarcos){
                System.out.println("¡Has ganado!");
            }   
            else if (contadorHundidos2 == NumBarcos)
            {
                System.out.println("¡Has perdido!");
            }
        }
        while(!juegoAcabado);
        sc.close();
    }

    public static void main(String[] args) 
    {
        HundirLaFlota juego = new HundirLaFlota();
        juego.Tutorial();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        juego.Jugar();
    }
}