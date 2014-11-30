package dc.control.enums;

public enum TipoVendaEn {

	VENDA("Venda", "1"),

	COMPOSICAO("Composição", "2"),

	PRODUCAO("Produção", "3"),

	INSUMO("Insumo", "4"),

	USO_PROPRIO("Uso próprio", "5");

	private String label;

	private String codigo;

	private TipoVendaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoVendaEn getTipoVenda(String codigo) {
		if (codigo.equals("1")) {
			return VENDA;
		}

		if (codigo.equals("2")) {
			return COMPOSICAO;
		}

		if (codigo.equals("3")) {
			return PRODUCAO;
		}

		if (codigo.equals("4")) {
			return INSUMO;
		}

		if (codigo.equals("5")) {
			return USO_PROPRIO;
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