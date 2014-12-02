package dc.control.enums;

public enum TipoSangueEn {

	APO("A+", "A+"),

	ANE("A-", "A-"),

	BPO("B+", "B+"),

	BNE("B-", "B-"),

	OPO("O+", "O+"),

	ONE("O-", "O-"),

	ABP("AB+", "AB+"),

	ABN("AB-", "AB-");

	private String label;

	private String codigo;

	private TipoSangueEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoSangueEn getEn(String codigo) {
		if (codigo.equals("A+")) {
			return APO;
		}

		if (codigo.equals("A-")) {
			return ANE;
		}

		if (codigo.equals("B+")) {
			return BPO;
		}

		if (codigo.equals("B-")) {
			return BNE;
		}

		if (codigo.equals("O+")) {
			return OPO;
		}

		if (codigo.equals("O-")) {
			return ONE;
		}

		if (codigo.equals("AB+")) {
			return ABP;
		}

		if (codigo.equals("AB-")) {
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