package dc.control.enums;

public enum IpptEn {

	PROPRIO("Próprio", "1"),

	TERCEIRO("Terceiro", "2");

	private String label;
	private String codigo;

	private IpptEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static IpptEn getiPPT(String codigo) {
		if (codigo.equals("1")) {
			return PROPRIO;
		}

		if (codigo.equals("2")) {
			return TERCEIRO;
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