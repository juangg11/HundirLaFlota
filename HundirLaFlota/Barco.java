public class Barco {
    private int tamanio;
    public boolean hundido;
    public int numTocado;

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
            System.out.println("Barco hundido");
            return true;
        }
        else {
            return false;
        }
    }
}
