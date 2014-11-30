package dc.control.enums;

public enum ClasseEn {

	A("A", "1"),

	B("B", "2"),

	C("C", "3");

	private String label;
	private String codigo;

	private ClasseEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static ClasseEn getClasse(String codigo) {
		if (codigo.equals("1")) {
			return A;
		}

		if (codigo.equals("2")) {
			return B;
		}

		if (codigo.equals("3")) {
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