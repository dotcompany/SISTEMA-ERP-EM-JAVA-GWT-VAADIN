package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.comercial.Venda;
import dc.entidade.contabilidade.demonstrativo.DreVinculoEntity;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoDetEntity;
import dc.entidade.contratos.ViewContratoDadosContratante;
import dc.entidade.financeiro.Adiantamento;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.Fornecedor;
import dc.entidade.pessoal.Cliente;
import dc.entidade.tributario.OperacaoFiscal;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "nfe_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeCabecalhoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_cabecalho_id_seq")
	@SequenceGenerator(name = "nfe_cabecalho_id_seq", sequenceName = "nfe_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Column(name = "codigo_numerico")
	@Caption("Código numérico")
	private String codigoNumerico;

	@Field
	@Column(name = "natureza_operacao")
	@Caption("Natureza da operação")
	private String naturezaOperacao;

	@Field
	@Column(name = "indicador_forma_pagamento")
	@Caption("")
	private String indicadorFormaPagamento;

	@Field
	@Column(name = "codigo_modelo")
	@Caption("")
	private String codigoModelo;

	@Field
	@Column(name = "serie")
	@Caption("")
	private String serie;

	@Field
	@Column(name = "numero")
	@Caption("")
	private String numero;

	@Field
	@Column(name = "data_emissao")
	@Caption("")
	private Date dataEmissao;

	@Field
	@Column(name = "data_entrada_saida")
	@Caption("")
	private Date dataEntradaSaida;

	@Field
	@Column(name = "hora_entrada_saida")
	@Caption("")
	private String horaEntradaSaida;

	@Field
	@Column(name = "tipo_operacao")
	@Caption("")
	private String tipoOperacao;

	@Field
	@Column(name = "codigo_municipio")
	@Caption("")
	private Integer codigoMunicipio;

	@Field
	@Column(name = "formato_impressao_danfe")
	@Caption("")
	private String formatoImpressaoDanfe;

	@Field
	@Column(name = "tipo_emissao")
	@Caption("")
	private String tipoEmissao;

	@Field
	@Column(name = "chave_acesso")
	@Caption("")
	private String chaveAcesso;

	@Field
	@Column(name = "digito_chave_acesso")
	@Caption("")
	private String digitoChaveAcesso;

	@Field
	@Column(name = "ambiente")
	@Caption("")
	private String ambiente;

	@Field
	@Column(name = "finalidade_emissao")
	@Caption("")
	private String finalidadeEmissao;

	@Field
	@Column(name = "processo_emissao")
	@Caption("")
	private String processoEmissao;

	@Field
	@Column(name = "versao_processo_emissao")
	@Caption("")
	private String versaoProcessoEmissao;

	@Field
	@Column(name = "data_entrada_contingencia")
	@Caption("")
	private Date dataEntradaContingencia;

	@Field
	@Column(name = "justificativa_contingencia")
	@Caption("")
	private String justificativaContingencia;

	@Field
	@Column(name = "base_calculo_icms")
	@Caption("")
	private BigDecimal baseCalculoIcms;

	@Field
	@Column(name = "valor_icms")
	@Caption("")
	private BigDecimal valorIcms;

	@Field
	@Column(name = "base_calculo_icms_st")
	@Caption("")
	private BigDecimal baseCalculoIcmsSt;

	@Field
	@Column(name = "valor_icms_st")
	@Caption("")
	private BigDecimal valorIcmsSt;

	@Field
	@Column(name = "valor_total_produtos")
	@Caption("")
	private BigDecimal valorTotalProdutos;

	@Field
	@Column(name = "valor_frete")
	@Caption("")
	private BigDecimal valorFrete;

	@Field
	@Column(name = "valor_seguro")
	@Caption("")
	private BigDecimal valorSeguro;

	@Field
	@Column(name = "valor_desconto")
	@Caption("")
	private BigDecimal valorDesconto;

	@Field
	@Column(name = "valor_imposto_importacao")
	@Caption("")
	private BigDecimal valorImpostoImportacao;

	@Field
	@Column(name = "valor_ipi")
	@Caption("")
	private BigDecimal valorIpi;

	@Field
	@Column(name = "valor_pis")
	@Caption("")
	private BigDecimal valorPis;

	@Field
	@Column(name = "valor_cofins")
	@Caption("")
	private BigDecimal valorCofins;

	@Field
	@Column(name = "valor_despesas_acessorias")
	@Caption("")
	private BigDecimal valorDespesasAcessorias;

	@Field
	@Column(name = "valor_total")
	@Caption("")
	private BigDecimal valorTotal;

	@Field
	@Column(name = "valor_servicos")
	@Caption("")
	private BigDecimal valorServicos;

	@Field
	@Column(name = "base_calculo_issqn")
	@Caption("")
	private BigDecimal baseCalculoIssqn;

	@Field
	@Column(name = "valor_issqn")
	@Caption("")
	private BigDecimal valorIssqn;

	@Field
	@Column(name = "valor_pis_issqn")
	@Caption("")
	private BigDecimal valorPisIssqn;

	@Field
	@Column(name = "valor_cofins_issqn")
	@Caption("")
	private BigDecimal valorCofinsIssqn;

	@Field
	@Column(name = "valor_retido_pis")
	@Caption("")
	private BigDecimal valorRetidoPis;

	@Field
	@Column(name = "valor_retido_cofins")
	@Caption("")
	private BigDecimal valorRetidoCofins;

	@Field
	@Column(name = "valor_retido_csll")
	@Caption("")
	private BigDecimal valorRetidoCsll;

	@Field
	@Column(name = "base_calculo_irrf")
	@Caption("")
	private BigDecimal baseCalculoIrrf;

	@Field
	@Column(name = "valor_retido_irrf")
	@Caption("")
	private BigDecimal valorRetidoIrrf;

	@Field
	@Column(name = "base_calculo_previdencia")
	@Caption("")
	private BigDecimal baseCalculoPrevidencia;

	@Field
	@Column(name = "valor_retido_previdencia")
	@Caption("")
	private BigDecimal valorRetidoPrevidencia;

	@Field
	@Column(name = "comex_uf_embarque")
	@Caption("")
	private String comexUfEmbarque;

	@Field
	@Column(name = "comex_local_embarque")
	@Caption("")
	private String comexLocalEmbarque;

	@Field
	@Column(name = "compra_nota_empenho")
	@Caption("")
	private String compraNotaEmpenho;

	@Field
	@Column(name = "compra_pedido")
	@Caption("")
	private String compraPedido;

	@Field
	@Column(name = "compra_contrato")
	@Caption("")
	private String compraContrato;

	@Field
	@Column(name = "informacoes_add_fisco")
	@Caption("Informações add fisco")
	private String informacoesAddFisco;

	@Field
	@Column(name = "informacoes_add_contribuinte")
	@Caption("Informações add contribuinte")
	private String informacoesAddContribuinte;

	@Field
	@Column(name = "status_nota")
	@Caption("Status da nota")
	private String statusNota;

	@Field
	@Column(name = "uf_emitente")
	@Caption("UF emitente")
	private Integer ufEmitente;
	
	@ManyToOne
	@JoinColumn(name = "id_adiantamento", nullable = false)
	@Caption("Adiantamento")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Adiantamento adiantamento;
	
	@ManyToOne
	@JoinColumn(name = "id_view_contrato_dados_contratante", nullable = false)
	@Caption("View Contrato Dados Contratante")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ViewContratoDadosContratante viewContratoDadosContratante;
	
	@Field
	@Column(name = "contato_email")
	@Caption("")
	private String contatoEmail;

	/**
	 * REFERENCIA - FK
	 */

	// id_tribut_operacao_fiscal integer,

	@ManyToOne
	@JoinColumn(name = "id_tribut_operacao_fiscal", nullable = false)
	@Caption("Tributário - Operação fiscal")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private OperacaoFiscal tributOperacaoFiscal;

	// id_venda_cabecalho integer,

	@ManyToOne
	@JoinColumn(name = "id_venda_cabecalho", nullable = false)
	@Caption("Venda - Cabeçalho")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Venda vendaCabecalho;

	// id_empresa integer,

	// id_fornecedor integer,

	@ManyToOne
	@JoinColumn(name = "id_fornecedor", nullable = false)
	@Caption("Fornecedor")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Fornecedor fornecedor;

	// id_cliente integer,

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	@Caption("Cliente")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Cliente cliente;
	
	//@ManyToOne
	//@JoinColumn(name = "id_contabil_lanca_programado_det", nullable = false)
	//@Caption("Contabil - Lancamento Programado Det")
	//@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	//private LancamentoProgramadoDetEntity lancamentoProgramadoDetEntity;
	
	@ManyToOne
	@JoinColumn(name = "id_contabil_dre_vinculo", nullable = false)
	@Caption("Contabil - Dre Vinculo")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private DreVinculoEntity dreVinculoEntity;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "nfeCabecalho", fetch = FetchType.LAZY)
	private List<NfeDestinatarioEntity> nfeDestinatarioList;

	/**
	 * CONSTRUTOR
	 */

	public NfeCabecalhoEntity() {
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

	public String getCodigoNumerico() {
		return codigoNumerico;
	}

	public void setCodigoNumerico(String codigoNumerico) {
		this.codigoNumerico = codigoNumerico;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public String getIndicadorFormaPagamento() {
		return indicadorFormaPagamento;
	}

	public void setIndicadorFormaPagamento(String indicadorFormaPagamento) {
		this.indicadorFormaPagamento = indicadorFormaPagamento;
	}

	public String getCodigoModelo() {
		return codigoModelo;
	}

	public void setCodigoModelo(String codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEntradaSaida() {
		return dataEntradaSaida;
	}

	public void setDataEntradaSaida(Date dataEntradaSaida) {
		this.dataEntradaSaida = dataEntradaSaida;
	}

	public String getHoraEntradaSaida() {
		return horaEntradaSaida;
	}

	public void setHoraEntradaSaida(String horaEntradaSaida) {
		this.horaEntradaSaida = horaEntradaSaida;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getFormatoImpressaoDanfe() {
		return formatoImpressaoDanfe;
	}

	public void setFormatoImpressaoDanfe(String formatoImpressaoDanfe) {
		this.formatoImpressaoDanfe = formatoImpressaoDanfe;
	}

	public String getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(String tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getDigitoChaveAcesso() {
		return digitoChaveAcesso;
	}

	public void setDigitoChaveAcesso(String digitoChaveAcesso) {
		this.digitoChaveAcesso = digitoChaveAcesso;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getFinalidadeEmissao() {
		return finalidadeEmissao;
	}

	public void setFinalidadeEmissao(String finalidadeEmissao) {
		this.finalidadeEmissao = finalidadeEmissao;
	}

	public String getProcessoEmissao() {
		return processoEmissao;
	}

	public void setProcessoEmissao(String processoEmissao) {
		this.processoEmissao = processoEmissao;
	}

	public String getVersaoProcessoEmissao() {
		return versaoProcessoEmissao;
	}

	public void setVersaoProcessoEmissao(String versaoProcessoEmissao) {
		this.versaoProcessoEmissao = versaoProcessoEmissao;
	}

	public Date getDataEntradaContingencia() {
		return dataEntradaContingencia;
	}

	public void setDataEntradaContingencia(Date dataEntradaContingencia) {
		this.dataEntradaContingencia = dataEntradaContingencia;
	}

	public String getJustificativaContingencia() {
		return justificativaContingencia;
	}

	public void setJustificativaContingencia(String justificativaContingencia) {
		this.justificativaContingencia = justificativaContingencia;
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public BigDecimal getBaseCalculoIcmsSt() {
		return baseCalculoIcmsSt;
	}

	public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
		this.baseCalculoIcmsSt = baseCalculoIcmsSt;
	}

	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}

	public BigDecimal getValorTotalProdutos() {
		return valorTotalProdutos;
	}

	public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
		this.valorTotalProdutos = valorTotalProdutos;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorImpostoImportacao() {
		return valorImpostoImportacao;
	}

	public void setValorImpostoImportacao(BigDecimal valorImpostoImportacao) {
		this.valorImpostoImportacao = valorImpostoImportacao;
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public BigDecimal getValorPis() {
		return valorPis;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = valorPis;
	}

	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}

	public BigDecimal getValorDespesasAcessorias() {
		return valorDespesasAcessorias;
	}

	public void setValorDespesasAcessorias(BigDecimal valorDespesasAcessorias) {
		this.valorDespesasAcessorias = valorDespesasAcessorias;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorServicos() {
		return valorServicos;
	}

	public void setValorServicos(BigDecimal valorServicos) {
		this.valorServicos = valorServicos;
	}

	public BigDecimal getBaseCalculoIssqn() {
		return baseCalculoIssqn;
	}

	public void setBaseCalculoIssqn(BigDecimal baseCalculoIssqn) {
		this.baseCalculoIssqn = baseCalculoIssqn;
	}

	public BigDecimal getValorIssqn() {
		return valorIssqn;
	}

	public void setValorIssqn(BigDecimal valorIssqn) {
		this.valorIssqn = valorIssqn;
	}

	public BigDecimal getValorPisIssqn() {
		return valorPisIssqn;
	}

	public void setValorPisIssqn(BigDecimal valorPisIssqn) {
		this.valorPisIssqn = valorPisIssqn;
	}

	public BigDecimal getValorCofinsIssqn() {
		return valorCofinsIssqn;
	}

	public void setValorCofinsIssqn(BigDecimal valorCofinsIssqn) {
		this.valorCofinsIssqn = valorCofinsIssqn;
	}

	public BigDecimal getValorRetidoPis() {
		return valorRetidoPis;
	}

	public void setValorRetidoPis(BigDecimal valorRetidoPis) {
		this.valorRetidoPis = valorRetidoPis;
	}

	public BigDecimal getValorRetidoCofins() {
		return valorRetidoCofins;
	}

	public void setValorRetidoCofins(BigDecimal valorRetidoCofins) {
		this.valorRetidoCofins = valorRetidoCofins;
	}

	public BigDecimal getValorRetidoCsll() {
		return valorRetidoCsll;
	}

	public void setValorRetidoCsll(BigDecimal valorRetidoCsll) {
		this.valorRetidoCsll = valorRetidoCsll;
	}

	public BigDecimal getBaseCalculoIrrf() {
		return baseCalculoIrrf;
	}

	public void setBaseCalculoIrrf(BigDecimal baseCalculoIrrf) {
		this.baseCalculoIrrf = baseCalculoIrrf;
	}

	public BigDecimal getValorRetidoIrrf() {
		return valorRetidoIrrf;
	}

	public void setValorRetidoIrrf(BigDecimal valorRetidoIrrf) {
		this.valorRetidoIrrf = valorRetidoIrrf;
	}

	public BigDecimal getBaseCalculoPrevidencia() {
		return baseCalculoPrevidencia;
	}

	public void setBaseCalculoPrevidencia(BigDecimal baseCalculoPrevidencia) {
		this.baseCalculoPrevidencia = baseCalculoPrevidencia;
	}

	public BigDecimal getValorRetidoPrevidencia() {
		return valorRetidoPrevidencia;
	}

	public void setValorRetidoPrevidencia(BigDecimal valorRetidoPrevidencia) {
		this.valorRetidoPrevidencia = valorRetidoPrevidencia;
	}

	public String getComexUfEmbarque() {
		return comexUfEmbarque;
	}

	public void setComexUfEmbarque(String comexUfEmbarque) {
		this.comexUfEmbarque = comexUfEmbarque;
	}

	public String getComexLocalEmbarque() {
		return comexLocalEmbarque;
	}

	public void setComexLocalEmbarque(String comexLocalEmbarque) {
		this.comexLocalEmbarque = comexLocalEmbarque;
	}

	public String getCompraNotaEmpenho() {
		return compraNotaEmpenho;
	}

	public void setCompraNotaEmpenho(String compraNotaEmpenho) {
		this.compraNotaEmpenho = compraNotaEmpenho;
	}

	public String getCompraPedido() {
		return compraPedido;
	}

	public void setCompraPedido(String compraPedido) {
		this.compraPedido = compraPedido;
	}

	public String getCompraContrato() {
		return compraContrato;
	}

	public void setCompraContrato(String compraContrato) {
		this.compraContrato = compraContrato;
	}

	public String getInformacoesAddFisco() {
		return informacoesAddFisco;
	}

	public void setInformacoesAddFisco(String informacoesAddFisco) {
		this.informacoesAddFisco = informacoesAddFisco;
	}

	public String getInformacoesAddContribuinte() {
		return informacoesAddContribuinte;
	}

	public void setInformacoesAddContribuinte(String informacoesAddContribuinte) {
		this.informacoesAddContribuinte = informacoesAddContribuinte;
	}
	
	public Adiantamento getAdiantamento() {
		return adiantamento;
	}

	public void setAdiantamento(Adiantamento adiantamento) {
		this.adiantamento = adiantamento;
	}

	public String getStatusNota() {
		return statusNota;
	}

	public void setStatusNota(String statusNota) {
		this.statusNota = statusNota;
	}

	public Integer getUfEmitente() {
		return ufEmitente;
	}

	public void setUfEmitente(Integer ufEmitente) {
		this.ufEmitente = ufEmitente;
	}

	public OperacaoFiscal getTributOperacaoFiscal() {
		return tributOperacaoFiscal;
	}

	public void setTributOperacaoFiscal(OperacaoFiscal tributOperacaoFiscal) {
		this.tributOperacaoFiscal = tributOperacaoFiscal;
	}

	public Venda getVendaCabecalho() {
		return vendaCabecalho;
	}

	public void setVendaCabecalho(Venda vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//public LancamentoProgramadoDetEntity getLancamentoProgramadoDetEntity() {
	//	return lancamentoProgramadoDetEntity;
	//}

	//public void setLancamentoProgramadoDetEntity(
	//		LancamentoProgramadoDetEntity lancamentoProgramadoDetEntity) {
	//	this.lancamentoProgramadoDetEntity = lancamentoProgramadoDetEntity;
	//}
	
	public DreVinculoEntity getDreVinculoEntity() {
		return dreVinculoEntity;
	}

	public void setDreVinculoEntity(DreVinculoEntity dreVinculoEntity) {
		this.dreVinculoEntity = dreVinculoEntity;
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}
	
	public ViewContratoDadosContratante getViewContratoDadosContratante() {
		return viewContratoDadosContratante;
	}

	public void setViewContratoDadosContratante(
			ViewContratoDadosContratante viewContratoDadosContratante) {
		this.viewContratoDadosContratante = viewContratoDadosContratante;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}