package dc.control.enums;

public enum IatEn {

	A("ARREDONDAMENTO", "1"),

	T("TRUNCAMENTO", "2");

	private String label;

	private String codigo;

	private IatEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static IatEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return A;
		}

		if (codigo.equals("2")) {
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