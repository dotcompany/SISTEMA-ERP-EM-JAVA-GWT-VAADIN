package dc.entidade.suprimentos.estoque;

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

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.produto.Produto;

@Entity
@Table(name = "estoque_contagem_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContagemDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_contagem_detalhe_id_seq")
	@SequenceGenerator(name = "estoque_contagem_detalhe_id_seq", sequenceName = "estoque_contagem_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Caption("Acuracidade")
	BigDecimal acuracidade;

	@Caption("Divergencia")
	BigDecimal divergencia;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Column(name = "quantidade_contada")
	private BigDecimal quantidadeContada;

	@Column(name = "quantidade_sistema")
	private BigDecimal quantidadeSistema;

	@ManyToOne
	@JoinColumn(name = "id_estoque_contagem_cabecalho")
	private ContagemCabecalhoEntity contagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAcuracidade() {
		return acuracidade;
	}

	public void setAcuracidade(BigDecimal acuracidade) {
		this.acuracidade = acuracidade;
	}

	public BigDecimal getDivergencia() {
		return divergencia;
	}

	public void setDivergencia(BigDecimal divergencia) {
		this.divergencia = divergencia;
	}

	public ContagemCabecalhoEntity getContagem() {
		return contagem;
	}

	public void setContagem(ContagemCabecalhoEntity contagem) {
		this.contagem = contagem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidadeContada() {
		return quantidadeContada;
	}

	public void setQuantidadeContada(BigDecimal quantidadeContada) {
		this.quantidadeContada = quantidadeContada;
	}

	public BigDecimal getQuantidadeSistema() {
		return quantidadeSistema;
	}

	public void setQuantidadeSistema(BigDecimal quantidadeSistema) {
		this.quantidadeSistema = quantidadeSistema;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}