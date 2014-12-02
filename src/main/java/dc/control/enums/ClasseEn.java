package dc.control.enums;

public enum ClasseEn {

	A("A", "A"),

	B("B", "B"),

	C("C", "C");

	private String label;

	private String codigo;

	private ClasseEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static ClasseEn getEn(String codigo) {
		if (codigo.equals("A")) {
			return A;
		}

		if (codigo.equals("B")) {
			return B;
		}

		if (codigo.equals("C")) {
			return C;
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