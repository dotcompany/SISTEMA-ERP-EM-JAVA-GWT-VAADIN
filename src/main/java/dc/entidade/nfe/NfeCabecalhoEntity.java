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

import dc.entidade.framework.AbstractMultiEmpresaModel;

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
	private String codigoNumerico;

	@Field
	@Column(name = "natureza_operacao")
	private String naturezaOperacao;

	@Field
	@Column(name = "indicador_forma_pagamento")
	private String indicadorFormaPagamento;

	@Field
	@Column(name = "codigo_modelo")
	private String codigoModelo;

	@Field
	@Column(name = "serie")
	private String serie;

	@Field
	@Column(name = "numero")
	private String numero;

	@Field
	@Column(name = "data_emissao")
	private Date dataEmissao;

	@Field
	@Column(name = "data_entrada_saida")
	private Date dataEntradaSaida;

	@Field
	@Column(name = "hora_entrada_saida")
	private String horaEntradaSaida;

	@Field
	@Column(name = "tipo_operacao")
	private String tipoOperacao;

	@Field
	@Column(name = "codigo_municipio")
	private Integer codigoMunicipio;

	@Field
	@Column(name = "formato_impressao_danfe")
	private String formatoImpressaoDanfe;

	@Field
	@Column(name = "tipo_emissao")
	private String tipoEmissao;

	@Field
	@Column(name = "chave_acesso")
	private String chaveAcesso;

	@Field
	@Column(name = "digito_chave_acesso")
	private String digitoChaveAcesso;

	@Field
	@Column(name = "ambiente")
	private String ambiente;

	@Field
	@Column(name = "finalidade_emissao")
	private String finalidadeEmissao;

	@Field
	@Column(name = "processo_emissao")
	private String processoEmissao;

	@Field
	@Column(name = "versao_processo_emissao")
	private String versaoProcessoEmissao;

	@Field
	@Column(name = "data_entrada_contingencia")
	private Date dataEntradaContingencia;

	@Field
	@Column(name = "justificativa_contingencia")
	private String justificativaContingencia;

	@Field
	@Column(name = "base_calculo_icms")
	private BigDecimal baseCalculoIcms;

	@Field
	@Column(name = "valor_icms")
	private BigDecimal valorIcms;

	@Field
	@Column(name = "base_calculo_icms_st")
	private BigDecimal baseCalculoIcmsSt;

	@Field
	@Column(name = "valor_icms_st")
	private BigDecimal valorIcmsSt;

	@Field
	@Column(name = "valor_total_produtos")
	private BigDecimal valorTotalProdutos;

	@Field
	@Column(name = "valor_frete")
	private BigDecimal valorFrete;

	@Field
	@Column(name = "valor_seguro")
	private BigDecimal valorSeguro;

	@Field
	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Field
	@Column(name = "valor_imposto_importacao")
	private BigDecimal valorImpostoImportacao;

	@Field
	@Column(name = "valor_ipi")
	private BigDecimal valorIpi;

	@Field
	@Column(name = "valor_pis")
	private BigDecimal valorPis;

	@Field
	@Column(name = "valor_cofins")
	private BigDecimal valorCofins;

	@Field
	@Column(name = "valor_despesas_acessorias")
	private BigDecimal valorDespesasAcessorias;

	@Field
	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Field
	@Column(name = "valor_servicos")
	private BigDecimal valorServicos;

	@Field
	@Column(name = "base_calculo_issqn")
	private BigDecimal baseCalculoIssqn;

	@Field
	@Column(name = "valor_issqn")
	private BigDecimal valorIssqn;

	@Field
	@Column(name = "valor_pis_issqn")
	private BigDecimal valorPisIssqn;

	@Field
	@Column(name = "valor_cofins_issqn")
	private BigDecimal valorCofinsIssqn;

	@Field
	@Column(name = "valor_retido_pis")
	private BigDecimal valorRetidoPis;

	@Field
	@Column(name = "valor_retido_cofins")
	private BigDecimal valorRetidoCofins;

	@Field
	@Column(name = "valor_retido_csll")
	private BigDecimal valorRetidoCsll;

	@Field
	@Column(name = "base_calculo_irrf")
	private BigDecimal baseCalculoIrrf;

	@Field
	@Column(name = "valor_retido_irrf")
	private BigDecimal valorRetidoIrrf;

	@Field
	@Column(name = "base_calculo_previdencia")
	private BigDecimal baseCalculoPrevidencia;

	@Field
	@Column(name = "valor_retido_previdencia")
	private BigDecimal valorRetidoPrevidencia;

	@Field
	@Column(name = "comex_uf_embarque")
	private String comexUfEmbarque;

	@Field
	@Column(name = "comex_local_embarque")
	private String comexLocalEmbarque;

	@Field
	@Column(name = "compra_nota_empenho")
	private String compraNotaEmpenho;

	@Field
	@Column(name = "compra_pedido")
	private String compraPedido;

	@Field
	@Column(name = "compra_contrato")
	private String compraContrato;

	@Field
	@Column(name = "informacoes_add_fisco")
	private String informacoesAddFisco;

	@Field
	@Column(name = "informacoes_add_contribuinte")
	private String informacoesAddContribuinte;

	@Field
	@Column(name = "status_nota")
	private String statusNota;

	@Field
	@Column(name = "uf_emitente")
	private Integer ufEmitente;

	// id_tribut_operacao_fiscal integer,
	// id_venda_cabecalho integer,
	// id_empresa integer,
	// id_fornecedor integer,
	// id_cliente integer,

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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}