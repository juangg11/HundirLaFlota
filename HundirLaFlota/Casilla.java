public class Casilla {
	private boolean ocupado;
	private boolean agua;
	private Barco barco;
	private boolean tocado;
	private boolean hundido;

	public Casilla() {
		this.ocupado = false;
		this.agua = false;
		this.barco = null;
		this.tocado = false;
		this.hundido = false;
	}

	public boolean getTocado() {
		return tocado;
	}

	public void setTocado(boolean Tocado) {
		this.tocado = Tocado;
	}

	public boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean Ocupado) {
		this.ocupado = Ocupado;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public void setAgua(boolean Agua) {
		this.agua = Agua;
	}

	public boolean getAgua() {
		return agua;
	}

	public boolean getHundido() {
		return hundido;
	}

	public void setHundido(boolean Hundido) {
		this.hundido = Hundido;
	}

	public void tocarCasilla(Casilla casilla[][], Barco barco) {
		
		for (int i = 0; i < casilla.length; i++) {
			
			for (int j = 0; j < casilla[0].length; j++) {
				
				if (casilla[i][j].getBarco() == barco) {
					casilla[i][j].hundido = true;
				}
			}
		}
	}
}
