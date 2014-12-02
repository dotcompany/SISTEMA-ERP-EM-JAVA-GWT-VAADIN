package dc.control.enums;

public enum TipoSangueEn {

	APO("A+", "1"),

	ANE("A-", "2"),

	BPO("B+", "3"),

	BNE("B-", "4"),

	OPO("O+", "5"),

	ONE("O-", "6"),

	ABP("AB+", "7"),

	ABN("AB-", "8");

	private TipoSangueEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static TipoSangueEn getTipoSangue(String codigo) {
		if (codigo.equals("1")) {
			return APO;
		}

		if (codigo.equals("2")) {
			return ANE;
		}

		if (codigo.equals("3")) {
			return BPO;
		}

		if (codigo.equals("4")) {
			return BNE;
		}

		if (codigo.equals("5")) {
			return OPO;
		}

		if (codigo.equals("6")) {
			return ONE;
		}

		if (codigo.equals("7")) {
			return ABP;
		}

		if (codigo.equals("8")) {
			return ABN;
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