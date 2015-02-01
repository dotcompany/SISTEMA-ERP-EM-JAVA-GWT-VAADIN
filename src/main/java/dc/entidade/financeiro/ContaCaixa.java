package dc.entidade.financeiro;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "conta_caixa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContaCaixa extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_caixa_id_seq")
	@SequenceGenerator(name = "conta_caixa_id_seq", sequenceName = "conta_caixa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Código")
	@Column(name = "CODIGO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigo = "";

	@Field
	@Caption("Dígito")
	@Column(name = "DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String digito = "";

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao = "";
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	@Field
	@Caption("Tipo")
	@Column(name = "TIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipo = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "ID_AGENCIA_BANCO", nullable = true)
	private AgenciaBancoEntity agenciaBanco;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public ContaCaixa() {

	}

	public ContaCaixa(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = (codigo == null ? "".trim() : codigo.toUpperCase().trim());
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = (digito == null ? "".trim() : digito.toUpperCase().trim());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}
	
	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "".trim() : tipo.toUpperCase().trim());
	}

	public AgenciaBancoEntity getAgenciaBanco() {
		return agenciaBanco;
	}

	public void setAgenciaBanco(AgenciaBancoEntity agenciaBanco) {
		this.agenciaBanco = agenciaBanco;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}