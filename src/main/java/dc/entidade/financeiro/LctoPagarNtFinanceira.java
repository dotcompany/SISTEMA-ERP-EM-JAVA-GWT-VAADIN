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
import org.hibernate.search.annotations.Indexed;

import dc.entidade.contabilidade.ContabilLancamentoDetalhe;
import dc.entidade.framework.AbstractModel;

@Entity
@Table(name = "LCTO_PAGAR_NT_FINANCEIRA")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LctoPagarNtFinanceira extends AbstractModel<Integer>{
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
    @JoinColumn(name = "ID_FIN_LANCAMENTO_PAGAR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private LancamentoPagar lancamentoPagar;
    @JoinColumn(name = "ID_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NaturezaFinanceira naturezaFinanceira;
    @JoinColumn(name = "ID_CONTABIL_LANCAMENTO_DET", referencedColumnName = "ID")
    @ManyToOne
    private ContabilLancamentoDetalhe contabilLancamentoDetalhe;

    public LctoPagarNtFinanceira() {
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

    public LancamentoPagar getLancamentoPagar() {
        return lancamentoPagar;
    }

    public void setLancamentoPagar(LancamentoPagar lancamentoPagar) {
        this.lancamentoPagar = lancamentoPagar;
    }

    public NaturezaFinanceira getNaturezaFinanceira() {
        return naturezaFinanceira;
    }

    public void setNaturezaFinanceira(NaturezaFinanceira naturezaFinanceira) {
        this.naturezaFinanceira = naturezaFinanceira;
    }

    public ContabilLancamentoDetalhe getContabilLancamentoDetalhe() {
        return contabilLancamentoDetalhe;
    }

    public void setContabilLancamentoDetalhe(ContabilLancamentoDetalhe contabilLancamentoDetalhe) {
        this.contabilLancamentoDetalhe = contabilLancamentoDetalhe;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinLctoPagarNtFinanceira[id=" + id + "]";
    }

}
