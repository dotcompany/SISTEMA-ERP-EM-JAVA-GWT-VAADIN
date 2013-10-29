package dc.entidade.financeiro;

import java.io.Serializable;
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

@Entity
@Table(name = "PARCELA_RECEBIMENTO")
public class ParcelaRecebimento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_RECEBIMENTO")
	private Date dataRecebimento;
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
	@Column(name = "VALOR_RECEBIDO")
	private BigDecimal valorRecebido;
	@Column(name = "HISTORICO")
	private String historico;
	@JoinColumn(name = "ID_FIN_PARCELA_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ParcelaReceber finParcelaReceber;
	@JoinColumn(name = "ID_FIN_TIPO_RECEBIMENTO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoRecebimento finTipoRecebimento;
	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContaCaixa contaCaixa;
	@JoinColumn(name = "ID_FIN_CHEQUE_RECEBIDO", referencedColumnName = "ID")
	@ManyToOne
	private ChequeRecebido finChequeRecebido;

	public ParcelaRecebimento() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
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

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public ParcelaReceber getParcelaReceber() {
		return finParcelaReceber;
	}

	public void setParcelaReceber(ParcelaReceber finParcelaReceber) {
		this.finParcelaReceber = finParcelaReceber;
	}

	public TipoRecebimento getTipoRecebimento() {
		return finTipoRecebimento;
	}

	public void setTipoRecebimento(TipoRecebimento finTipoRecebimento) {
		this.finTipoRecebimento = finTipoRecebimento;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public ChequeRecebido getChequeRecebido() {
		return finChequeRecebido;
	}

	public void setChequeRecebido(ChequeRecebido finChequeRecebido) {
		this.finChequeRecebido = finChequeRecebido;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.ParcelaRecebimento[id=" + id + "]";
	}

}
