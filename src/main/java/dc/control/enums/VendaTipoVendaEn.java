package dc.control.enums;

public enum VendaTipoVendaEn {

	O("Do Orçamento", "1"),

	V("Venda Direta", "2");

	private VendaTipoVendaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static VendaTipoVendaEn getTipoVenda(String codigo) {
		if (codigo.equals("0")) {
			return O;
		}

		if (codigo.equals("1")) {
			return V;
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