package dc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.control.enums.SimNaoEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.FornecedorEntity;

/** @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados. */
@Entity
@Table(name = "lancamento_pagar")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoPagarEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@NotNull(message = "Pagamento Compartilhado é Obrigatório")
	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Pagamento Compartilhado")
	@Column(name = "PAGAMENTO_COMPARTILHADO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn pagamentoCompartilhado;

	@Field
	@Caption("Valor Total")
	@Column(name = "VALOR_TOTAL", precision = 14, scale = 0)
	//@NotNull(message = "Valor Total é Obrigatório")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorTotal;

	@Field
	@Column(name = "VALOR_A_PAGAR", precision = 14, scale = 0)
	@Caption(value = "Valor à Pagar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorAPagar;

	@Field
	@Caption(value = "Data Lançamento")
	@Column(name = "DATA_LANCAMENTO")
	@Temporal(TemporalType.DATE)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataLancamento;

	/*@Lob
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "IMAGEM_DOCUMENTO")
	@Caption(value = "Imagem Documento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String imagemDocumento;*/

	@JoinColumn(name = "ID_DOCUMENTO_ORIGEM", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Documento Origem")
	@NotNull(message = "Documento Origem é Obrigatório")
	private DocumentoOrigem documentoOrigem;

	@JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Fornecedor")
	@NotNull(message = "Fornecedor é Obrigatório")
	private FornecedorEntity fornecedor;

	@Field
	@Caption(value = "Quantidade Parcela")
	@Column(name = "QUANTIDADE_PARCELA")
	@NotNull(message = "Quantidade de Parcelas é Obrigatório")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcela;

	@Field
	@Caption(value = "Número Documento")
	@Column(name = "NUMERO_DOCUMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroDocumento;

	@Field
	@Caption(value = "Primeiro Vencimento")
	@Temporal(TemporalType.DATE)
	@Column(name = "PRIMEIRO_VENCIMENTO")
	@NotNull(message = "Primeiro Vencimento é Obrigatório")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date primeiroVencimento;

	@Field
	@Caption(value = "Código Módulo Lcto.")
	@Column(name = "CODIGO_MODULO_LCTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoModuloLcto;

	@Field
	@Caption(value = "Intervalo Entre Parcelas")
	@Column(name = "INTERVALO_ENTRE_PARCELAS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer intervaloEntreParcelas;

	@OneToMany(mappedBy = "lancamentoPagar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaPagar> parcelasPagar = new ArrayList<>();

	@OneToMany(mappedBy = "lancamentoPagar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<LctoPagarNtFinanceira> LctoPagarNtFinanceiras = new ArrayList<>();

	public LancamentoPagarEntity() {
	}

	public LancamentoPagarEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SimNaoEn getPagamentoCompartilhado() {
		return pagamentoCompartilhado;
	}

	public void setPagamentoCompartilhado(SimNaoEn pagamentoCompartilhado) {
		this.pagamentoCompartilhado = pagamentoCompartilhado;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(BigDecimal valorAPagar) {
		this.valorAPagar = valorAPagar;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	/**public String getImagemDocumento() {
		return imagemDocumento;
	}

	public void setImagemDocumento(String imagemDocumento) {
		this.imagemDocumento = imagemDocumento;
	}**/

	@Override
	public String toString() {
		return fornecedor.getPessoa().getNome();
	}

	/** @return the documentoOrigem */
	public DocumentoOrigem getDocumentoOrigem() {
		return documentoOrigem;
	}

	/** @param documentoOrigem
	 *            the documentoOrigem to set */
	public void setDocumentoOrigem(DocumentoOrigem documentoOrigem) {
		this.documentoOrigem = documentoOrigem;
	}

	/** @return the fornecedor */
	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	/** @param fornecedor
	 *            the fornecedor to set */
	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
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

	public String getCodigoModuloLcto() {
		return codigoModuloLcto;
	}

	public void setCodigoModuloLcto(String codigoModuloLcto) {
		this.codigoModuloLcto = codigoModuloLcto;
	}

	public Integer getIntervaloEntreParcelas() {
		return intervaloEntreParcelas;
	}

	public void setIntervaloEntreParcelas(Integer intervaloEntreParcelas) {
		this.intervaloEntreParcelas = intervaloEntreParcelas;
	}

	public void addParcelaPagar(ParcelaPagar parcela) {
		parcela.setLancamentoPagar(this);
		this.parcelasPagar.add(parcela);
	}

	public void removeParcelaPagar(ParcelaPagar parcela) {
		parcela.setLancamentoPagar(null);
		parcelasPagar.remove(parcela);

	}

	public List<ParcelaPagar> getParcelasPagar() {
		return parcelasPagar;
	}

	public void setParcelasPagar(List<ParcelaPagar> parcelasPagar) {
		this.parcelasPagar = parcelasPagar;
	}

	public void removeLctoPagarNtFinanceira(LctoPagarNtFinanceira value) {
		this.LctoPagarNtFinanceiras.remove(value);
		value.setLancamentoPagar(null);
	}
	
	public LctoPagarNtFinanceira addLctoPagarNtFinanceira(LctoPagarNtFinanceira lctoPagarNtFinanceira) {
		getLctoPagarNtFinanceiras().add(lctoPagarNtFinanceira);
		lctoPagarNtFinanceira.setLancamentoPagar(this);;

		return lctoPagarNtFinanceira;
	}

	public List<LctoPagarNtFinanceira> getLctoPagarNtFinanceiras() {
		return LctoPagarNtFinanceiras;
	}

	public void setLctoPagarNtFinanceiras(List<LctoPagarNtFinanceira> lctoPagarNtFinanceiras) {
		LctoPagarNtFinanceiras = lctoPagarNtFinanceiras;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LancamentoPagarEntity)) {
            return false;
        }

        LancamentoPagarEntity that = (LancamentoPagarEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(getId(), that.getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return new HashCodeBuilder()
                    .append(id)
                    .toHashCode();
        }
    }

}
