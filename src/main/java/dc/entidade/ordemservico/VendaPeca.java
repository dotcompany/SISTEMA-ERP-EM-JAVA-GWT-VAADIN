package dc.entidade.ordemservico;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.produto.Produto;

@Entity
@Table(name = "os_venda_peca")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class VendaPeca extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_ordem_servico", referencedColumnName = "id")
	private OrdemServico ordemServico;

	@ManyToOne
	@JoinColumn(name = "id_vendedor", referencedColumnName = "id")
	private Colaborador vendedor;

	@ManyToOne
	@JoinColumn(name = "id_tecnico", referencedColumnName = "id")
	private Colaborador tecnico;

	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id")
	private Produto produto;
	
	@Field
	@Caption("TIPO PECA")
	@Column(name = "tipo_peca")
	private String tipoPeca;

	@Field
	@Caption("QUANTIDADE")
	@Column(name = "quantidade_produto")
	private BigDecimal quantidadeProduto;

	@Column(name = "valor_unitario_pago")
	private BigDecimal valorUnitarioPago;

	@Column(name = "valor_unitario_prod")
	private BigDecimal valorUnitario;

	@Column(name="valor_subtotal")
	private BigDecimal valorSubtotal;
	
	@Column(name = "perc_desconto")
	private BigDecimal percentualDesconto;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Colaborador getVendedor() {
		return vendedor;
	}

	public void setVendedor(Colaborador vendedor) {
		this.vendedor = vendedor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getTipoPeca() {
		return tipoPeca;
	}

	public void setTipoPeca(String tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	public BigDecimal getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(BigDecimal quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public BigDecimal getValorUnitarioPago() {
		return valorUnitarioPago;
	}

	public void setValorUnitarioPago(BigDecimal valorUnitarioPago) {
		this.valorUnitarioPago = valorUnitarioPago;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Colaborador getTecnico() {
		return tecnico;
	}

	public void setTecnico(Colaborador tecnico) {
		this.tecnico = tecnico;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}
	
}
