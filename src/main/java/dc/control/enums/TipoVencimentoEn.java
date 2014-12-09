package dc.control.enums;

public enum TipoVencimentoEn {

	M("MENSAL", "M"),

	D("DIÁRIO", "D");

	private String label;

	private String codigo;

	private TipoVencimentoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static TipoVencimentoEn getEn(String codigo) {
		if (codigo.equals("M")) {
			return M;
		}

		if (codigo.equals("D")) {
			return D;
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