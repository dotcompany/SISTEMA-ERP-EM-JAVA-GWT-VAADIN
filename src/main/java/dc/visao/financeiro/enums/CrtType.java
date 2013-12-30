package dc.visao.financeiro.enums;

public enum CrtType {

	SIMPLES_NACIONAL("Simples Nacional", "1"), SIMPLES_EXCESSO("Simples Excesso", "2"), REGIME_NORMAL("Regime Nacional","3");

	private CrtType(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static CrtType getCrtType(String codigo) {
		for (CrtType e : CrtType.values()) {
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