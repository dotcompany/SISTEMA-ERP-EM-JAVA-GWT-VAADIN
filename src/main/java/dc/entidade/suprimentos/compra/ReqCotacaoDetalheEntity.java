package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

/**
 * The persistent class for the compra_req_cotacao_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_req_cotacao_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ReqCotacaoDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_req_cotacao_detalhe_id_seq")
	@SequenceGenerator(name = "compra_req_cotacao_detalhe_id_seq", sequenceName = "compra_req_cotacao_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "quantidade_cotada")
	private BigDecimal quantidadeCotada;

	@ManyToOne
	@JoinColumn(name = "id_compra_cotacao")
	private Cotacao cotacao;

	@ManyToOne
	@JoinColumn(name = "id_compra_requisicao_detalhe")
	private RequisicaoDetalheEntity requisicaoDetalhe;

	public ReqCotacaoDetalheEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidadeCotada() {
		return this.quantidadeCotada;
	}

	public void setQuantidadeCotada(BigDecimal quantidadeCotada) {
		this.quantidadeCotada = quantidadeCotada;
	}

	public Cotacao getCotacao() {
		return this.cotacao;
	}

	public void setCotacao(Cotacao compraCotacao) {
		this.cotacao = compraCotacao;
	}

	public RequisicaoDetalheEntity getRequisicaoDetalhe() {
		return this.requisicaoDetalhe;
	}

	public void setRequisicaoDetalhe(
			RequisicaoDetalheEntity compraRequisicaoDetalhe) {
		this.requisicaoDetalhe = compraRequisicaoDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}