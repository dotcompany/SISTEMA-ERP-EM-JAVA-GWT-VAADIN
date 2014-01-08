package dc.entidade.contratos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.Pessoa;

@Entity
@Table(name = "CONTRATO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Contrato extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Field
	@Caption(value = "Id")
	private Integer id;

	@Field
	@Caption(value = "Número")
	@Column(name = "NUMERO")
	private String numero;

	@Field
	@Caption(value = "Nome")
	@Column(name = "NOME")
	private String nome;

	@Field
	@Caption(value = "Descrição")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Field
	@Caption(value = "Data Cadastro")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;

	@Field
	@Caption(value = "Data Início Vigência")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INICIO_VIGENCIA")
	private Date dataInicioVigencia;

	@Field
	@Caption(value = "Data Fim Vigência")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FIM_VIGENCIA")
	private Date dataFimVigencia;

	@Field
	@Caption(value = "Dia Faturamento")
	@Column(name = "DIA_FATURAMENTO", columnDefinition = "bpchar")
	private String diaFaturamento;

	@Field
	@Caption(value = "Valor")
	@Column(name = "VALOR")
	private BigDecimal valor;

	@Column(name = "QUANTIDADE_PARCELAS")
	private Integer quantidadeParcelas;

	@Column(name = "INTERVALO_ENTRE_PARCELAS")
	private Integer intervaloEntreParcelas;

	@Caption(value = "Observação")
	private String observacao;

	@Caption(value = "Tipo Contrato")
	@JoinColumn(name = "ID_TIPO_CONTRATO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoContrato tipoContrato;

	@JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
	@ManyToOne
	private ContabilConta contabilConta;

	@JoinColumn(name = "ID_SOLICITACAO_SERVICO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContratoSolicitacaoServico contratoSolicitacaoServico;
	
	/**
	 * 
	 * Mapeamento de Pessoa
	 * @ Wesley Jr
	 * 
	 */
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Pessoa")
	private Pessoa pessoa;

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ContratoHistFaturamento> contratosHistoricosFaturamentos = new ArrayList<>();

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ContratoHistoricoReajuste> contratosHistoricosReajustes = new ArrayList<>();

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ContratoPrevFaturamento> contratosPrevisoesFaturamentos = new ArrayList<>();
	
	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaPagar> parcelasPagar = new ArrayList<>();

	@Transient
	private ContratoTemplate contratoTemplate;

	public Contrato() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public Date getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public String getDiaFaturamento() {
		return diaFaturamento;
	}

	public void setDiaFaturamento(String diaFaturamento) {
		this.diaFaturamento = diaFaturamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Integer getIntervaloEntreParcelas() {
		return intervaloEntreParcelas;
	}

	public void setIntervaloEntreParcelas(Integer intervaloEntreParcelas) {
		this.intervaloEntreParcelas = intervaloEntreParcelas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public ContabilConta getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilConta contabilConta) {
		this.contabilConta = contabilConta;
	}

	public ContratoSolicitacaoServico getContratoSolicitacaoServico() {
		return contratoSolicitacaoServico;
	}

	public void setContratoSolicitacaoServico(ContratoSolicitacaoServico contratoSolicitacaoServico) {
		this.contratoSolicitacaoServico = contratoSolicitacaoServico;
	}

	public void addParcelaPagar(ParcelaPagar parcela) {
		parcela.setContrato(this);
		this.parcelasPagar.add(parcela);
	}

	public void removeParcelaPagar(ParcelaPagar parcela) {
		parcela.setContrato(null);
		parcelasPagar.remove(parcela);

	}
	
	@Override
	public String toString() {
		return nome;
	}

	/**
	 * @return the contratoTemplate
	 */
	public ContratoTemplate getContratoTemplate() {
		return contratoTemplate;
	}

	/**
	 * @param contratoTemplate
	 *            the contratoTemplate to set
	 */
	public void setContratoTemplate(ContratoTemplate contratoTemplate) {
		this.contratoTemplate = contratoTemplate;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ContratoHistFaturamento addContratoHistFaturamento(ContratoHistFaturamento contratoHistFaturamento) {
		getContratosHistoricosFaturamentos().add(contratoHistFaturamento);
		contratoHistFaturamento.setContrato(this);

		return contratoHistFaturamento;
	}

	public ContratoHistFaturamento removeContratoHistFaturamento(ContratoHistFaturamento contratoHistFaturamento) {
		getContratosHistoricosFaturamentos().remove(contratoHistFaturamento);
		contratoHistFaturamento.setContrato(null);

		return contratoHistFaturamento;
	}

	public ContratoHistoricoReajuste addContratoHistoricoReajuste(ContratoHistoricoReajuste contratoHistReajustes) {
		getContratosHistoricosReajustes().add(contratoHistReajustes);
		contratoHistReajustes.setContrato(this);

		return contratoHistReajustes;
	}

	public ContratoHistoricoReajuste removeContratoHistoricoReajuste(ContratoHistoricoReajuste contratoHistReajustes) {
		getContratosHistoricosReajustes().remove(contratoHistReajustes);
		contratoHistReajustes.setContrato(null);

		return contratoHistReajustes;
	}

	public ContratoPrevFaturamento addContratoPrevFaturamento(ContratoPrevFaturamento contratoPrevFaturamentos) {
		getContratosPrevisoesFaturamentos().add(contratoPrevFaturamentos);
		contratoPrevFaturamentos.setContrato(this);

		return contratoPrevFaturamentos;
	}

	public ContratoPrevFaturamento removeContratoPrevFaturamento(ContratoPrevFaturamento contratoPrevFaturamento) {
		getContratosPrevisoesFaturamentos().remove(contratoPrevFaturamento);
		contratoPrevFaturamento.setContrato(null);

		return contratoPrevFaturamento;
	}

	public List<ContratoHistFaturamento> getContratosHistoricosFaturamentos() {
		return contratosHistoricosFaturamentos;
	}

	public void setContratosHistoricosFaturamentos(List<ContratoHistFaturamento> contratosHistoricosFaturamentos) {
		this.contratosHistoricosFaturamentos = contratosHistoricosFaturamentos;
	}

	public List<ContratoHistoricoReajuste> getContratosHistoricosReajustes() {
		return contratosHistoricosReajustes;
	}

	public void setContratosHistoricosReajustes(List<ContratoHistoricoReajuste> contratosHistoricosReajustes) {
		this.contratosHistoricosReajustes = contratosHistoricosReajustes;
	}

	public List<ContratoPrevFaturamento> getContratosPrevisoesFaturamentos() {
		return contratosPrevisoesFaturamentos;
	}

	public void setContratosPrevisoesFaturamentos(List<ContratoPrevFaturamento> contratosPrevisoesFaturamentos) {
		this.contratosPrevisoesFaturamentos = contratosPrevisoesFaturamentos;
	}
}
