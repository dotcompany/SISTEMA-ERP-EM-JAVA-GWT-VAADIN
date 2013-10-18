package dc.entidade.financeiro;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.Cliente;

@Entity
@Table(name = "LANCAMENTO_RECEBER")
public class LancamentoReceber extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "QUANTIDADE_PARCELA")
	private Integer quantidadeParcela;
	@Column(name = "VALOR_TOTAL")
	private BigDecimal valorTotal;
	@Column(name = "VALOR_A_RECEBER")
	private BigDecimal valorAReceber;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_LANCAMENTO")
	private Date dataLancamento;
	@Column(name = "NUMERO_DOCUMENTO")
	private String numeroDocumento;
	@Temporal(TemporalType.DATE)
	@Column(name = "PRIMEIRO_VENCIMENTO")
	private Date primeiroVencimento;
	@Column(name = "TAXA_COMISSAO")
	private BigDecimal taxaComissao;
	@Column(name = "VALOR_COMISSAO")
	private BigDecimal valorComissao;
	@Column(name = "INTERVALO_ENTRE_PARCELAS")
	private Integer intervaloEntreParcelas;
	@Column(name = "CODIGO_MODULO_LCTO")
	private String codigoModuloLcto;
	@JoinColumn(name = "ID_FIN_DOCUMENTO_ORIGEM", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private DocumentoOrigem documentoOrigem;
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaReceber> parcelasReceber = new ArrayList<>();

	@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<LctoReceberNtFinanceira> LctoReceberNtFinanceiras = new ArrayList<>();

	public LancamentoReceber() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorAReceber() {
		return valorAReceber;
	}

	public void setValorAReceber(BigDecimal valorAReceber) {
		this.valorAReceber = valorAReceber;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getPrimeiroVencimento() {
		return primeiroVencimento;
	}

	public void setPrimeiroVencimento(Date primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}

	public Integer getIntervaloEntreParcelas() {
		return intervaloEntreParcelas;
	}

	public void setIntervaloEntreParcelas(Integer intervaloEntreParcelas) {
		this.intervaloEntreParcelas = intervaloEntreParcelas;
	}

	public String getCodigoModuloLcto() {
		return codigoModuloLcto;
	}

	public void setCodigoModuloLcto(String codigoModuloLcto) {
		this.codigoModuloLcto = codigoModuloLcto;
	}

	public DocumentoOrigem getDocumentoOrigem() {
		return documentoOrigem;
	}

	public void setDocumentoOrigem(DocumentoOrigem documentoOrigem) {
		this.documentoOrigem = documentoOrigem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.LancamentoReceber[id=" + id + "]";
	}

	public List<ParcelaReceber> getParcelasReceber() {
		return parcelasReceber;
	}

	public void setParcelasReceber(List<ParcelaReceber> parcelasReceber) {
		this.parcelasReceber = parcelasReceber;
	}

	public List<LctoReceberNtFinanceira> getLctoReceberNtFinanceiras() {
		return LctoReceberNtFinanceiras;
	}

	public void setLctoReceberNtFinanceiras(List<LctoReceberNtFinanceira> lctoReceberNtFinanceiras) {
		LctoReceberNtFinanceiras = lctoReceberNtFinanceiras;
	}

	public void removeLctoReceberNtFinanceira(LctoReceberNtFinanceira value) {
		// TODO Auto-generated method stub

	}

	public LctoReceberNtFinanceira addLctoReceberNtFinanceira() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeParcelaReceber(ParcelaReceber value) {
		// TODO Auto-generated method stub

	}

	public void addParcelaReceber(ParcelaReceber parcela) {
		// TODO Auto-generated method stub

	}

}
