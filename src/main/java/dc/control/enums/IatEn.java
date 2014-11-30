package dc.control.enums;

public enum IatEn {

	ARREDONDAMENTO("Arredondamento", "1"),

	TRUNCAMENTO("Truncamento", "2");

	private String label;
	private String codigo;

	private IatEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static IatEn getIat(String codigo) {
		if (codigo.equals("1")) {
			return ARREDONDAMENTO;
		}

		if (codigo.equals("2")) {
			return TRUNCAMENTO;
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