package dc.control.enums;

public enum SexoEn {

	F("FEMININO", "F"),

	M("MASCULINO", "M");

	private String label;

	private String codigo;

	private SexoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static SexoEn getEn(String codigo) {
		if (codigo.equals("F")) {
			return F;
		}

		if (codigo.equals("M")) {
			return M;
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