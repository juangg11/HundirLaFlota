
public class JugadorAutomatico {
	private char simbolo = '\u25A0';
	private static Sonido sonido = new Sonido();

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
		int Random1;
		int Random2;
		boolean disparoValido = false;
		while (!disparoValido) {
			Random1 = (int) (Math.random() * 10);
			Random2 = (int) (Math.random() * 10);
			if (!tablero[Random1][Random2].getAgua() && !tablero[Random1][Random2].getTocado()
					&& !tablero[Random1][Random2].getHundido()) {
				disparoValido = true;
				if (tablero[Random1][Random2].getOcupado()) {
					System.out.println("-------------------");
					System.out.println("El enemigo ha tocado un barco");
					System.out.println("-------------------");
					sonido.cargarSonido("Sonidos/disparo.wav");
					sonido.reproducir();
					tablero[Random1][Random2].setTocado(true);
					tablero[Random1][Random2].getBarco().numTocado++;

					if (tablero[Random1][Random2].getBarco().comprobarHundido()) {
						for (int i = 0; i < tablero.length; i++) {
							for (int j = 0; j < tablero[0].length; j++) {
								if (tablero[i][j].getBarco() == tablero[Random1][Random2].getBarco()) {
									tablero[i][j].setHundido(true);
									tablero[i][j].setTocado(false);
								}
							}
						}
						contadorHundidos2++;
					}

				} else {
					System.out.println("-------------------");
					System.out.println("El enemigo ha disparado al agua");
					System.out.println("-------------------");
					sonido.cargarSonido("Sonidos/agua.wav");
					sonido.reproducir();

					tablero[Random1][Random2].setAgua(true);
				}
			}
		}

		return contadorHundidos2;
	}
}
