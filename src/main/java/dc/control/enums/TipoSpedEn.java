package dc.control.enums;

public enum TipoSpedEn {

	MERCADORIA_PRA_REVENDA("Mercadoria P/Revenda", "00"),

	MATERIA_PRIMA("Matéria Prima", "01"),

	EMBALAGEM("Embalagem", "02"),

	PRODUTO_EM_PROCESSO("Produto em Processo", "03"),

	PRODUTO_ACABADO("Produto Acabado", "04"),

	SUBPRODUTO("SubProduto", "05"),

	PRODUTO_INTERMEDIARIO("Produto Intermediário", "06"),

	MATERIAL_USO_CONSUMO("Material de Uso e Consumo", "07");

	private String label;
	private String codigo;

	private TipoSpedEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoSpedEn getTipoSped(String codigo) {
		if (codigo.equals("00")) {
			return MERCADORIA_PRA_REVENDA;
		}

		if (codigo.equals("01")) {
			return MATERIA_PRIMA;
		}

		if (codigo.equals("02")) {
			return EMBALAGEM;
		}

		if (codigo.equals("03")) {
			return PRODUTO_EM_PROCESSO;
		}

		if (codigo.equals("04")) {
			return PRODUTO_ACABADO;
		}

		if (codigo.equals("05")) {
			return SUBPRODUTO;
		}

		if (codigo.equals("06")) {
			return PRODUTO_INTERMEDIARIO;
		}

		if (codigo.equals("07")) {
			return MATERIAL_USO_CONSUMO;
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