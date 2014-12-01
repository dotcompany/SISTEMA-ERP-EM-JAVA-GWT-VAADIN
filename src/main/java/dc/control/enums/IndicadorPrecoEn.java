package dc.control.enums;

public enum IndicadorPrecoEn {

	T("Tabela", "1"),

	U("Último pedido", "2");

	private String label;
	private String codigo;

	private IndicadorPrecoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static IndicadorPrecoEn getValor(String codigo) {
		if (codigo.equals("1")) {
			return T;
		}

		if (codigo.equals("2")) {
			return U;
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