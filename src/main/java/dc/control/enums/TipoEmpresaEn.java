package dc.control.enums;

public enum TipoEmpresaEn {

	M("MATRIZ", "1"),

	F("FILIAL", "2"),

	D("DEPÓSITO", "3");

	private String label;

	private String codigo;

	private TipoEmpresaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoEmpresaEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return M;
		}

		if (codigo.equals("2")) {
			return F;
		}

		if (codigo.equals("3")) {
			return D;
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