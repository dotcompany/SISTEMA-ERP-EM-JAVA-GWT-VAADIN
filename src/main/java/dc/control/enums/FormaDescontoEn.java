package dc.control.enums;

public enum FormaDescontoEn {

	P("Produto", "1"),

	F("Fim do pedido", "2");

	private String label;
	private String codigo;

	private FormaDescontoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static FormaDescontoEn getValor(String codigo) {
		if (codigo.equals("1")) {
			return P;
		}

		if (codigo.equals("2")) {
			return F;
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