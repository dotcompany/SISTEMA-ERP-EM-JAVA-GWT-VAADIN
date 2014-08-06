package dc.entidade.ordemservico;

import java.io.Serializable;
import java.util.Date;

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
public class InformacaoGeralEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String numeroOS = "";

	private Date dataEntrada;

	private Date dataEfetiv;

	private String numeroComanda = "";

	private String status = "";

	private String situacaoServico = "";

	/**
	 * CONSTRUTOR
	 */

	public InformacaoGeralEntity() {

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

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataEfetiv() {
		return dataEfetiv;
	}

	public void setDataEfetiv(Date dataEfetiv) {
		this.dataEfetiv = dataEfetiv;
	}

	public String getNumeroComanda() {
		return numeroComanda;
	}

	public void setNumeroComanda(String numeroComanda) {
		this.numeroComanda = numeroComanda;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSituacaoServico() {
		return situacaoServico;
	}

	public void setSituacaoServico(String situacaoServico) {
		this.situacaoServico = situacaoServico;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}