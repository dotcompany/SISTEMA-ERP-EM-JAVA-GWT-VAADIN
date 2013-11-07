package dc.visao.financeiro.enums;

public enum SimNao {

	SIM("Sim", "S"), NAO("Não", "N");

	private SimNao(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static SimNao getSimNao(String codigo) {
		for (SimNao e : SimNao.values()) {
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