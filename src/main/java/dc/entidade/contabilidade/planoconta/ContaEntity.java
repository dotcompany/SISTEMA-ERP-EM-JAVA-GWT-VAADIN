package dc.entidade.contabilidade.planoconta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeDetEntity;
import dc.entidade.contabilidade.lancamento.LancamentoDetalheEntity;
import dc.entidade.contabilidade.lancamento.LancamentoOrcadoEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "contabil_conta")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContaEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_conta_id_seq")
	@SequenceGenerator(name = "contabil_conta_id_seq", sequenceName = "contabil_conta_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "classificacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Classificação")
	private String classificacao = "";

	@Field
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Tipo")
	private String tipo = "";

	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao = "";

	@Field
	@Column(name = "data_inclusao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Data da inclusão")
	private Date dataInclusao;

	@Field
	@Column(name = "situacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Situação")
	private String situacao = "";

	@Field
	@Column(name = "natureza")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Natureza")
	private String natureza = "";

	@Field
	@Column(name = "patrimonio_resultado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Patrimônio resultado")
	private String patrimonioResultado = "";

	@Field
	@Column(name = "livro_caixa")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Livro caixa")
	private String livroCaixa = "";

	@Field
	@Column(name = "dfc")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "DFC")
	private String dfc = "";

	@Field
	@Column(name = "ordem")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Ordem")
	private String ordem = "";

	@Field
	@Column(name = "codigo_reduzido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Código reduzido")
	private String codigoReduzido = "";

	@Field
	@Column(name = "codigo_efd")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Código EFD")
	private String codigoEfd = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_contabil_conta integer,

	@ManyToOne
	@JoinColumn(name = "id_contabil_conta", nullable = false)
	@Caption("Conta")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ContaEntity conta;

	// id_plano_conta integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_plano_conta", nullable = false)
	@Caption("Plano de conta")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private PlanoContaEntity planoConta;

	// id_plano_conta_ref_sped integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_plano_conta_ref_sped", nullable = false)
	@Caption("Plano de conta ref sped")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private PlanoContaRefSpedEntity planoContaRefSped;

	// id_empresa integer,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
	private List<ContaEntity> contaList;

	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
	private List<EncerramentoExeDetEntity> encerramentoExeDetList;

	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
	private List<LancamentoDetalheEntity> lancamentoDetalheList;

	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
	private List<LancamentoOrcadoEntity> lancamentoOrcadoList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getDescricao();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public ContaEntity() {
		// TODO Auto-generated constructor stub
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

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = (classificacao == null ? "" : classificacao
				.toUpperCase());
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = (situacao == null ? "" : situacao.toUpperCase());
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = (natureza == null ? "" : natureza.toUpperCase());
	}

	public String getPatrimonioResultado() {
		return patrimonioResultado;
	}

	public void setPatrimonioResultado(String patrimonioResultado) {
		this.patrimonioResultado = (patrimonioResultado == null ? ""
				: patrimonioResultado.toUpperCase());
	}

	public String getLivroCaixa() {
		return livroCaixa;
	}

	public void setLivroCaixa(String livroCaixa) {
		this.livroCaixa = (livroCaixa == null ? "" : livroCaixa.toUpperCase());
	}

	public String getDfc() {
		return dfc;
	}

	public void setDfc(String dfc) {
		this.dfc = (dfc == null ? "" : dfc.toUpperCase());
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = (ordem == null ? "" : ordem.toUpperCase());
	}

	public String getCodigoReduzido() {
		return codigoReduzido;
	}

	public void setCodigoReduzido(String codigoReduzido) {
		this.codigoReduzido = (codigoReduzido == null ? "" : codigoReduzido
				.toUpperCase());
	}

	public String getCodigoEfd() {
		return codigoEfd;
	}

	public void setCodigoEfd(String codigoEfd) {
		this.codigoEfd = (codigoEfd == null ? "" : codigoEfd.toUpperCase());
	}

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

	public PlanoContaEntity getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoContaEntity planoConta) {
		this.planoConta = planoConta;
	}

	public PlanoContaRefSpedEntity getPlanoContaRefSped() {
		return planoContaRefSped;
	}

	public void setPlanoContaRefSped(PlanoContaRefSpedEntity planoContaRefSped) {
		this.planoContaRefSped = planoContaRefSped;
	}

	public List<ContaEntity> getContaList() {
		return contaList;
	}

	public void setContaList(List<ContaEntity> contaList) {
		this.contaList = contaList;
	}

	public List<EncerramentoExeDetEntity> getEncerramentoExeDetList() {
		return encerramentoExeDetList;
	}

	public void setEncerramentoExeDetList(
			List<EncerramentoExeDetEntity> encerramentoExeDetList) {
		this.encerramentoExeDetList = encerramentoExeDetList;
	}

	public List<LancamentoDetalheEntity> getLancamentoDetalheList() {
		return lancamentoDetalheList;
	}

	public void setLancamentoDetalheList(
			List<LancamentoDetalheEntity> lancamentoDetalheList) {
		this.lancamentoDetalheList = lancamentoDetalheList;
	}

	public List<LancamentoOrcadoEntity> getLancamentoOrcadoList() {
		return lancamentoOrcadoList;
	}

	public void setLancamentoOrcadoList(
			List<LancamentoOrcadoEntity> lancamentoOrcadoList) {
		this.lancamentoOrcadoList = lancamentoOrcadoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}