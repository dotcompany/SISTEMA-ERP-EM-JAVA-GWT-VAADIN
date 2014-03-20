package dc.entidade.ordemservico;

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

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.pessoal.Colaborador;

@Entity
@Table(name = "os_entrada_servico")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class EntradaServico extends AbstractModel<Integer> {

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
	@JoinColumn(name = "id_tecnico", referencedColumnName = "id")
	private Colaborador tecnico;

	@ManyToOne
	@JoinColumn(name = "id_vendedor", referencedColumnName = "id")
	private Colaborador vendedor;

	@ManyToOne
	@JoinColumn(name = "id_servico", referencedColumnName = "id")
	private ServicoOs servico;
	
	@Temporal(TemporalType.DATE)
	@Caption("DATA GARANTIA")
	@Column(name = "data_garantia")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataGarantia;

	@Field
	@Caption("HORA TRABALHADA")
	@Column(name = "hr_trabalhada")
	private BigDecimal horaTrabalhada;

	@Field
	@Caption("QUANTIDADE SERVICO")
	@Column(name = "quantidade_servico")
	private BigDecimal quantidadeServico;

	@Column(name = "valor_original")
	private BigDecimal valorOriginal;

	@Column(name = "valor_cobrado")
	private BigDecimal valorCobrado;

	@Column(name="valor_subtotal")
	private BigDecimal valorSubtotal;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "perc_desconto")
	private BigDecimal percentualDesconto;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "perc_tecnico")
	private BigDecimal percentualTecnico;
	
	@Column(name = "com_tecnico")
	private BigDecimal comissaoTecnico;

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

	public Colaborador getTecnico() {
		return tecnico;
	}

	public void setTecnico(Colaborador tecnico) {
		this.tecnico = tecnico;
	}

	public Colaborador getVendedor() {
		return vendedor;
	}

	public void setVendedor(Colaborador vendedor) {
		this.vendedor = vendedor;
	}

	public ServicoOs getServico() {
		return servico;
	}

	public void setServico(ServicoOs servico) {
		this.servico = servico;
	}

	public Date getDataGarantia() {
		return dataGarantia;
	}

	public void setDataGarantia(Date dataGarantia) {
		this.dataGarantia = dataGarantia;
	}

	public BigDecimal getHoraTrabalhada() {
		return horaTrabalhada;
	}

	public void setHoraTrabalhada(BigDecimal horaTrabalhada) {
		this.horaTrabalhada = horaTrabalhada;
	}

	public BigDecimal getQuantidadeServico() {
		return quantidadeServico;
	}

	public void setQuantidadeServico(BigDecimal quantidadeServico) {
		this.quantidadeServico = quantidadeServico;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getPercentualTecnico() {
		return percentualTecnico;
	}

	public void setPercentualTecnico(BigDecimal percentualTecnico) {
		this.percentualTecnico = percentualTecnico;
	}

	public BigDecimal getComissaoTecnico() {
		return comissaoTecnico;
	}

	public void setComissaoTecnico(BigDecimal comissaoTecnico) {
		this.comissaoTecnico = comissaoTecnico;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}
	

}
