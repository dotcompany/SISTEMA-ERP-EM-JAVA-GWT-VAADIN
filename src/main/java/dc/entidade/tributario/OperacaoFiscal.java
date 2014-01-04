package dc.entidade.tributario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "tribut_operacao_fiscal")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperacaoFiscal extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opf")
	@SequenceGenerator(name = "opf", sequenceName = "tribut_operacao_fiscal_id_seq", allocationSize = 1)
	@ComboCode
	@Field
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

//	@ManyToOne
//	@JoinColumn(name = "ID_EMPRESA", nullable = false)
//	private Empresa empresa;

	@Field
    private Integer cfop;

	@Field
	@Caption("Descrição")
	@Column(name="descricao")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}

	
	

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

	@Override
	public String toString() {
		return nome;
	}

}