package dc.control.enums;

public enum CnhEn {

	A("A", "1"),

	B("B", "2"),

	C("C", "3"),

	D("D", "4"),

	E("E", "5");

	private String label;
	private String codigo;

	private CnhEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static CnhEn getTipoSped(String codigo) {
		if (codigo.equals("1")) {
			return A;
		}

		if (codigo.equals("2")) {
			return B;
		}

		if (codigo.equals("3")) {
			return C;
		}

		if (codigo.equals("4")) {
			return D;
		}

		if (codigo.equals("5")) {
			return E;
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