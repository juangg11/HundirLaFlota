import java.util.List;
import java.util.Scanner;

public class JugadorHumano {
	private char simbolo = '\u25A0';
	private Scanner sc = new Scanner(System.in);
	private static Sonido sonido = new Sonido();
	private int fila;
	private int columna;
	private int orientacion;
	private boolean datosCorrectos;
	private GestorArchivos archivo = new GestorArchivos();
	private List<String> valores = archivo.leerCSV();

	public List<String> getValores() {
		return valores;
	}
	public void crearTablero(Casilla[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = new Casilla();
			}
		}
		imprimirTablero(tablero);
	}

	public void imprimirTablero(Casilla[][] tablero) {
		System.out.println(" ");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (tablero[i][j].getOcupado() && tablero[i][j].getHundido()) {
					System.out.print("\u001B[31m" + simbolo + "  ");
				} else if (tablero[i][j].getOcupado() && !tablero[i][j].getHundido() && tablero[i][j].getTocado()) {
					System.out.print("\u001B[33m" + simbolo + "  ");
				} else if (tablero[i][j].getOcupado() && !tablero[i][j].getHundido() && !tablero[i][j].getTocado()) {
					System.out.print("\u001B[35m" + simbolo + "  ");
				} else if (tablero[i][j].getAgua()) {
					System.out.print("\u001B[34m" + simbolo + "  ");
				} else {
					System.out.print("\u001B[32m" + simbolo + "  ");
				}
			}
			System.out.println("\u001B[0m");
		}
		System.out.println(" ");
	}

	public void colocacion(Casilla[][] tablero, int num) {

		Barco barcos[] = new Barco[num];

		datosCorrectos = false;

		for (int i = 0; i < barcos.length; i++) {
			barcos[i] = new Barco(i + 1);
		}

		for (int i = 0; i < barcos.length; i++) {
			do {
				System.out.println("Coloca los barcos: ");
				System.out.println("Introduce la fila: ");
				fila = sc.nextInt();
				System.out.println("Introduce la columna: ");
				columna = sc.nextInt();
				System.out.println("Introduce la orientacion (1 = horizontal, 0 = vertical): ");
				orientacion = sc.nextInt();

				if (fila < 0 || fila > 9 || columna < 0 || columna > 9 || orientacion < 0 || orientacion > 1) {
					System.out.println("----------X---------");
					System.out.println("\u001B[31mPosicion no valida\u001B[0m");
					System.out.println("----------X---------");
					datosCorrectos = false;
				} else if (orientacion == 1 && columna > 10 - barcos[i].getTamanio()) {
					System.out.println("----------X---------");
					System.out.println("\u001B[31mPosicion no valida\u001B[0m");
					System.out.println("----------X---------");
					datosCorrectos = false;
				} else if (orientacion == 0 && fila > 10 - barcos[i].getTamanio()) {
					System.out.println("----------X---------");
					System.out.println("\u001B[31mPosicion no valida\u001B[0m");
					System.out.println("----------X---------");
					datosCorrectos = false;
				} else {
					datosCorrectos = true;
				}
			} while (!datosCorrectos);

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
			imprimirTablero(tablero);
			
			int prob = Integer.parseInt(valores.get((fila + columna) + 1));
			int indice = valores.indexOf(fila + "" + columna) + 1;
			valores.set(indice, String.valueOf(prob + 1));
		}
	}

	public int disparar(Casilla[][] tablero2, int contadorHundidos1) {

		do {
			System.out.println("Introduce la fila: ");
			fila = sc.nextInt();
			System.out.println("Introduce la columna: ");
			columna = sc.nextInt();

			if (fila < 0 || fila > 9 || columna < 0 || columna > 9 || tablero2[fila][columna].getAgua()
					|| tablero2[fila][columna].getHundido() || tablero2[fila][columna].getTocado()) {
				System.out.println("----------X---------");
				System.out.println("\u001B[31mDatos incorrectos\u001B[0m");
				System.out.println("----------X---------");
				datosCorrectos = false;
			} else {
				datosCorrectos = true;
			}
		} while (!datosCorrectos);

		if (tablero2[fila][columna].getOcupado() && !tablero2[fila][columna].getAgua()) {
			System.out.println("-------------------");
			System.out.println("¡Tocado!");
			System.out.println("-------------------");
			sonido.cargarSonido("Sonidos/disparo.wav");
			sonido.reproducir();
			tablero2[fila][columna].setTocado(true);
			tablero2[fila][columna].setAgua(false);
			tablero2[fila][columna].getBarco().numTocado++;

			if (tablero2[fila][columna].getBarco().comprobarHundido()) {
				for (int i = 0; i < tablero2.length; i++) {
					for (int j = 0; j < tablero2[0].length; j++) {
						if (tablero2[i][j].getBarco() == tablero2[fila][columna].getBarco()) {
							tablero2[i][j].setHundido(true);
							tablero2[i][j].setTocado(false);
						}
					}
				}
				contadorHundidos1++;
			}
		} else if (tablero2[fila][columna].getAgua()) {
			System.out.println("Casilla Repetida");
		} else {
			System.out.println("-------------------");
			System.out.println("Agua");
			System.out.println("-------------------");
			sonido.cargarSonido("Sonidos/agua.wav");
			sonido.reproducir();
			tablero2[fila][columna].setAgua(true);

		}
		return contadorHundidos1;
	}
}
