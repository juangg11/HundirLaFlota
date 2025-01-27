import java.util.Scanner;

public class HundirLaFlota {
    private Casilla[][] tablero = new Casilla[10][10];
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

    public void Jugar()
    {   
        CrearTablero();

        Scanner sc  = new Scanner(System.in);
        
        Barco barcos[] = new Barco[5];

        for (int i = 0; i < barcos.length; i++)
        {
            barcos[i] = new Barco(i+1);
        }

        for (int i = 0; i < barcos.length; i++)
        {
            System.out.println("Coloca los barcos: ");
            System.out.println("Introduce la fila: ");
            int fila = sc.nextInt();
            System.out.println("Introduce la columna: ");
            int columna = sc.nextInt();
            System.out.println("Introduce la orientacion (1 = horizontal, 0 = vertical): ");
            int orientacion = sc.nextInt();
            if (tablero[fila][columna].getOcupado())
            {
                System.out.println("Casilla ocupada");
                i--;
            }
            else 
                if (orientacion == 0)
                {
                    for (int j = 0; j < barcos[i].getTamanio(); j++)
                    {
                        if (fila + j < tablero.length)
                        {
                            tablero[fila+j][columna].setBarco(barcos[i]);
                            tablero[fila+j][columna].setOcupado(true);
                        }
                        else {
                            System.out.println("No se puede colocar el barco");
                            i--;
                            
                            for (int a = j; a > 0; a--)
                            {
                                tablero[fila][columna+a-1].setOcupado(false);
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
                            tablero[fila][columna+k].setBarco(barcos[i]);
                            tablero[fila][columna+k].setOcupado(true);
                        }
                        else 
                        {
                            System.out.println("No se puede colocar el barco");
                            i--;

                            for (int a = k; a > 0; a--)
                            {
                                tablero[fila][columna+a-1].setOcupado(false);
                            }
                        }
                    }
                }
                
                imprimirTablero();
        }
    }

    public static void main(String[] args) {
        HundirLaFlota juego = new HundirLaFlota();
        juego.Jugar();
    }
}