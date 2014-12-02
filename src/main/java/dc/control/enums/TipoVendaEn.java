package dc.control.enums;

public enum TipoVendaEn {

	V("VENDA", "1"),

	C("COMPOSIÇÃO", "2"),

	P("PRODUÇÃO", "3"),

	I("INSUMO", "4"),

	U("USO PRÓPRIO", "5");

	private String label;

	private String codigo;

	private TipoVendaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoVendaEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return V;
		}

		if (codigo.equals("2")) {
			return C;
		}

		if (codigo.equals("3")) {
			return P;
		}

		if (codigo.equals("4")) {
			return I;
		}

		if (codigo.equals("5")) {
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