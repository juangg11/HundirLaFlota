import java.util.List;

public class HundirLaFlota {
	private Casilla[][] tablero = new Casilla[10][10];
	private Casilla[][] tablero2 = new Casilla[10][10];
	JugadorHumano jh = new JugadorHumano();
	JugadorAutomatico ja = new JugadorAutomatico();
	int numBarcos = 3;
	private char simbolo = '\u25A0';
	private int turno = 1;
	private static Sonido sonido = new Sonido();
	private static GestorArchivos archivo = new GestorArchivos();

	public void TurnoAzar() {

		turno = (int) (Math.random() * 2);
		if (turno == 1) {
			System.out.println("-------------------");
			System.out.println("Empieza el Jugador");
			System.out.println("-------------------");
		} else if (turno == 0) {
			System.out.println("-------------------");
			System.out.println("Empieza el Enemigo");
			System.out.println("-------------------");
		}
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

	public void Jugar() {
		jh.crearTablero(tablero);
		ja.crearTablero(tablero2, numBarcos);

		jh.colocacion(tablero, numBarcos);
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

		do {
			if (turno == 1) {
				System.out.println("Este es el tablero enemigo, para jugar, introduce las coordenadas de disparo:");
				ja.imprimirTablero(tablero2);

				contadorHundidos1 = jh.disparar(tablero2, contadorHundidos1);
				ja.imprimirTablero(tablero2);
				turno = 0;

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			else {

				contadorHundidos2 = ja.disparar(tablero, contadorHundidos2);
				turno = 1;
				jh.imprimirTablero(tablero);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				if (contadorHundidos1 == numBarcos) {
					System.out.println("¡Has ganado!");
					sonido.cargarSonido("Sonidos/ganar.wav");
					sonido.reproducir();
					Thread.sleep(3000);
					System.exit(0);
				} else if (contadorHundidos2 == numBarcos) {
					System.out.println("Has perdido");
					Thread.sleep(3000);
					System.exit(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (!juegoAcabado);

	}

	public static void main(String[] args) {
		//archivo.inicializarTablero();
		//List<String> valores = archivo.leerCSV();
		//valores.set(1, "1"+ System.lineSeparator());
		HundirLaFlota juego = new HundirLaFlota();
		juego.Tutorial();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		juego.Jugar();
		archivo.guardarCSV(valores);
	}
}