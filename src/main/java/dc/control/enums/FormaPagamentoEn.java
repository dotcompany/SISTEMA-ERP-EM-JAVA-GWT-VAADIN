package dc.control.enums;

public enum FormaPagamentoEn {

	D("DINHEIRO", "1"),

	Q("CHEQUE", "2"),

	C("CONTA", "3");

	private String label;

	private String codigo;

	private FormaPagamentoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static FormaPagamentoEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return D;
		}

		if (codigo.equals("2")) {
			return Q;
		}

		if (codigo.equals("3")) {
			return C;
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