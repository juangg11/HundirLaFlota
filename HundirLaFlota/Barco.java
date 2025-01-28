public class Barco {
    private int tamanio;
    public boolean hundido;
    public int numTocado;
    private static Sonido sonido = new Sonido();

    public Barco(int tamanio) {
        this.tamanio = tamanio;
        this.hundido = false;
        numTocado = 0;
    }
    public int getTamanio() {
        return tamanio;
    }
    public boolean comprobarHundido() {
        if (numTocado == tamanio) {
            System.out.println("-------------------");
            System.out.println("Barco hundido");
            System.out.println("-------------------");
            sonido.cargarSonido("Sonidos/explosion.wav");
            sonido.reproducir();
            return true;
        }
        else {
            return false;
        }
    }
}
