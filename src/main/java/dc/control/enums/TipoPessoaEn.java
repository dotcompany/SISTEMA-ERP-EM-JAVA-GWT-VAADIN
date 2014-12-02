package dc.control.enums;

public enum TipoPessoaEn {

	F("FÍSICA", "F"),

	J("JURÍDICA", "J");

	private String label;

	private String codigo;

	private TipoPessoaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoPessoaEn getEn(String codigo) {
		if (codigo.equals("F")) {
			return F;
		}

		if (codigo.equals("J")) {
			return J;
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