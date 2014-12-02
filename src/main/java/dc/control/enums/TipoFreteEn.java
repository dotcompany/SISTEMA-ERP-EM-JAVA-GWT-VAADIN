package dc.control.enums;

public enum TipoFreteEn {

	E("EMITENTE", "1"),

	D("DESTINATÁRIO", "2"),

	S("SEM FRETE", "3"),

	T("TERCEIROS", "4");

	private String label;

	private String codigo;

	private TipoFreteEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoFreteEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return E;
		}

		if (codigo.equals("2")) {
			return D;
		}

		if (codigo.equals("3")) {
			return S;
		}

		if (codigo.equals("4")) {
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