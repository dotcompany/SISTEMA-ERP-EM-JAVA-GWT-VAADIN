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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.ClienteEntity;

@Entity
@Table(name = "LANCAMENTO_RECEBER")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoReceber extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Field
	private Integer id;

	@Field
	@Column(name = "QUANTIDADE_PARCELA")
	@Caption(value = "Quantidade Parcela")
	private Integer quantidadeParcela;

	@Field
	@Column(name = "VALOR_TOTAL")
	@Caption(value = "Valor Total")
	private BigDecimal valorTotal;

	@Field
	@Column(name = "VALOR_A_RECEBER")
	@Caption(value = "Valor Receber")
	private BigDecimal valorAReceber;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_LANCAMENTO")
	@Caption(value = "Data Lançamento")
	private Date dataLancamento;

	@Field
	@Column(name = "NUMERO_DOCUMENTO")
	@Caption(value = "Número Documento")
	private String numeroDocumento;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "PRIMEIRO_VENCIMENTO")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimento;

	@Field
	@Column(name = "TAXA_COMISSAO")
	@Caption(value = "Taxa Comissão")
	private BigDecimal taxaComissao;

	@Field
	@Column(name = "VALOR_COMISSAO")
	@Caption(value = "Valor Comissão")
	private BigDecimal valorComissao;

	@Column(name = "INTERVALO_ENTRE_PARCELAS")
	@Field
	@Caption(value = "Inrvalo Parcelas")
	private Integer intervaloEntreParcelas;

	@Field
	@Column(name = "CODIGO_MODULO_LCTO")
	@Caption(value = "Código Módulo Lcto")
	private String codigoModuloLcto;

	@JoinColumn(name = "ID_DOCUMENTO_ORIGEM", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Documento Origem")
	private DocumentoOrigem documentoOrigem;

	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ClienteEntity cliente;

	@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaReceber> parcelasReceber = new ArrayList<>();

	@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<LctoReceberNtFinanceira> LctoReceberNtFinanceira = new ArrayList<>();

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

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/*
	 * public List<ParcelaReceber> getParcelaReceber() { return parcelaReceber;
	 * }
	 * 
	 * public void setParcelaReceber(List<ParcelaReceber> parcelaReceber) {
	 * this.parcelaReceber = parcelaReceber; }
	 */

	public List<LctoReceberNtFinanceira> getLctoReceberNtFinanceira() {
		return LctoReceberNtFinanceira;
	}

	public void setLctoReceberNtFinanceira(List<LctoReceberNtFinanceira> lctoReceberNtFinanceira) {
		LctoReceberNtFinanceira = lctoReceberNtFinanceira;
	}

	public void removeLctoReceberNtFinanceira(LctoReceberNtFinanceira value) {
		this.LctoReceberNtFinanceira.remove(value);
		value.setLancamentoReceber(null);
	}

	public LctoReceberNtFinanceira addLctoReceberNtFinanceira() {
		LctoReceberNtFinanceira lctoReceberNtFinanceira = new LctoReceberNtFinanceira();
		lctoReceberNtFinanceira.setLancamentoReceber(this);
		this.LctoReceberNtFinanceira.add(lctoReceberNtFinanceira);

		return lctoReceberNtFinanceira;
	}

	public List<ParcelaReceber> getParcelasReceber() {
		return parcelasReceber;
	}

	public void setParcelasReceber(List<ParcelaReceber> parcelaReceber) {
		this.parcelasReceber = parcelaReceber;
	}

	public void removeParcelaReceber(ParcelaReceber value) {
		// TODO Auto-generated method stub

	}

	public void addParcelaReceber(ParcelaReceber parcela) {
		parcela.setLancamentoReceber(this);
		this.parcelasReceber.add(parcela);

	}

}
