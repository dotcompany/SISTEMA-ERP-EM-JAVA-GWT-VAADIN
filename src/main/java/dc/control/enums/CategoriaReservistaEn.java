package dc.control.enums;

public enum CategoriaReservistaEn {

	U("1", "1"),

	D("2", "2"),

	T("3", "3");

	private String label;

	private String codigo;

	private CategoriaReservistaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static CategoriaReservistaEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return U;
		}

		if (codigo.equals("2")) {
			return D;
		}

		if (codigo.equals("3")) {
			return T;
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