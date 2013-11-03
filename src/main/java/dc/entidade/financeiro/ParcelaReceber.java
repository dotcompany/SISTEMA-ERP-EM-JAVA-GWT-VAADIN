package dc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "PARCELA_RECEBER")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParcelaReceber extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Field
	@Caption(value = "Id")
	private Integer id;

	@Field
	@Column(name = "NUMERO_PARCELA")
	@Caption(value = "Número Parcelas")
	private Integer numeroParcela;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EMISSAO")
	@Caption(value = "Data Emissão")
	private Date dataEmissao;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VENCIMENTO")
	@Caption(value = "Data Vencimento")
	private Date dataVencimento;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DESCONTO_ATE")
	@Caption(value = "Desconto até")
	private Date descontoAte;

	@Field
	@Column(name = "VALOR")
	@Caption(value = "Valor")
	private BigDecimal valor;

	@Column(name = "TAXA_JURO")
	@Field
	@Caption(value = "Taxa Juros")
	private BigDecimal taxaJuro;

	@Field
	@Column(name = "TAXA_MULTA")
	@Caption(value = "Taxa Multa")
	private BigDecimal taxaMulta;

	@Field
	@Column(name = "TAXA_DESCONTO")
	@Caption(value = "Taxa Desconto")
	private BigDecimal taxaDesconto;

	@Field
	@Column(name = "VALOR_JURO")
	@Caption(value = "Valor Juro")
	private BigDecimal valorJuro;

	@Field
	@Column(name = "VALOR_MULTA")
	@Caption(value = "Valor Multa")
	private BigDecimal valorMulta;

	@Field
	@Column(name = "VALOR_DESCONTO")
	@Caption(value = "Valor Desconto")
	private BigDecimal valorDesconto;

	@Column(name = "EMITIU_BOLETO")
	@Field
	@Caption(value = "Emitiu Boleto")
	private String emitiuBoleto;

	@Column(name = "BOLETO_NOSSO_NUMERO")
	@Field
	@Caption(value = "Boleto Nosso Número")
	private String boletoNossoNumero;

	@JoinColumn(name = "ID_LANCAMENTO_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private LancamentoReceber lancamentoReceber;

	@JoinColumn(name = "ID_STATUS_PARCELA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private StatusParcela finStatusParcela;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Conta Caixa")
	private ContaCaixa contaCaixa;

	@Transient
	private boolean selecionado;

	public ParcelaReceber() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDescontoAte() {
		return descontoAte;
	}

	public void setDescontoAte(Date descontoAte) {
		this.descontoAte = descontoAte;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxaJuro() {
		return taxaJuro;
	}

	public void setTaxaJuro(BigDecimal taxaJuro) {
		this.taxaJuro = taxaJuro;
	}

	public BigDecimal getTaxaMulta() {
		return taxaMulta;
	}

	public void setTaxaMulta(BigDecimal taxaMulta) {
		this.taxaMulta = taxaMulta;
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorJuro() {
		return valorJuro;
	}

	public void setValorJuro(BigDecimal valorJuro) {
		this.valorJuro = valorJuro;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getEmitiuBoleto() {
		return emitiuBoleto;
	}

	public void setEmitiuBoleto(String emitiuBoleto) {
		this.emitiuBoleto = emitiuBoleto;
	}

	public String getBoletoNossoNumero() {
		return boletoNossoNumero;
	}

	public void setBoletoNossoNumero(String boletoNossoNumero) {
		this.boletoNossoNumero = boletoNossoNumero;
	}

	public LancamentoReceber getLancamentoReceber() {
		return lancamentoReceber;
	}

	public void setLancamentoReceber(LancamentoReceber finLancamentoReceber) {
		this.lancamentoReceber = finLancamentoReceber;
	}

	public StatusParcela getStatusParcela() {
		return finStatusParcela;
	}

	public void setStatusParcela(StatusParcela finStatusParcela) {
		this.finStatusParcela = finStatusParcela;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.ParcelaReceber[id=" + id + "]";
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
