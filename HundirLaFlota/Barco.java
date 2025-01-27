public class Barco {
    private int tamanio;
    public boolean hundido;

    public Barco(int tamanio) {
        this.tamanio = tamanio;
        this.hundido = false;
    }
    public int getTamanio() {
        return tamanio;
    }
}
