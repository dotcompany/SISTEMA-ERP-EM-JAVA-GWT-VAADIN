package dc.control.enums;

public enum TipoSpedEn {

	MR("Mercadoria p venda", "00"),

	MP("Matéria prima", "01"),

	EM("Embalagem", "02"),

	PP("Produto em processo", "03"),

	PA("Produto acabado", "04"),

	SP("Subproduto", "05"),

	PI("Produto intermediário", "06"),

	MC("Material de uso e consumo", "07");

	private String label;
	private String codigo;

	private TipoSpedEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoSpedEn getTipoSped(String codigo) {
		if (codigo.equals("00")) {
			return MR;
		}

		if (codigo.equals("01")) {
			return MP;
		}

		if (codigo.equals("02")) {
			return EM;
		}

		if (codigo.equals("03")) {
			return PP;
		}

		if (codigo.equals("04")) {
			return PA;
		}

		if (codigo.equals("05")) {
			return SP;
		}

		if (codigo.equals("06")) {
			return PI;
		}

		if (codigo.equals("07")) {
			return MC;
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