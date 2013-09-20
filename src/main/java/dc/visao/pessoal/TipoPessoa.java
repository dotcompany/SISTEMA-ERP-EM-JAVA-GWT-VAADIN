package dc.visao.pessoal;

public class TipoPessoa {
	String label;
	String codigo;
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

	public TipoPessoa(String label,String codigo){
		this.label = label;
		this.codigo = codigo;
	}

}
