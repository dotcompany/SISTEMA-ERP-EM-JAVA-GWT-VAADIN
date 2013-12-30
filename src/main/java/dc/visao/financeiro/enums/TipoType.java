package dc.visao.financeiro.enums;

public enum TipoType {

	MATRIZ("Matriz", "1"), FILIAL("Filial", "2"), DEPOSITO("Depósito","3");

	private TipoType(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static TipoType getCrtType(String codigo) {
		for (TipoType e : TipoType.values()) {
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