package dc.entidade.tributario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Empresa;

@Entity
@Table(name = "tribut_operacao_fiscal")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperacaoFiscal extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opf")
	@SequenceGenerator(name = "opf", sequenceName = "tribut_operacao_fiscal_id_seq", allocationSize = 1)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA", nullable = false)
	private Empresa empresa;

	@Field
	@Caption("CFOP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer cfop;

	@Field
	@Caption("Descrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@Field
	@Caption("Descrição na NF")
	@Column(name = "descricao_na_nf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoNaNF;

	@Field
	@Caption("Observações")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	@Override
	public String toString() {
		return descricao;
	}

}