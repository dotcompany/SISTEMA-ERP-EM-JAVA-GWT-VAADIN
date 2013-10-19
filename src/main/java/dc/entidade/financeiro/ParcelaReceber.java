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

import dc.entidade.framework.AbstractModel;

@Entity
@Table(name = "PARCELA_RECEBER")
public class ParcelaReceber extends AbstractModel<Integer> {
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NUMERO_PARCELA")
	private Integer numeroParcela;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EMISSAO")
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VENCIMENTO")
	private Date dataVencimento;
	@Temporal(TemporalType.DATE)
	@Column(name = "DESCONTO_ATE")
	private Date descontoAte;
	@Column(name = "VALOR")
	private BigDecimal valor;
	@Column(name = "TAXA_JURO")
	private BigDecimal taxaJuro;
	@Column(name = "TAXA_MULTA")
	private BigDecimal taxaMulta;
	@Column(name = "TAXA_DESCONTO")
	private BigDecimal taxaDesconto;
	@Column(name = "VALOR_JURO")
	private BigDecimal valorJuro;
	@Column(name = "VALOR_MULTA")
	private BigDecimal valorMulta;
	@Column(name = "VALOR_DESCONTO")
	private BigDecimal valorDesconto;
	@Column(name = "EMITIU_BOLETO")
	private String emitiuBoleto;
	@Column(name = "BOLETO_NOSSO_NUMERO")
	private String boletoNossoNumero;
	@JoinColumn(name = "ID_LANCAMENTO_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private LancamentoReceber lancamentoReceber;
	@JoinColumn(name = "ID_STATUS_PARCELA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private StatusParcela finStatusParcela;
	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
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
