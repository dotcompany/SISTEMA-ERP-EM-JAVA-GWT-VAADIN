package dc.control.enums;

public enum TipoSemestre {

	P("PRIMEIRO SEMESTRE", "Primeiro Semestre"),

	S("SEGUNDO SEMESTRE", "Segundo Semestre");

	private String key;

	private String value;

	private TipoSemestre(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoSemestre getEn(String value) {
		if (value.equals("Primeiro Semestre")) {
			return P;
		}

		if (value.equals("Segundo Semestre")) {
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