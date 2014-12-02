package dc.control.enums;

public enum IpptEn {

	P("PRÓPRIO", "1"),

	T("TERCEIRO", "2");

	private String label;

	private String codigo;

	private IpptEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static IpptEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return P;
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