package dc.entidade.ordemservico;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

// @Entity
// @Table(name = "")
// @XmlRootElement
// @Indexed
// @Analyzer(impl = BrazilianAnalyzer.class)
public class LaudoTecnicoEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String observacaoLaudoTecnico = "";

	private String observacaoLaudoFerramentas = "";

	/**
	 * CONSTRUTOR
	 */

	public LaudoTecnicoEntity() {

	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacaoLaudoTecnico() {
		return observacaoLaudoTecnico;
	}

	public void setObservacaoLaudoTecnico(String observacaoLaudoTecnico) {
		this.observacaoLaudoTecnico = (observacaoLaudoTecnico == null ? ""
				: observacaoLaudoTecnico.toUpperCase());
	}

	public String getObservacaoLaudoFerramentas() {
		return observacaoLaudoFerramentas;
	}

	public void setObservacaoLaudoFerramentas(String observacaoLaudoFerramentas) {
		this.observacaoLaudoFerramentas = (observacaoLaudoFerramentas == null ? ""
				: observacaoLaudoFerramentas.toUpperCase());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}