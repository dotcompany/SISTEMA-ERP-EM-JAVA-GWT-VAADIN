package dc.control.enums;

public enum TipoSemestre {
	
	P("PRIMEIRO SEMESTRE", "P"),

	S("SEGUNDO SEMESTRE", "S");

	private String key;

	private String value;

	private TipoSemestre(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoSemestre getSemestre(String value) {
		if (value.equals("P")) {
			return P;
		}

		if (value.equals("S")) {
			return S;
		}

		return null;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
