package dc.control.enums;

public enum CnhEn {

	A("A", "A"),

	B("B", "B"),

	C("C", "C"),

	D("D", "D"),

	E("E", "E");

	private String label;

	private String codigo;

	private CnhEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static CnhEn getEn(String codigo) {
		if (codigo.equals("A")) {
			return A;
		}

		if (codigo.equals("B")) {
			return B;
		}

		if (codigo.equals("C")) {
			return C;
		}

		if (codigo.equals("D")) {
			return D;
		}

		if (codigo.equals("E")) {
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