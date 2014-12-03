package dc.control.enums;

public enum TipoSindicatoEn {

	P("PATRONAL", "P"),

	E("EMPREGADOS", "E");

	private String label;

	private String codigo;

	private TipoSindicatoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoSindicatoEn getEn(String codigo) {
		if (codigo.equals("P")) {
			return P;
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