package dc.visao.financeiro.enums;

public enum TipoRegimeType {

	LUCRO_REAL("Lucro Real", "1"), LUCRO_PRESUMIDO("Lucro Presumido", "2"), SIMPLES_NACIONAL("Simples Nacional","3");

	private TipoRegimeType(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static TipoRegimeType getCrtType(String codigo) {
		for (TipoRegimeType e : TipoRegimeType.values()) {
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