import java.util.Scanner;

public class HundirLaFlota {
    private Casilla[][] tablero = new Casilla[10][10];
    private Casilla[][] tablero2 = new Casilla[10][10];
    int NumBarcos = 3;
    private char simbolo = '\u25A0';
    private int Turno = 1;
    private static Sonido sonido = new Sonido();
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

    public void TurnoAzar()
    {    
        Turno = (int) (Math.random() * 2);
        if (Turno == 1)
        {
            System.out.println("-------------------");
            System.out.println("Empieza el Jugador");
            System.out.println("-------------------");
        }
        else if (Turno == 0)
        {
            System.out.println("-------------------");
            System.out.println("Empieza el Enemigo");
            System.out.println("-------------------");
        }
    }

    public void CrearTablero2() {
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                tablero2[i][j] = new Casilla();
            }
        }
    
        Barco barcos2[] = new Barco[NumBarcos];
    
        for (int i = 0; i < barcos2.length; i++) {
            barcos2[i] = new Barco(i + 1);
        }
    
        int contador = 0;
        while (contador < NumBarcos) {
            int Random1 = (int) (Math.random() * 10);
            int Random2 = (int) (Math.random() * 10);
            int Random3 = (int) (Math.random() * 2);
            int contador2 = 0;
            boolean colocacionExitosa = true;
    
            do {
                try {
                    if (Random3 == 0) { 
                        if (Random2 + contador2 >= 10) { 
                            colocacionExitosa = false;
                            break;
                        }
                        tablero2[Random1][Random2 + contador2].setBarco(barcos2[contador]);
                        tablero2[Random1][Random2 + contador2].setOcupado(true);
                    } else { 
                        if (Random1 + contador2 >= 10) { 
                            colocacionExitosa = false;
                            break;
                        }
                        tablero2[Random1 + contador2][Random2].setBarco(barcos2[contador]);
                        tablero2[Random1 + contador2][Random2].setOcupado(true);
                    }
                    contador2++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    colocacionExitosa = false;
                    break;
                }
            } while (contador2 < barcos2[contador].getTamanio());
    
            if (colocacionExitosa) {
                contador++;
            }
        }
    }
    

    public void imprimirTablero() {
        System.out.println(" ");
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
        System.out.println(" ");
    }

    public void imprimirTablero2() {
        System.out.println(" ");
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
        System.out.println(" ");
    }
    
    public void Tutorial() {
        System.out.println("-------------------");
        System.out.println("\u001B[1mBienvenido a Hundir la Flota");
        System.out.println("El objetivo del juego es hundir los barcos enemigos antes de que hunda los tuyos");
        System.out.println("Para colocar los barcos, introduce la fila, la columna y la orientación");
        System.out.println("Para disparar, introduce solo la fila y la columna");
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
        CrearTablero2();

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
                System.out.println("Coloca los barcos: ");
                System.out.println("Introduce la fila: ");
                fila = sc.nextInt();
                System.out.println("Introduce la columna: ");
                columna = sc.nextInt();
                System.out.println("Introduce la orientacion (1 = horizontal, 0 = vertical): ");
                orientacion = sc.nextInt();

                if(fila < 0 || fila > 9 || columna < 0 || columna > 9 || orientacion < 0 || orientacion > 1)
                {   
                    System.out.println("----------X---------");
                    System.out.println("\u001B[31mPosicion no valida\u001B[0m");
                    System.out.println("----------X---------");
                    datosCorrectos = false;
                }
                else if (orientacion == 1 && columna > 10 - barcos[i].getTamanio())
                {
                    System.out.println("----------X---------");
                    System.out.println("\u001B[31mPosicion no valida\u001B[0m");
                    System.out.println("----------X---------");
                    datosCorrectos = false;
                }
                else if (orientacion == 0 && fila > 10 - barcos[i].getTamanio())
                {
                    System.out.println("----------X---------");
                    System.out.println("\u001B[31mPosicion no valida\u001B[0m");
                    System.out.println("----------X---------");
                    datosCorrectos = false;
                }
                else
                {
                    datosCorrectos = true;
                }
            }
            while(!datosCorrectos);

            if (tablero[fila][columna].getOcupado()) {
                System.out.println("----------X---------");
                System.out.println("\u001B[31mCasilla ocupada\u001B[0m");
                System.out.println("----------X---------");
                i--;
            } else {
                boolean espacioLibre = true;
                if (orientacion == 0) {
                    for (int j = 0; j < barcos[i].getTamanio(); j++) {
                        if (fila + j >= tablero.length || tablero[fila + j][columna].getOcupado()) {
                            espacioLibre = false;
                            break;
                        }
                    }
                    if (espacioLibre) {
                        for (int j = 0; j < barcos[i].getTamanio(); j++) {
                            tablero[fila + j][columna].setBarco(barcos[i]);
                            tablero[fila + j][columna].setOcupado(true);
                        }
                    } else {
                        System.out.println("--------------X--------------");
                        System.out.println("\u001B[31mNo se puede colocar el barco aquí\u001B[0m");
                        System.out.println("--------------X--------------");
                        i--;
                    }
                } else {
                    for (int k = 0; k < barcos[i].getTamanio(); k++) {
                        if (columna + k >= tablero[0].length || tablero[fila][columna + k].getOcupado()) {
                            espacioLibre = false;
                            break;
                        }
                    }
                    if (espacioLibre) {
                        for (int k = 0; k < barcos[i].getTamanio(); k++) {
                            tablero[fila][columna + k].setBarco(barcos[i]);
                            tablero[fila][columna + k].setOcupado(true);
                        }
                    } else {
                        System.out.println("---------------X--------------");
                        System.out.println("\u001B[31mNo se puede colocar el barco aquí\u001B[0m");
                        System.out.println("---------------X--------------");
                        i--;
                    }
                }
            }

            imprimirTablero();
        }

        System.out.println("Has colocado los barcos");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TurnoAzar();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean juegoAcabado = false;
        int contadorHundidos1 = 0;
        int contadorHundidos2 = 0;

        do
        {
            if(Turno == 1)
            {
                System.out.println("Este es el tablero enemigo, para jugar, introduce las coordenadas de disparo:");
                imprimirTablero2();

                do 
                {
                    System.out.println("Introduce la fila: ");
                    fila = sc.nextInt();
                    System.out.println("Introduce la columna: ");
                    columna = sc.nextInt();
        
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
                    System.out.println("-------------------");
                    System.out.println("¡Tocado!");
                    System.out.println("-------------------");
                    sonido.cargarSonido("Sonidos/disparo.wav");
                    sonido.reproducir();
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
                    System.out.println("Casilla Repetida");
                }
                else
                {
                    System.out.println("-------------------");
                    System.out.println("Agua");
                    System.out.println("-------------------");
                    sonido.cargarSonido("Sonidos/agua.wav");
                    sonido.reproducir();
                    tablero2[fila][columna].setAgua(true);
                    Turno = 0;
                }
                
                imprimirTablero2();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            else if(Turno == 0)
            {
                int Random1;
                int Random2;
                boolean disparoValido = false;
                while (!disparoValido) 
                {
                    Random1 = (int) (Math.random() * 10);
                    Random2 = (int) (Math.random() * 10);
                    if (!tablero[Random1][Random2].getAgua() && !tablero[Random1][Random2].getTocado() && !tablero[Random1][Random2].getHundido()) {
                        disparoValido = true;
                        if (tablero[Random1][Random2].getOcupado()) 
                        {
                            System.out.println("-------------------");
                            System.out.println("El enemigo ha tocado un barco");
                            System.out.println("-------------------");
                            sonido.cargarSonido("Sonidos/disparo.wav");
                            sonido.reproducir();
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
    
                        } else 
                        {
                            System.out.println("-------------------");
                            System.out.println("El enemigo ha disparado al agua");
                            System.out.println("-------------------");
                            sonido.cargarSonido("Sonidos/agua.wav");
                            sonido.reproducir();

                            tablero[Random1][Random2].setAgua(true);
                            Turno = 1;
                        }
                    }
                }
    
                imprimirTablero();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (contadorHundidos1 == NumBarcos) {
                    System.out.println("¡Has ganado!");
                    sonido.cargarSonido("Sonidos/ganar.wav");
                    sonido.reproducir();
                    Thread.sleep(3000);
                    System.exit(0);
                } else if (contadorHundidos2 == NumBarcos) {
                    System.out.println("Has perdido");
                    Thread.sleep(3000);
                    System.exit(0);
                }
                } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        while(!juegoAcabado);
        sc.close();
    }

    public static void main(String[] args) 
    {
        HundirLaFlota juego = new HundirLaFlota();
        juego.Tutorial();

        try 
        {
            Thread.sleep(10000);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        juego.Jugar();
    }
}