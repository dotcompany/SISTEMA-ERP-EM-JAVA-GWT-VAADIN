package dc.control.enums;

public enum CategoriaPessoaEn {

	C("CLIENTE", "1"),

	F("FORNECEDOR", "2"),

	O("COLABORADOR", "3"),

	T("TRANSPORTADORA", "4");

	private String label;

	private String codigo;

	private CategoriaPessoaEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static CategoriaPessoaEn getEn(String codigo) {
		if (codigo.equals("1")) {
			return C;
		}

		if (codigo.equals("2")) {
			return F;
		}

		if (codigo.equals("3")) {
			return O;
		}

		if (codigo.equals("4")) {
			return T;
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