package dc.control.enums;

public enum TipoEmpresaEn {

	MATRIZ("MATRIZ", "1"),

	FILIAL("FILIAL", "2"),

	DEPOSITO("DEPÓSITO", "3");

	private String label;

	private String codigo;

	private TipoEmpresaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoEmpresaEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return MATRIZ;
		}

		if (codigo.equals("2")) {
			return FILIAL;
		}

		if (codigo.equals("3")) {
			return DEPOSITO;
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