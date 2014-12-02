package dc.control.enums;

public enum CrtEn {

	N("Simples nacional", "1"),

	E("Simples nacional - excesso de sublimite receita bruta", "2"),

	R("Regime normal", "3");

	private String label;
	private String codigo;

	private CrtEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static CrtEn getTipoSped(String codigo) {
		if (codigo.equals("1")) {
			return N;
		}

		if (codigo.equals("2")) {
			return E;
		}

		if (codigo.equals("3")) {
			return R;
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