package dc.control.enums;

public enum CrtEn {

	N("SIMPLES NACIONAL", "1"),

	E("SIMPLES NACIONAL - EXCESSO DE SUBLIMITE RECEITA BRUTA", "2"),

	R("REGIME NORMAL", "3");

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