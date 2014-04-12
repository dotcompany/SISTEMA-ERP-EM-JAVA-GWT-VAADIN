package dc.entidade.tributario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.nfe.NfeCabecalhoEntity;

@Entity
@Table(name = "tribut_operacao_fiscal")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperacaoFiscal extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tribut_operacao_fiscal_id_seq")
	@SequenceGenerator(name = "tribut_operacao_fiscal_id_seq", sequenceName = "tribut_operacao_fiscal_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	// @ManyToOne
	// @JoinColumn(name = "ID_EMPRESA", nullable = false)
	// private Empresa empresa;

	@Field
	private Integer cfop;

	@Field
	@Caption("Descrição")
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Descrição na NF")
	@Column(name = "descricao_na_nf")
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoNaNF;

	@Field
	@Caption("Observações")
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * Módulo: NFE
	 */

	@OneToMany(mappedBy = "tributOperacaoFiscal", fetch = FetchType.LAZY)
	private List<NfeCabecalhoEntity> nfeCabecalhoList;

	/**
	 * 
	 */

	/**
	 * CONSTRUTOR
	 */

	public OperacaoFiscal() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// public Empresa getEmpresa() {
	// return empresa;
	// }
	//
	// public void setEmpresa(Empresa empresa) {
	// this.empresa = empresa;
	// }

	public String getNome() {
		return nome;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoNaNF() {
		return descricaoNaNF;
	}

	public void setDescricaoNaNF(String descricaoNaNF) {
		this.descricaoNaNF = descricaoNaNF;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * Módulo: NFE
	 */

	public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
		return nfeCabecalhoList;
	}

	public void setNfeCabecalhoList(List<NfeCabecalhoEntity> nfeCabecalhoList) {
		this.nfeCabecalhoList = nfeCabecalhoList;
	}

	/**
	 * 
	 */

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}