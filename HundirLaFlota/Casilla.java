public class Casilla {
    private boolean Ocupado;
    private boolean Tocado;
    private Barco barco;

    public Casilla() {
        this.Ocupado = false;
        this.Tocado = false;
        this.barco = null;
    }

    public boolean getOcupado() {
        return Ocupado;
    }

    public void setOcupado(boolean ocupado) {
        Ocupado = ocupado;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public void setTocado (boolean tocado) {
        Tocado = tocado;
    }

    public boolean getTocado() {
        return Tocado;
    }   
}
