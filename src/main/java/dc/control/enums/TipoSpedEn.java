package dc.control.enums;

public enum TipoSpedEn {

	MR("MERCADORIA P VENDA", "00"),

	MP("MATÉRIA PRIMA", "01"),

	EM("EMBALAGEM", "02"),

	PP("PRODUTO EM PROCESSO", "03"),

	PA("PRODUTO ACABADO", "04"),

	SP("SUBPRODUTO", "05"),

	PI("PRODUTO INTERMEDIÁRIO", "06"),

	MC("MATERIAL DE USO E CONSUMO", "07");

	private String label;

	private String codigo;

	private TipoSpedEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoSpedEn getEn(String codigo) {
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