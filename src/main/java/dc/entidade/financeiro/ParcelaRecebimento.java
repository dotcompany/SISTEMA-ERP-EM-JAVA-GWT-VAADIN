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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "PARCELA_RECEBIMENTO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParcelaRecebimento extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_RECEBIMENTO")
	@Caption(value = "Data Recebimento")
	@Field
	private Date dataRecebimento;

	@Caption(value = "Taxa Juros")
	@Column(name = "TAXA_JURO")
	@Field
	private BigDecimal taxaJuro;

	@Caption(value = "Taxa Multa")
	@Column(name = "TAXA_MULTA")
	@Field
	private BigDecimal taxaMulta;

	@Caption(value = "Taxa Desconto")
	@Column(name = "TAXA_DESCONTO")
	@Field
	private BigDecimal taxaDesconto;

	@Caption(value = "Valor juros")
	@Column(name = "VALOR_JURO")
	@Field
	private BigDecimal valorJuro;

	@Column(name = "VALOR_MULTA")
	@Caption(value = "Valor Multa")
	@Field
	private BigDecimal valorMulta;

	@Caption(value = "Valor Desconto")
	@Column(name = "VALOR_DESCONTO")
	@Field
	private BigDecimal valorDesconto;

	@Caption(value = "Valor Recebido")
	@Column(name = "VALOR_RECEBIDO")
	@Field
	private BigDecimal valorRecebido;

	@Caption(value = "Histórico")
	@Column(name = "HISTORICO")
	@Field
	private String historico;

	@JoinColumn(name = "ID_PARCELA_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ParcelaReceber parcelaReceber;

	@JoinColumn(name = "ID_TIPO_RECEBIMENTO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoRecebimento tipoRecebimento;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContaCaixa contaCaixa;

	@JoinColumn(name = "ID_CHEQUE_RECEBIDO", referencedColumnName = "ID")
	@ManyToOne
	private ChequeRecebido chequeRecebido;

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
		return parcelaReceber;
	}

	public void setParcelaReceber(ParcelaReceber finParcelaReceber) {
		this.parcelaReceber = finParcelaReceber;
	}

	public TipoRecebimento getTipoRecebimento() {
		return tipoRecebimento;
	}

	public void setTipoRecebimento(TipoRecebimento finTipoRecebimento) {
		this.tipoRecebimento = finTipoRecebimento;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public ChequeRecebido getChequeRecebido() {
		return chequeRecebido;
	}

	public void setChequeRecebido(ChequeRecebido finChequeRecebido) {
		this.chequeRecebido = finChequeRecebido;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.ParcelaRecebimento[id=" + id + "]";
	}

}
