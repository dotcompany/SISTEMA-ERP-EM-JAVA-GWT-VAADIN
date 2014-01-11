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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}