import java.util.*;

public class JugadorAutomatico {
    private char simbolo = '\u25A0';
    private static Sonido sonido = new Sonido();
    private GestorArchivos archivo = new GestorArchivos();
    private List<String> valores = archivo.leerCSV();

    public void crearTablero(Casilla[][] tablero2, int num) {
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                tablero2[i][j] = new Casilla();
            }
        }

        Barco barcos2[] = new Barco[num];
        for (int i = 0; i < barcos2.length; i++) {
            barcos2[i] = new Barco(i + 1);
        }

        int contador = 0;
        while (contador < num) {
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

    public void imprimirTablero(Casilla[][] tablero2) {
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

	public int disparar(Casilla[][] tablero, int contadorHundidos2) {
		Map<String, Integer> mapaFrecuencia = new HashMap<>();
		List<String> listaPonderada = new ArrayList<>();
		
		for (int i = 0; i < valores.size(); i += 2) {
			String casilla = valores.get(i);
			int frecuencia = Integer.parseInt(valores.get(i + 1));
			mapaFrecuencia.put(casilla, frecuencia);
			
			for (int j = 0; j < frecuencia + 1; j++) {
				listaPonderada.add(casilla);
			}
		}
		
		boolean disparoValido = false;
		int fila = 0, columna = 0;
		
		while (!disparoValido) {
			String seleccion = listaPonderada.get((int) (Math.random() * listaPonderada.size()));
			fila = Character.getNumericValue(seleccion.charAt(0));
			columna = Character.getNumericValue(seleccion.charAt(1));
			
			if (!tablero[fila][columna].getAgua() && !tablero[fila][columna].getTocado() && !tablero[fila][columna].getHundido()) {
				disparoValido = true;
			}
		}
		
		if (tablero[fila][columna].getOcupado()) {
			System.out.println("-------------------");
			System.out.println("El enemigo ha tocado un barco en " + fila + "," + columna);
			System.out.println("-------------------");
			sonido.cargarSonido("Sonidos/disparo.wav");
			sonido.reproducir();
			tablero[fila][columna].setTocado(true);
			tablero[fila][columna].getBarco().numTocado++;
			
			if (tablero[fila][columna].getBarco().comprobarHundido()) {
				for (int i = 0; i < tablero.length; i++) {
					for (int j = 0; j < tablero[0].length; j++) {
						if (tablero[i][j].getBarco() == tablero[fila][columna].getBarco()) {
							tablero[i][j].setHundido(true);
							tablero[i][j].setTocado(false);
						}
					}
				}
				contadorHundidos2++;
			}
		} else {
			System.out.println("-------------------");
			System.out.println("El enemigo ha disparado al agua en " + fila + "," + columna);
			System.out.println("-------------------");
			sonido.cargarSonido("Sonidos/agua.wav");
			sonido.reproducir();
			tablero[fila][columna].setAgua(true);
		}
		
		return contadorHundidos2;
	}	
}