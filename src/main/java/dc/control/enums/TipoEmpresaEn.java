package dc.control.enums;

public enum TipoEmpresaEn {

	M("MATRIZ", "M"),

	F("FILIAL", "F"),

	D("DEPÓSITO", "D");

	private String label;

	private String codigo;

	private TipoEmpresaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoEmpresaEn getEn(String codigo) {
		if (codigo.equals("M")) {
			return M;
		}

		if (codigo.equals("F")) {
			return F;
		}

		if (codigo.equals("D")) {
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