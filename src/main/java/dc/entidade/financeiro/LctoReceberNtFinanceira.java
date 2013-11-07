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

import dc.entidade.contabilidade.ContabilLancamentoDetalhe;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "LCTO_RECEBER_NT_FINANCEIRA")
public class LctoReceberNtFinanceira extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;
	@Column(name = "VALOR")
	private BigDecimal valor;
	@JoinColumn(name = "ID_LANCAMENTO_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private LancamentoReceber lancamentoReceber;
	@JoinColumn(name = "ID_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private NaturezaFinanceira naturezaanceira;
	@JoinColumn(name = "ID_CONTABIL_LANCAMENTO_DET", referencedColumnName = "ID")
	@ManyToOne
	private ContabilLancamentoDetalhe contabilLancamentoDetalhe;

	public LctoReceberNtFinanceira() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LancamentoReceber getLancamentoReceber() {
		return lancamentoReceber;
	}

	public void setLancamentoReceber(LancamentoReceber finLancamentoReceber) {
		this.lancamentoReceber = finLancamentoReceber;
	}

	public NaturezaFinanceira getNaturezaFinanceira() {
		return naturezaanceira;
	}

	public void setNaturezaFinanceira(NaturezaFinanceira naturezaanceira) {
		this.naturezaanceira = naturezaanceira;
	}

	public ContabilLancamentoDetalhe getContabilLancamentoDetalhe() {
		return contabilLancamentoDetalhe;
	}

	public void setContabilLancamentoDetalhe(ContabilLancamentoDetalhe contabilLancamentoDetalhe) {
		this.contabilLancamentoDetalhe = contabilLancamentoDetalhe;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.LctoReceberNtanceira[id=" + id + "]";
	}

}
