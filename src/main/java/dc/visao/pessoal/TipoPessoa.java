package dc.visao.pessoal;

public class TipoPessoa {

	String label;
	String codigo;

	public static final TipoPessoa PESSOA_FISICA = new TipoPessoa("Fisica", "1");
	public static final TipoPessoa PESSOA_JURIDICA = new TipoPessoa("Juridica", "2");

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoPessoa(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPessoa other = (TipoPessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	public static final TipoPessoa getTipoPessoa(String codigo) {
		if ("1".equals(codigo)) {
			return PESSOA_FISICA;
		} else if ("2".equals(codigo)) {
			return PESSOA_JURIDICA;
		} else {
			return null;
		}
	}
}