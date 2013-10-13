package dc.entidade.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Cnae;

@Entity
@Table(name = "empresa_cnae")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class EmpresaCnae extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epc")
	@SequenceGenerator(name = "epc", sequenceName = "empresa_cnae_id_seq", allocationSize = 1)
	private Integer id;
	
	@Caption("Principal")
	String principal;//0-Não 1-Sim
		
	@Column(name="ramo_atividade")
	@Caption("Ramo de Atividade")
	String ramoAtividade;
	
	@Column(name="objeto_social")
	@Caption("Objeto Social")
	String objetoSocial;
	
//	@ManyToOne
//	@JoinColumn(name = "ID_EMPRESA", nullable = false)
//	private Empresa empresa;
	
	@Caption("CNAE")
	@ManyToOne
	@JoinColumn(name = "ID_CNAE", nullable = false)
	private Cnae cnae;
	
	@Transient
	@Caption("Principal")
	private String principalStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getObjetoSocial() {
		return objetoSocial;
	}

	public void setObjetoSocial(String objetoSocial) {
		this.objetoSocial = objetoSocial;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}

	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public String getPrincipalStr() {
		principalStr = principal.equals("0") ? "Não" : "Sim";
		return principalStr;
	}

	public void setPrincipalStr(String principalStr) {
		this.principalStr = principalStr;
	}

	@Override
	public String toString() {
		return cnae.getNome();
	}
	
	
	
	
	
	
	
}
