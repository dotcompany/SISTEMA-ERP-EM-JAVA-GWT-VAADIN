package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

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
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "codigo_numerico")
	@Caption(value = "Código numérico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoNumerico = "";

	@Field
	@Column(name = "natureza_operacao")
	@Caption(value = "Natureza da operação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String naturezaOperacao = "";

	@Field
	@Column(name = "indicador_forma_pagamento")
	@Caption(value = "Indicador da forma de pagamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String indicadorFormaPagamento = "";

	@Field
	@Column(name = "codigo_modelo")
	@Caption(value = "Código do modelo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoModelo = "";

	@Field
	@Column(name = "serie")
	@Caption(value = "Série")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String serie = "";

	@Field
	@Column(name = "numero")
	@Caption(value = "Número")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero = "";

	@Field
	@Column(name = "data_emissao")
	@Caption(value = "Data de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEmissao;

	@Field
	@Column(name = "data_entrada_saida")
	@Caption(value = "Data de entrada / saída")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEntradaSaida;

	@Field
	@Column(name = "hora_entrada_saida")
	@Caption(value = "Hora de entrada / saída")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String horaEntradaSaida = "";

	@Field
	@Column(name = "tipo_operacao")
	@Caption(value = "Tipo de operação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoOperacao = "";

	@Field
	@Column(name = "codigo_municipio")
	@Caption(value = "Código do município")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoMunicipio = new Integer(0);

	@Field
	@Column(name = "formato_impressao_danfe")
	@Caption(value = "Formato de impressão do DANFE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String formatoImpressaoDanfe = "";

	@Field
	@Column(name = "tipo_emissao")
	@Caption(value = "Tipo de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoEmissao = "";

	@Field
	@Column(name = "chave_acesso")
	@Caption(value = "Chave de acesso")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String chaveAcesso = "";

	@Field
	@Column(name = "digito_chave_acesso")
	@Caption(value = "Digíto da chave de acesso")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String digitoChaveAcesso = "";

	@Field
	@Column(name = "ambiente")
	@Caption(value = "Ambiente")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ambiente = "";

	@Field
	@Column(name = "finalidade_emissao")
	@Caption(value = "Finalidade de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String finalidadeEmissao = "";

	@Field
	@Column(name = "processo_emissao")
	@Caption(value = "Processo de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String processoEmissao = "";

	@Field
	@Column(name = "versao_processo_emissao")
	@Caption(value = "Versão do processo de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String versaoProcessoEmissao = "";

	@Field
	@Column(name = "data_entrada_contingencia")
	@Caption(value = "Data da entrada da contingência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEntradaContingencia;

	@Field
	@Column(name = "justificativa_contingencia")
	@Caption(value = "Justificativa da contingência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String justificativaContingencia = "";

	@Field
	@Column(name = "base_calculo_icms")
	@Caption(value = "Base de cálculo do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcms = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms")
	@Caption(value = "Valor do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcms = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_icms_st")
	@Caption(value = "Base de cálculo do ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms_st")
	@Caption(value = "Valor do ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_total_produtos")
	@Caption(value = "Valor total dos produtos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotalProdutos = new BigDecimal(0);

	@Field
	@Column(name = "valor_frete")
	@Caption(value = "Valor do frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorFrete = new BigDecimal(0);

	@Field
	@Column(name = "valor_seguro")
	@Caption(value = "Valor do seguro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSeguro = new BigDecimal(0);

	@Field
	@Column(name = "valor_desconto")
	@Caption(value = "Valor do desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Column(name = "valor_imposto_importacao")
	@Caption(value = "Valor do imposto de importação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorImpostoImportacao = new BigDecimal(0);

	@Field
	@Column(name = "valor_ipi")
	@Caption(value = "Valor do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIpi = new BigDecimal(0);

	@Field
	@Column(name = "valor_pis")
	@Caption(value = "Valor do PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPis = new BigDecimal(0);

	@Field
	@Column(name = "valor_cofins")
	@Caption(value = "Valor do COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCofins = new BigDecimal(0);

	@Field
	@Column(name = "valor_despesas_acessorias")
	@Caption(value = "Valor das despesas acessórias")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDespesasAcessorias = new BigDecimal(0);

	@Field
	@Column(name = "valor_total")
	@Caption(value = "Valor total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal = new BigDecimal(0);

	@Field
	@Column(name = "valor_servicos")
	@Caption(value = "Valor dos serviços")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorServicos = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_issqn")
	@Caption(value = "Base de cálculo do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_issqn")
	@Caption(value = "Valor do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_pis_issqn")
	@Caption(value = "Valor do PIS / ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPisIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_cofins_issqn")
	@Caption(value = "Valor do COFINS / ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCofinsIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_pis")
	@Caption(value = "Valor retido do PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoPis = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_cofins")
	@Caption(value = "Valor retido do COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoCofins = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_csll")
	@Caption(value = "Valor retido do CSLL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoCsll = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_irrf")
	@Caption(value = "Base de cálculo do IRRF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIrrf = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_irrf")
	@Caption(value = "Valor retido do IRRF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoIrrf = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_previdencia")
	@Caption(value = "Base de cálculo da previdência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoPrevidencia = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_previdencia")
	@Caption(value = "Valor retido da previdência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoPrevidencia = new BigDecimal(0);

	@Field
	@Column(name = "comex_uf_embarque")
	@Caption(value = "Comex UF de embarque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comexUfEmbarque = "";

	@Field
	@Column(name = "comex_local_embarque")
	@Caption(value = "Comex local de embarque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comexLocalEmbarque = "";

	@Field
	@Column(name = "compra_nota_empenho")
	@Caption(value = "Compra da nota de empenho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String compraNotaEmpenho = "";

	@Field
	@Column(name = "compra_pedido")
	@Caption(value = "Compra do pedido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String compraPedido = "";

	@Field
	@Column(name = "compra_contrato")
	@Caption(value = "Compra do contrato")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String compraContrato = "";

	@Field
	@Column(name = "informacoes_add_fisco")
	@Caption(value = "Informações add fisco")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String informacoesAddFisco = "";

	@Field
	@Column(name = "informacoes_add_contribuinte")
	@Caption(value = "Informações add contribuinte")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String informacoesAddContribuinte = "";

	@Field
	@Column(name = "status_nota")
	@Caption(value = "Status da nota")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String statusNota = "";

	@Field
	@Column(name = "uf_emitente")
	@Caption(value = "UF emitente")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer ufEmitente = new Integer(0);

	@Field
	@Column(name = "contato_email")
	@Caption(value = "E-mail do contato")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contatoEmail = "";

	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Column(name = "id_adiantamento")
	@Caption(value = "Adiantamento")
	private Integer adiantamento;

	@Field
	@Column(name = "id_tribut_operacao_fiscal")
	@Caption(value = "Tributário - Operação fiscal")
	private Integer tributOperacaoFiscal;

	@Field
	@Column(name = "id_venda_cabecalho")
	@Caption(value = "Venda - Cabeçalho")
	private Integer vendaCabecalho;

	@Field
	@Column(name = "id_fornecedor")
	@Caption(value = "Fornecedor")
	private Integer fornecedor;

	@Field
	@Column(name = "id_cliente")
	@Caption(value = "Cliente")
	private Integer cliente;

	@Field
	@Column(name = "id_contabil_lanca_programado_det")
	@Caption(value = "Contábil - Lança programado")
	private Integer contabilLancaProgramadoDet;

	@Field
	@Column(name = "id_contabil_dre_vinculo")
	@Caption(value = "Contábil - Dre vínculo")
	private Integer contabilDreVinculoEntity;

	@Field
	@Column(name = "id_view_contrato_dados_contratante")
	@Caption(value = "View contrato dados contratante")
	private Integer viewContratoDadosContratante;

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "nfeCabecalho", fetch = FetchType.LAZY)
	// private List<NfeDestinatarioEntity> nfeDestinatarioList;

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

	public Integer getAdiantamento() {
		return adiantamento;
	}

	public void setAdiantamento(Integer adiantamento) {
		this.adiantamento = adiantamento;
	}

	public Integer getTributOperacaoFiscal() {
		return tributOperacaoFiscal;
	}

	public void setTributOperacaoFiscal(Integer tributOperacaoFiscal) {
		this.tributOperacaoFiscal = tributOperacaoFiscal;
	}

	public Integer getVendaCabecalho() {
		return vendaCabecalho;
	}

	public void setVendaCabecalho(Integer vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	public Integer getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Integer fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getContabilLancaProgramadoDet() {
		return contabilLancaProgramadoDet;
	}

	public void setContabilLancaProgramadoDet(Integer contabilLancaProgramadoDet) {
		this.contabilLancaProgramadoDet = contabilLancaProgramadoDet;
	}

	public Integer getContabilDreVinculoEntity() {
		return contabilDreVinculoEntity;
	}

	public void setContabilDreVinculoEntity(Integer contabilDreVinculoEntity) {
		this.contabilDreVinculoEntity = contabilDreVinculoEntity;
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public Integer getViewContratoDadosContratante() {
		return viewContratoDadosContratante;
	}

	public void setViewContratoDadosContratante(
			Integer viewContratoDadosContratante) {
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