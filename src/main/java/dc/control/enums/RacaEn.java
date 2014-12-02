package dc.control.enums;

public enum RacaEn {

	B("Branco", "1"),

	N("Negro", "2"),

	P("Pardo", "3"),

	I("Indio", "4");

	private String label;
	private String codigo;

	private RacaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static RacaEn getTipoSped(String codigo) {
		if (codigo.equals("1")) {
			return B;
		}

		if (codigo.equals("2")) {
			return N;
		}

		if (codigo.equals("3")) {
			return P;
		}

		if (codigo.equals("4")) {
			return I;
		}

		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}

}