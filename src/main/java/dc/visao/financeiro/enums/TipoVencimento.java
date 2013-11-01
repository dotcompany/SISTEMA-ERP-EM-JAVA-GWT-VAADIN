package dc.visao.financeiro.enums;

public enum TipoVencimento {

	MENSAL("Mensal", "M"), DIARIO("Diário", "D");

	private TipoVencimento(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static TipoVencimento getTipoVencimento(String codigo) {
		for (TipoVencimento e : TipoVencimento.values()) {
			if (e.getCodigo().equalsIgnoreCase(codigo)) {
				return e;
			}
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
