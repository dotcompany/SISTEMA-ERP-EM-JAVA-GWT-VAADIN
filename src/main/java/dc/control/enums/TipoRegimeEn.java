package dc.control.enums;

public enum TipoRegimeEn {

	R("LUCRO REAL", "1"),

	P("SIMPLES PRESUMIDO", "2"),

	N("SIMPLES NACIONAL", "3");

	private String label;

	private String codigo;

	private TipoRegimeEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoRegimeEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return R;
		}

		if (codigo.equals("2")) {
			return P;
		}

		if (codigo.equals("3")) {
			return N;
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