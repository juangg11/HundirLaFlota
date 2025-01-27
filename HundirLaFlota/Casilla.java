public class Casilla {
    private boolean Ocupado;
    private boolean Agua;
    private Barco barco;
    private boolean Tocado;
    private boolean Hundido;

    public Casilla() {
        this.Ocupado = false;
        this.Agua = false;
        this.barco = null;
        this.Tocado = false;
        this.Hundido = false;
    }
    public boolean getTocado() {
        return Tocado;
    }

    public void setTocado(boolean Tocado) {
        this.Tocado = Tocado;
    }

    public boolean getOcupado() {
        return Ocupado;
    }

    public void setOcupado(boolean Ocupado) {
        this.Ocupado = Ocupado;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public void setAgua(boolean Agua) {
        this.Agua = Agua;
    }

    public boolean getAgua() {
        return Agua;
    }   

    public boolean getHundido() {
        return Hundido;
    }

    public void setHundido(boolean Hundido) {
        this.Hundido = Hundido;
    }
    public void tocarCasilla(Casilla casilla[][], Barco barco) 
    {
        for(int i = 0; i < casilla.length; i++)
        {
            for(int j = 0; j < casilla[0].length; j++)
            {
                if(casilla[i][j].getBarco() == barco)
                {
                    casilla[i][j].Hundido = true;
                }
            }
        }
    }
}
