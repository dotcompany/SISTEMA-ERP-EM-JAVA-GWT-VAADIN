package dc.control.enums;

public enum VendaTipoVendaEn {

	O("DO ORÇAMENTO", "1"),

	V("VENDA DIRETA", "2");

	private String label;

	private String codigo;

	private VendaTipoVendaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static VendaTipoVendaEn getEn(String codigo) {
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